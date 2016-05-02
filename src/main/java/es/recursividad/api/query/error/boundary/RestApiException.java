package es.recursividad.api.query.error.boundary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Exception class that wraps any REST API error.
 * All API exceptions should extend this class in order to be handled properly.
 * <p/>
 * Plus the wrapped exception, it contains a {@link HttpStatus} code and a
 * message that will be returned as response.
 *
 * @author victor.hernandez @ recursividad.es
 */
@Getter
@JsonIgnoreProperties(value = { "cause", "stackTrace", "localizedMessage", "suppressed" })
public class RestApiException extends RuntimeException {

    private HttpStatus status;
    private String message;
    private Throwable cause;

    protected RestApiException(HttpStatus status, String message, Throwable cause) {
        this.status = status;
        this.message = message;
        this.cause = cause;
    }

    protected RestApiException(HttpStatus status, String message) {
        this(status, message, null);
    }
}
