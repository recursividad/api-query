package es.recursividad.api.query.error.boundary;

import org.springframework.http.HttpStatus;

/**
 * REST API exception that indicates that a given precondition in one or more of the request headers was not met.
 * <p/>
 * This exception allows the client to place preconditions on the current resource metainformation and thus prevent
 * the requested method from being applied to a resource other than te one intended.
 * <p/>
 * Throwing this exception returns a response to the client with {@link HttpStatus#PRECONDITION_FAILED} status code.
 *
 * @author victor.hernandez @ recursividad.es
 */
public class PreconditionFailedException extends RestApiException {

    private static final long serialVersionUID = 3630196237232412289L;

    public PreconditionFailedException(String message) {
        super(HttpStatus.PRECONDITION_FAILED, message);
    }

    public PreconditionFailedException(String message, Exception cause) {
        super(HttpStatus.PRECONDITION_FAILED, message, cause);
    }
}
