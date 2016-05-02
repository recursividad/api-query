package es.recursividad.api.query.error.domain;

/**
 * Exception that indicates a condition required by a property was not met by a set of input parameters.
 * <p/>
 * This exception should be used whenever a known input condition was not met.
 *
 * @author victor.hernandez @ recursividad.es
 */
public class UnmetConditionException extends DomainException {

    private static final long serialVersionUID = -4576547645027915364L;

    public UnmetConditionException(String message) {
        super(DomainError.UNMET_CONDITION, message);
    }
}