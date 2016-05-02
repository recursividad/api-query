package es.recursividad.api.query.error.domain;

/**
 * Exception that indicates that the required entity has been deleted.
 *
 * @author victor.hernandez @ recursividad.es
 */
public class DeletedEntityException extends DomainException {

    private static final long serialVersionUID = 7057224299835833179L;

    public DeletedEntityException(String message) {
        super(DomainError.DELETED, message);
    }
}
