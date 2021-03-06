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
public class ListResult {

    @JsonProperty("data")   private List<Message> data;
    @JsonProperty("meta")   private Map<Object, Object> meta;
    @JsonProperty("error")  private Map<Object, Object> error;
}
