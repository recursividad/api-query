package es.recursividad.api.query.error.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Exception class that all business exceptions in the system must inherit to be handled properly.
 *
 * @author victor.hernandez @ recursividad.es
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class DomainException extends RuntimeException {

    private static final long serialVersionUID = -6413500377167785105L;

    private DomainError error;
    private String message;

    protected DomainException(DomainError error) {
        this.error = error;
    }
}
