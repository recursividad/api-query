package es.recursividad.api.query.error.domain;

/**
 * Exception that indicates that an argument is required for processing the invocation.
 * <p/>
 * This exception should be used whenever an expected parameter was not received or was not an instance of the expected type.
 * Use {@link UnmetConditionException} for further condition assertions.
 *
 * @author victor.hernandez @ recursividad.es
 */
public class BadArgumentException extends DomainException {

    private static final long serialVersionUID = -7017410600351876372L;

    public BadArgumentException(String message) {
        super(DomainError.BAD_ARGUMENT, message);
    }
}
