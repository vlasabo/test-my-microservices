package com.example.thing.service;

import com.example.thing.model.Thing;
import com.example.thing.repository.ThingRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

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
    public Optional<Thing> getThingByItemId(long itemId) {
        simulateException();
        return thingRepository.getThingByItemId(itemId);
    }

    private void simulateException() {
        Random rand = new Random();
        int randomNum = rand.nextInt(3) + 1;
        if (randomNum % 2 == 1) {
            throw new RuntimeException();
        }
    }
}
