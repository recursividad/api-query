package es.recursividad.api.query.error;

import es.recursividad.api.query.error.boundary.*;
import es.recursividad.api.query.error.domain.*;
import es.recursividad.api.query.error.domain.UnsupportedOperationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author victor.hernandez @ recursividad.es
 */
@Slf4j
@ControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<RestApiException> handleRestException(RestApiException restException) {
        log.error("{} {}", restException.getStatus(), restException.getMessage());
        return new ResponseEntity<>(restException, restException.getStatus());
    }

    @ExceptionHandler({ BadArgumentException.class, IllegalArgumentException.class})
    public ResponseEntity<RestApiException> handleBadRequestException(Exception domainException) {
        RestApiException restException = new BadRequestException(domainException.getMessage(), domainException);
        restException.setStackTrace(domainException.getStackTrace());

        return handleRestException(restException);
    }

    @ExceptionHandler({ DeletedEntityException.class, EntityNotFoundException.class })
    public ResponseEntity<RestApiException> handleNotFoundException(DomainException domainException) {
        RestApiException restException = new NotFoundException(domainException.getMessage(), domainException);
        restException.setStackTrace(domainException.getStackTrace());

        return handleRestException(restException);
    }

    @ExceptionHandler({ DuplicatedEntityException.class, RaceConditionException.class, UnmetConditionException.class })
    public ResponseEntity<RestApiException> handleConflictException(DomainException domainException) {
        RestApiException restException = new ConflictException(domainException.getMessage(), domainException);
        restException.setStackTrace(domainException.getStackTrace());

        return handleRestException(restException);
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<RestApiException> handleMethodNotAllowedException(UnsupportedOperationException domainException) {
        RestApiException restException = new MethodNotAllowedException(domainException.getMessage(), domainException);
        restException.setStackTrace(domainException.getStackTrace());

        return handleRestException(restException);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<RestApiException> handleForbiddenException(UnauthorizedAccessException domainException) {
        RestApiException restException = new ForbiddenException(domainException.getMessage(), domainException);
        restException.setStackTrace(domainException.getStackTrace());

        return handleRestException(restException);
    }

    @ExceptionHandler({ InternalErrorException.class, DomainException.class, Throwable.class })
    public ResponseEntity<RestApiException> handleInternalErrorException(Throwable throwable) {
        RestApiException restException = new InternalServerErrorException(throwable.getMessage(), throwable);
        restException.setStackTrace(throwable.getStackTrace());

        return handleRestException(restException);
    }
}
