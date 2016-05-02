package es.recursividad.api.query.error.domain;

/**
 * Exception that indicates that an operation is not supported.
 * <p/>
 * This exception should be used whenever an operation is no longer supported but it was in any prior version.
 *
 * @author victor.hernandez @ recursividad.es
 */
public class UnsupportedOperationException extends DomainException {

    private static final long serialVersionUID = -3571899502345518202L;

    public UnsupportedOperationException(String message) {
        super(DomainError.UNSUPPORTED_OPERATION, message);
    }
}