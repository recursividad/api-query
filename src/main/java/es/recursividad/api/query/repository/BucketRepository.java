package es.recursividad.api.query.repository;

import es.recursividad.api.query.domain.Bucket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author victor.hernandez @ recursividad.es
 */
public interface BucketRepository extends CrudRepository<Bucket, String> {

    List<Bucket> findAll();
}
