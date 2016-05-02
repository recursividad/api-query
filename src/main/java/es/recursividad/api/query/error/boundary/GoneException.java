package es.recursividad.api.query.error.boundary;

import org.springframework.http.HttpStatus;

/**
 * REST API exception that indicates that the requested resource is no longer available and no forwarding
 * address is known.
 * <p/>
 * This condition is expected to be considered permanent. If it is not possible to determine whether or not
 * the condition is permanent use {@link NotFoundException} instead.
 * <p/>
 * Throwing this exception returns a response to the client with {@link HttpStatus#GONE} status code.
 *
 * @author victor.hernandez @ recursividad.es
 */
public class GoneException extends RestApiException {

    private static final long serialVersionUID = 5454326669671377450L;

    public GoneException(String message) {
        super(HttpStatus.GONE, message);
    }

    public GoneException(String message, Exception cause) {
        super(HttpStatus.GONE, message, cause);
    }
}
