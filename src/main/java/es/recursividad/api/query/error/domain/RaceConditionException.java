package es.recursividad.api.query.error.domain;

/**
 * Exception that indicates that a race condition has occurred and therefore the request could not be processed.
 *
 * @author victor.hernandez @ recursividad.es
 */
public class RaceConditionException extends DomainException {

    private static final long serialVersionUID = -8101215144691065194L;

    public RaceConditionException(String message) {
        super(DomainError.RACE_CONDITION, message);
    }
}
