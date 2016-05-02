package es.recursividad.api.query.error.boundary;

import org.springframework.http.HttpStatus;

/**
 * REST API exception that indicates that the server has not found the requested resource. This exception does
 * not indicate whether the condition is temporary or permanent.
 * <p/>
 * Throwing this exception returns a response to the client with {@link HttpStatus#NOT_FOUND} status code.
 *
 * @author victor.hernandez @ recursividad.es
 */
public class NotFoundException extends RestApiException {

    private static final long serialVersionUID = -3836969812732066779L;

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public NotFoundException(String message, Exception cause) {
        super(HttpStatus.NOT_FOUND, message, cause);
    }
}
