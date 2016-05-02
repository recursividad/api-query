package es.recursividad.api.query.error.boundary;

import org.springframework.http.HttpStatus;

/**
 * REST API exception that indicates that the method specified in the request is not allowed for the resource.
 * <p/>
 * The response must include an {@code Allow} header containing a list of valid methods for the requested resource.
 * <p/>
 * Throwing this exception returns a response to the client with {@link HttpStatus#METHOD_NOT_ALLOWED} status code.
 *
 * @author victor.hernandez @ recursividad.es
 */
public class MethodNotAllowedException extends RestApiException {

    private static final long serialVersionUID = -4022294996243650693L;

    public MethodNotAllowedException(String message) {
        super(HttpStatus.METHOD_NOT_ALLOWED, message);
    }

    public MethodNotAllowedException(String message, Exception cause) {
        super(HttpStatus.METHOD_NOT_ALLOWED, message, cause);
    }
}
