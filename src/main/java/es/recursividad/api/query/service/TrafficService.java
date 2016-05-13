package es.recursividad.api.query.service;

import es.recursividad.api.query.client.RunscopeApiClient;
import es.recursividad.api.query.domain.Bucket;
import es.recursividad.api.query.domain.runscope.Message;
import es.recursividad.api.query.domain.runscope.Result;
import es.recursividad.api.query.error.domain.InternalErrorException;
import es.recursividad.api.query.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

/**
 * @author victor.hernandez @ recursividad.es
 */
@Slf4j
@Service
public class TrafficService {

    @Autowired private BucketService bucketService;
    @Autowired private RunscopeApiClient runscopeApiClient;
    @Autowired private MessageRepository messageRepository;

    /**
     * Capture all the traffic for every registered bucket.
     */
    public void capture() {
        // Get all the buckets
        List<Bucket> buckets = bucketService.getAllBuckets();

        // For each bucket capture the new traffic
        for (Bucket bucket : buckets) {
            getBucketTraffic(bucket);
        }
    }

    /**
     * Retrieve the a bucket traffic from Runscope.
     *
     * @param bucket Bucket.
     */
    public void getBucketTraffic(@NotNull Bucket bucket) {
        Double since = bucket.getLastSummaryTime() != null ? bucket.getLastSummaryTime().toInstant(ZoneOffset.UTC).toEpochMilli() / 1000d : null;
        Double before = null;
        Boolean loadMore = true;

        do {
            try {
                if (before != null)
                log.info(String.format("Last summary time is %s. Capturing", LocalDateTime.ofEpochSecond(before.longValue(), (int) ((before - before.longValue()) * 1000000000), ZoneOffset.UTC)));
                Result result = runscopeApiClient.getMessageStream(String.format("Bearer %s", bucket.getToken()), bucket.getId(), since, before).execute().body();

                if (!result.getData().isEmpty()) {
                //    messageRepository.save(result.getData());

                    Message lastMessage = result.getData().get(result.getData().size() - 1);
                    before = lastMessage.getResponse().getTimestamp();
                }

                loadMore = !result.getData().isEmpty();

            } catch (IOException e) {
                throw new InternalErrorException("Couldn't connect with Runscope.");
            }
        } while(loadMore);
    }
}
