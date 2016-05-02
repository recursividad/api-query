package es.recursividad.api.query.boundary;

import es.recursividad.api.query.error.domain.BadArgumentException;
import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author victor.hernandez @ recursividad.es
 */
public class AuthorizationHeader {

    private static final Pattern authorizationPattern = Pattern.compile("(Bearer )(.*)");
    @Getter private String token;

    public AuthorizationHeader(String authorizationHeader) {
        // Check that the authorization header is valid
        Matcher authorizationMatcher = authorizationPattern.matcher(authorizationHeader);
        if (!authorizationMatcher.matches()) {
            throw new BadArgumentException("Provide a valid Bearer authorization token to continue");
        }

        token = authorizationMatcher.group(2);
    }
}
