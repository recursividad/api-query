package es.recursividad.api.query.security;

import es.recursividad.api.query.client.RunscopeApiClient;
import es.recursividad.api.query.error.domain.InternalErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;

/**
 * @author victor.hernandez @ recursividad.es
 */
@Service
public class SecurityService {

    @Autowired
    RunscopeApiClient runscopeApiClient;

    /**
     * Check if the its possible to read the bucket with the given token.
     *
     * @param token Bucket access token.
     * @param bucketId Bucket ID.
     * @return {@code true} if it's possible to read the bucket with the provided token or {@code false} otherwise.
     */
    public boolean canReadBucket(String token, String bucketId) {
        Response<?> response;
        try {
            response = runscopeApiClient.getBucketInfo(String.format("Bearer %s", token), bucketId).execute();
        } catch (IOException e) {
            throw new InternalErrorException("Couldn't connect with Runscope.");
        }

        return response.isSuccessful();
    }
}
