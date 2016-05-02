package es.recursividad.api.query.error.domain;

/**
 * Exception that indicates that the required entity does not exist.
 *
 * @author victor.hernandez @ recursividad.es
 */
public class EntityNotFoundException extends DomainException {

    private static final long serialVersionUID = 8864890829548141483L;

    public EntityNotFoundException(String message) {
        super(DomainError.NOT_FOUND, message);
    }
}
