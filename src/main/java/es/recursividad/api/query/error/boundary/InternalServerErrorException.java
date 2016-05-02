package es.recursividad.api.query.error.boundary;

import org.springframework.http.HttpStatus;

/**
 * REST API exception that indicates that the server encountered an unexpected condition which prevented
 * it from fulfilling the request.
 * <p/>
 * Throwing this exception returns a response to the client with {@link HttpStatus#INTERNAL_SERVER_ERROR} status code.
 *
 * @author victor.hernandez @ recursividad.es
 */
public class InternalServerErrorException extends RestApiException {

    private static final long serialVersionUID = 5188505117953269267L;

    public InternalServerErrorException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    public InternalServerErrorException(String message, Exception cause) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message, cause);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message, cause);
    }
}