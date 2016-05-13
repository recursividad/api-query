package es.recursividad.api.query.repository;

import es.recursividad.api.query.domain.runscope.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author victor.hernandez @ recursividad.es
 */
public interface MessageRepository extends CrudRepository<Message, String> {

    List<Message> findAllByBucketKey(String bucketKey);

    List<Message> findAllByBucketKeyAndDetailedIsFalse(String bucketKey);
}
