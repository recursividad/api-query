package es.recursividad.api.query.service;

import es.recursividad.api.query.client.RunscopeApiClient;
import es.recursividad.api.query.domain.Bucket;
import es.recursividad.api.query.domain.runscope.ListResult;
import es.recursividad.api.query.domain.runscope.Message;
import es.recursividad.api.query.error.domain.InternalErrorException;
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
    @Autowired private MessageService messageService;

    /**
     * Capture all the traffic for every registered bucket.
     */
    public void capture() {
        // Get all the buckets
        List<Bucket> buckets = bucketService.getAllBuckets();

        // For each bucket capture the new traffic
        for (Bucket bucket : buckets) {
            getBucketTraffic(bucket);
            messageService.getBucketMessageDetails(bucket);
        }
    }

    /**
     * Retrieve the a bucket traffic from Runscope.
     *
     * @param bucket Bucket.
     */
    public void getBucketTraffic(@NotNull Bucket bucket) {
        log.info(String.format("Starting to get traffic in bucket with ID %s", bucket.getId()));
        int total = 0;

        Double since = bucket.getLastSummaryTime() != null ? bucket.getLastSummaryTime().toInstant(ZoneOffset.UTC).toEpochMilli() / 1000d : null;
        Double before = null;
        Boolean loadMore = true;

        do {
            try {
                if (before != null)
                log.info(String.format("Last summary time is %s. Capturing", LocalDateTime.ofEpochSecond(before.longValue(), (int) ((before - before.longValue()) * 1000000000), ZoneOffset.UTC)));
                ListResult result = runscopeApiClient.getMessageStream(String.format("Bearer %s", bucket.getToken()), bucket.getId(), since, before).execute().body();
                if (!result.getData().isEmpty()) {
                    Message lastMessage = result.getData().get(result.getData().size() - 1);

                    // Check if there are more messages
                    if (messageService.findById(lastMessage.getUuid()) != null) {
                        loadMore = false;
                    }

                    // Store the traffic in the database
                    for (Message message : result.getData()) {
                        // Check that the message doesn't already exists
                        if (messageService.findById(message.getUuid()) == null) {
                            messageService.insert(message);
                            total++;
                        }
                    }


                    // Prepare next query
                    before = lastMessage.getResponse().getTimestamp();
                } else {
                    loadMore = false;
                }

            } catch (IOException e) {
                throw new InternalErrorException("Couldn't connect with Runscope.");
            }
        } while(loadMore);

        log.info("Done retrieving messages for bucket {}. Total new messages {}.", bucket.getId(), total);
    }
}
