package es.recursividad.api.query.error.boundary;

import org.springframework.http.HttpStatus;

/**
 * REST API exception that indicates that the request could not be completed due to a conflict with the
 * current state of the resource.
 * <p/>
 * This exception only should be thrown in situations where it is expected that the user might be able to resolve
 * the conflict and resubmit the request.
 * <p/>
 * Throwing this exception returns a response to the client with {@link HttpStatus#CONFLICT} status code.
 *
 * @author victor.hernandez @ recursividad.es
 */
public class ConflictException extends RestApiException {

    private static final long serialVersionUID = -7405042139433769760L;

    public ConflictException(String message) {
        super(HttpStatus.CONFLICT, message);
    }

    public ConflictException(String message, Exception cause) {
        super(HttpStatus.CONFLICT, message, cause);
    }
}
