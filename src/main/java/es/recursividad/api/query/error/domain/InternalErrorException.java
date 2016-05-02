package es.recursividad.api.query.error.domain;

/**
 * Exception than indicates that some internal error has occurred.
 *
 * @author victor.hernandez @ recursividad.es
 */
public class InternalErrorException extends DomainException {

    private static final long serialVersionUID = -3534530693729146208L;

    public InternalErrorException(String message) {
        super(DomainError.INTERNAL_ERROR, message);
    }
}
