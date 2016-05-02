package es.recursividad.api.query.error.boundary;

import org.springframework.http.HttpStatus;

/**
 * REST API exception that indicates a bad client request that could not be understood by the server due to
 * malformed syntax. The client should not repeat the request without modifications.
 * <p/>
 * Throwing this exception returns a response to the client with {@link HttpStatus#BAD_REQUEST} status code.
 *
 * @author victor.hernandez @ recursividad.es
 */
public class BadRequestException extends RestApiException {

    private static final long serialVersionUID = -1844753226369393517L;

    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public BadRequestException(String message, Exception cause) {
        super(HttpStatus.BAD_REQUEST, message, cause);
    }
}
