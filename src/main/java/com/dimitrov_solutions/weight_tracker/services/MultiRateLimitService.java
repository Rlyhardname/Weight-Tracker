package com.dimitrov_solutions.weight_tracker.services;

import io.github.bucket4j.Bucket;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MultiRateLimitService {

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    public Bucket resolveBucket(String email) {
        return cache.computeIfAbsent(email, this::newBucket);
    }

    private Bucket newBucket(String email) {
        return Bucket.builder()
                .addLimit((limit) ->
                        limit.capacity(1)
                                .refillIntervally(1, Duration.ofHours(1))).build();
    }
}