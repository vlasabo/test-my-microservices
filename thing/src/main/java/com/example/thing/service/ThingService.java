package com.example.thing.service;

import com.example.thing.model.Thing;
import com.example.thing.model.redis.ThingItemHash;
import com.example.thing.repository.ThingRepository;
import com.example.thing.repository.redis.ThingItemHashRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ThingService {
    private final ThingRepository thingRepository;
    private final ThingItemHashRepository thingItemHashRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

    public String getThingNameByItemId(long itemId){
        Optional<ThingItemHash> fromRedis = thingItemHashRepository.findById(itemId);
        if (fromRedis.isPresent()) {
            logger.warn("return from Redis");
            return fromRedis.get().getThingName();
        }

        logger.warn("return from DB");
        return getThingByItemId(itemId)
                .map(Thing::getName)
                .orElse("");
    }

}
