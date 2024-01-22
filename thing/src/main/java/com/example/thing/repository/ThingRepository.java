package com.example.thing.repository;

import com.example.thing.model.Thing;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ThingRepository extends CrudRepository<Thing, Long> {

    Optional<Thing> getThingByItemId(long itemId);
}
