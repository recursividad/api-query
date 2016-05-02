package es.recursividad.api.query.error.boundary;

import org.springframework.http.HttpStatus;

/**
 * REST API exception that indicates that the server understood the request but is refusing to fulfill it.
 * <p/>
 * Throwing this exception returns a response to the client with {@link HttpStatus#FORBIDDEN} status code.
 *
 * @author victor.hernandez @ recursividad.es
 */
public class ForbiddenException extends RestApiException {

    private static final long serialVersionUID = 888869710397857558L;

    public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }

    public ForbiddenException(String message, Exception cause) {
        super(HttpStatus.FORBIDDEN, message, cause);
    }
}
