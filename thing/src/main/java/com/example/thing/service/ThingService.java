package com.example.thing.service;

import com.example.thing.model.Thing;
import com.example.thing.repository.ThingRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ThingService {
    private final ThingRepository thingRepository;

    public Optional<Thing> getThingById(long thingId) {
        return thingRepository.findById(thingId);
    }

    public Thing addThing(Thing thing) {
        return thingRepository.save(thing);
    }

    @CircuitBreaker(name = "thingService")
    @RateLimiter(name = "thingService")
    public Optional<Thing> getThingByItemId(long itemId) {
        return thingRepository.getThingByItemId(itemId);
    }

}
