package es.recursividad.api.query.domain.runscope;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author victor.hernandez @ recursividad.es
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Document(collection =  "messages")
public class Message {

    @Id @Field("_id")
    @JsonProperty("uuid")           private String uuid;
    @JsonProperty("bucket_key")     private String bucketKey;
    @JsonProperty("request")        private Request request;
    @JsonProperty("response")       private Response response;
    @JsonProperty("detailed")        private Boolean detailed = false;
}
