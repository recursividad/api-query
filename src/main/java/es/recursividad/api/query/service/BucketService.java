package es.recursividad.api.query.service;

import es.recursividad.api.query.domain.Bucket;
import es.recursividad.api.query.error.domain.BadArgumentException;
import es.recursividad.api.query.error.domain.DuplicatedEntityException;
import es.recursividad.api.query.error.domain.EntityNotFoundException;
import es.recursividad.api.query.error.domain.UnauthorizedAccessException;
import es.recursividad.api.query.repository.BucketRepository;
import es.recursividad.api.query.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author victor.hernandez @ recursividad.es
 */
@Service
public class BucketService {

    @Autowired private BucketRepository bucketRepository;
    @Autowired private SecurityService securityService;

    /**
     * Registers a Bucket to track it's traffic.
     *
     * @param bucket Bucket.
     * @return Registered bucket.
     */
    public Bucket register(@NotNull Bucket bucket) {
        // Check input
        if (bucket == null || bucket.getId() == null || bucket.getToken() == null) {
            throw new BadArgumentException("The Bucket to be registered cannot be null.");
        }
        // Check permissions
        if (!securityService.canReadBucket(bucket.getToken(), bucket.getId())) {
            throw new UnauthorizedAccessException("Cannot read the bucket info with the provided access token.");
        }
        // Check the bucket it's not already registered
        if (bucketRepository.exists(bucket.getId())) {
            throw new DuplicatedEntityException(String.format("Bucket %s already exists.", bucket.getId()));
        }

        return bucketRepository.save(bucket);
    }

    /**
     * Unregisters a Bucket and deletes all it's traffic.
     *
     * @param bucketId Bucket ID.
     * @return {@code true} in case of success or {@code false} otherwise.
     */
    public boolean unregister(@NotNull String token, @NotNull String bucketId) {
        // Check input
        if (bucketId.isEmpty()) {
            throw new BadArgumentException("The Bucket ID cannot be null or empty");
        }
        // Check that the bucket exists
        if (!bucketRepository.exists(bucketId)) {
            throw new EntityNotFoundException(String.format("Cannot find Bucket with ID %s", bucketId));
        }
        // Check that the user has access to the bucket
        if (!securityService.canReadBucket(token, bucketId)) {
            throw new UnauthorizedAccessException("Cannot access to the bucket with the provided access token.");
        }

        // Delete the bucket
        bucketRepository.delete(bucketId);

        return true;
    }

    /**
     * Get one bucket info.
     *
     * @param token Access token.
     * @param bucketId Bucket ID.
     * @return Bucket.
     */
    public Bucket getBucket(@NotNull String token, @NotNull String bucketId) {
        // Check input
        if (bucketId.isEmpty()) {
            throw new BadArgumentException("The Bucket ID cannot be null or empty");
        }
        // Check that the bucket exists
        if (!bucketRepository.exists(bucketId)) {
            throw new EntityNotFoundException(String.format("Cannot find Bucket with ID %s", bucketId));
        }

        // Retrieve the bucket
        Bucket bucket = bucketRepository.findOne(bucketId);

        // Check that the user can access to the requested bucket
        if (!bucket.getToken().equals(token)) {
            throw new UnauthorizedAccessException("Cannot access to the bucket with the provided access token.");
        }

        return bucket;
    }

    /**
     * Get all the buckets registered in the system.
     *
     * @return List of buckets registed in the system.
     */
    protected List<Bucket> getAllBuckets() {
        return bucketRepository.findAll();
    }
}
