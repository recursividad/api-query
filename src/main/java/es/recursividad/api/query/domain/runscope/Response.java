package es.recursividad.api.query.domain.runscope;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author victor.hernandez @ recursividad.es
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class Response {

    @JsonProperty("headers")        private Map<String, List<String>> headers;
    @JsonProperty("status")         private Integer status;
    @JsonProperty("reason")         private String reason;
    @JsonProperty("size_bytes")     private Integer sizeByte;
    @JsonProperty("timestamp")      private String timestamp;
    @JsonProperty("body")           private String body;
    @JsonProperty("body_encoding")  private String bodyEncoding;
}
