package es.recursividad.api.query.error.boundary;

import org.springframework.http.HttpStatus;

/**
 * REST API exception that indicates that the server is currently unable to handle the request due to a temporary
 * overloading or maintenance process.
 * <p/>
 * This exception should only be thrown to indicate a temporary condition which will be alleviated after some delay.
 * It the length of the delay is known it may be indicated in a {@code Retry-After} header.
 * <p/>
 * Throwing this exception returns a response to the client with {@link HttpStatus#SERVICE_UNAVAILABLE} status code.
 *
 * @author victor.hernandez @ recursividad.es
 */
public class ServiceUnavailableException extends RestApiException {

    private static final long serialVersionUID = -5896277965685346135L;

    public ServiceUnavailableException(String message) {
        super(HttpStatus.SERVICE_UNAVAILABLE, message);
    }

    public ServiceUnavailableException(String message, Exception cause) {
        super(HttpStatus.SERVICE_UNAVAILABLE, message, cause);
    }
}
