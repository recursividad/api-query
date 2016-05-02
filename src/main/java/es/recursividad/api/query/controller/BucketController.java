package es.recursividad.api.query.controller;

import es.recursividad.api.query.boundary.AuthorizationHeader;
import es.recursividad.api.query.boundary.BucketPostForm;
import es.recursividad.api.query.domain.Bucket;
import es.recursividad.api.query.service.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author victor.hernandez @ recursividad.es
 */
@RestController("buckets")
@RequestMapping("/buckets")
public class BucketController {

    @Autowired private BucketService bucketService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Bucket> registerBucket(
            @RequestHeader(name = "Authorization", required = true) AuthorizationHeader authorizationHeader,
            @RequestBody @NotNull @Valid BucketPostForm bucketPostForm) {
        // Build the bucket
        Bucket bucket = bucketPostForm.build();
        bucket.setToken(authorizationHeader.getToken());

        // Register the bucket
        bucket = bucketService.register(bucket);

        return new ResponseEntity<>(bucket, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{bucketId}",  method = RequestMethod.DELETE)
    public ResponseEntity<?> unregisterBucket(
            @RequestHeader(name = "Authorization", required = true) AuthorizationHeader authorizationHeader,
            @PathVariable("bucketId") String bucketId) {
        // Try to unregister the bucket
        boolean result = bucketService.unregister(authorizationHeader.getToken(), bucketId);

        return new ResponseEntity<>(result ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{bucketId}", method = RequestMethod.GET)
    public ResponseEntity<Bucket> getBucket(
            @RequestHeader(name = "Authorization", required = true) AuthorizationHeader authorizationHeader,
            @PathVariable("bucketId") String bucketId) {
        // Get the bucket info
        Bucket bucket = bucketService.getBucket(authorizationHeader.getToken(), bucketId);

        return new ResponseEntity<>(bucket, HttpStatus.OK);
    }
}
