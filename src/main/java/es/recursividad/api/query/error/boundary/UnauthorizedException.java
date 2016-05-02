package es.recursividad.api.query.error.boundary;

import org.springframework.http.HttpStatus;

/**
 * REST API exception that indicates that the request requires user authentication.
 * <p/>
 * Throwing this exception returns a response to the client with {@link HttpStatus#UNAUTHORIZED} status code.
 *
 * @author victor.hernandez @ recursividad.es
 */
public class UnauthorizedException extends RestApiException {

    private static final long serialVersionUID = -422459006693770557L;

    public UnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }

    public UnauthorizedException(String message, Exception cause) {
        super(HttpStatus.UNAUTHORIZED, message, cause);
    }
}
