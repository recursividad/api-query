package es.recursividad.api.query.error.domain;

import lombok.Getter;

/**
 * Enumeration of business errors with its internal code.
 *
 * @author victor.hernandez @ recursividad.es
 */
@Getter
public enum DomainError {

    BAD_ARGUMENT(1),
    UNMET_CONDITION(2),
    DELETED(3),
    NOT_FOUND(4),
    DUPLICATED_ENTITY(5),
    UNSUPPORTED_OPERATION(6),
    RACE_CONDITION(7),
    UNAUTHORIZED_ACCESS(8),
    INTERNAL_ERROR(0);

    private int code;

    DomainError(int code) {
        this.code = code;
    }
}
