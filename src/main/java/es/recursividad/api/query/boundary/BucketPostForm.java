package es.recursividad.api.query.boundary;

import es.recursividad.api.query.domain.Bucket;
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
public class BucketPostForm {

    private String id;

    public Bucket build() {
        return Bucket.builder().id(id).build();
    }
}
