package es.recursividad.api.query.error.domain;

/**
 * @author victor.hernandez @ recursividad.es
 */
public class UnauthorizedAccessException extends DomainException {

    private static final long serialVersionUID = 3402498827449080845L;

    public UnauthorizedAccessException(String message) {
        super(DomainError.UNAUTHORIZED_ACCESS, message);
    }

}
