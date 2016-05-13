package es.recursividad.api.query.client;

import es.recursividad.api.query.domain.runscope.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.Map;

/**
 * @author victor.hernandez @ recursividad.es
 */
public interface RunscopeApiClient {

    @GET("/buckets")
    Call<Map<String, Object>> getBuckets(@Header("Authorization") String bearer);

    @GET("/buckets/{bucketId}")
    Call<Map<String, Object>> getBucketInfo(@Header("Authorization") String bearer, @Path("bucketId") String bucketId);

    @GET("/buckets/{bucketId}/messages")
    Call<Result> getMessageStream(@Header("Authorization") String bearer, @Path("bucketId") String bucketId, @Query("since") Double since, @Query("before") Double before);

    @GET("/buckets/{bucketId}/messages/{messageId}")
    Call<Map<String, Object>> getMessageDetails(@Header("Authorization") String bearer, @Path("bucketId") String bucketId, @Path("messageId") String messageId);
}
