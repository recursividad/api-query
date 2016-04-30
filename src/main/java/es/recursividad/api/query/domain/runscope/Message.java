package es.recursividad.api.query.domain.runscope;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author victor.hernandez @ recursividad.es
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class Message {


    @JsonProperty("uuid")           private String uuid;
    @JsonProperty("bucket_key")     private String bucketKey;
    @JsonProperty("request")        private Request request;
    @JsonProperty("response")       private Response response;
}
