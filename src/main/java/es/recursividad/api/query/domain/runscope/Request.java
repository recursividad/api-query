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
public class Request {

    @JsonProperty("headers")        private Map<String, List<String>> headers;
    @JsonProperty("host")           private String host;
    @JsonProperty("method")         private String method;
    @JsonProperty("params")         private Map<String, List<String>> params;
    @JsonProperty("path")           private String path;
    @JsonProperty("scheme")         private String scheme;
    @JsonProperty("timestamp")      private Double timestamp;
    @JsonProperty("body")           private String body;
    @JsonProperty("body_encoding")  private String bodyEncoding;
}
