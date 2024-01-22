package com.example.thing.controller;

import com.example.thing.model.Thing;
import com.example.thing.service.ThingService;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/v1/thing/")
@RequiredArgsConstructor
public class ThingController {
    private final ThingService thingService;

    @GetMapping(value = "{thingId}")
    public ResponseEntity<Thing> getThing(@PathVariable long thingId) {
        return new ResponseEntity<>(thingService.getThingById(thingId).orElseThrow(NotFoundException::new),
                HttpStatus.OK);
    }

    @GetMapping(value = "/itemId/{itemId}")
    public Thing getThingByItemId(@PathVariable long itemId) {
        new Thread(() -> thingService.getThingByItemId(itemId)).start();
        new Thread(() -> thingService.getThingByItemId(itemId)).start();
        new Thread(() -> thingService.getThingByItemId(itemId)).start();
        new Thread(() -> thingService.getThingByItemId(itemId)).start();
        new Thread(() -> thingService.getThingByItemId(itemId)).start();
        new Thread(() -> thingService.getThingByItemId(itemId)).start();
        return new Thing();
    }

    @PostMapping(value = "add")
    public ResponseEntity<Thing> addThing(@RequestBody Thing thing) {
        return new ResponseEntity<>(thingService.addThing(thing), HttpStatus.CREATED); //упрощено без ДТО
    }
}
