package com.example.item.service;

import com.example.item.config.ThingFeignClient;
import com.example.item.model.Thing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThingService {
    private final ThingFeignClient thingFeignClient;

    public Thing getThingById(long id) {
        return thingFeignClient.getThing(id);
    }
}
