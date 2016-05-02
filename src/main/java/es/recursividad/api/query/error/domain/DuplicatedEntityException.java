package es.recursividad.api.query.error.domain;

/**
 * Exception that indicates that the given entity already exists.
 * <p/>
 * This exception should be used when trying to create an entity that already exists.
 *
 * @author victor.hernandez @ recursividad.es
 */
public class DuplicatedEntityException extends DomainException {

    private static final long serialVersionUID = 617472258855621620L;

    public DuplicatedEntityException(String message) {
        super(DomainError.DUPLICATED_ENTITY, message);
    }
}
