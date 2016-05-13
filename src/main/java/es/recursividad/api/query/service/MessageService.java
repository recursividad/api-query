package es.recursividad.api.query.service;

import es.recursividad.api.query.client.RunscopeApiClient;
import es.recursividad.api.query.domain.Bucket;
import es.recursividad.api.query.domain.runscope.Message;
import es.recursividad.api.query.domain.runscope.MessageResult;
import es.recursividad.api.query.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

/**
 * @author victor.hernandez @ recursividad.es
 */
@Slf4j
@Service
public class MessageService {

    @Autowired private RunscopeApiClient runscopeApiClient;
    @Autowired private MessageRepository messageRepository;

    /**
     * Find one message by its ID.
     * @param id Message ID.
     * @return Message.
     */
    public Message findById(String id) {
        return messageRepository.findOne(id);
    }

    /**
     * Insert a list of messages into the database.
     *
     * @param messages List of messages to be inserted.
     */
    public void insert(List<Message> messages) {
        if (messages != null && !messages.isEmpty()) {
            messages.forEach(this::insert);
        }
    }

    /**
     * Insert a message into the database.
     *
     * @param message Message to be inserted.
     */
    public void insert(Message message) {
        if (message != null) {
            message.setDetailed(false);
            save(message);
        }
    }

    /**
     * Store a list of messages into the database.
     * @param messages List of messages to store.
     */
    public void save(List<Message> messages) {
        if (messages != null && !messages.isEmpty()) {
            messages.forEach(this::save);
        }
    }

    /**
     * Store a message into the database.
     *
     * @param message Message to be store.
     */
    public void save(Message message) {
        if (message != null) {
            messageRepository.save(message);
        }
    }

    /**
     * Retrieve the bucket message details from Runscope.
     *
     * @param bucket Bucket.
     */
    public void getBucketMessageDetails(@NotNull Bucket bucket) {
        log.info(String.format("Starting to get details for messages in bucket with ID %s", bucket.getId()));
        int successful = 0;
        int notFound = 0;

        List<Message> undetailedMessages = messageRepository.findAllByBucketKeyAndDetailedIsFalse(bucket.getId());

        if (undetailedMessages != null && !undetailedMessages.isEmpty()) {
            for (Message message : undetailedMessages) {
                try {
                    Response<MessageResult> response = runscopeApiClient.getMessageDetails(String.format("Bearer %s", bucket.getToken()), bucket.getId(), message.getUuid()).execute();

                    if (response.isSuccessful()) {
                        successful++;
                        message = response.body().getData();
                    } else {
                        notFound++;
                    }

                    // Store the result
                    message.setDetailed(true);
                    messageRepository.save(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        log.info("Done retrieving message details for bucket {}. Total {}. Success {}. Not found {}", bucket.getId(), successful+notFound, successful, notFound);
    }
}
