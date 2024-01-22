package com.example.item.service;

import com.example.item.config.ThingFeignClient;
import com.example.item.model.Item;
import com.example.item.model.Thing;
import com.example.item.repository.ItemRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ThingFeignClient thingFeignClient;

    public Item getItemById(long itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        Thing thing = thingFeignClient.getThing(itemId);
        item.orElseThrow(NotFoundException::new).setThingName(thing.getName());
        return item.get();
    }

    public Item add(Item item) {
        return itemRepository.save(item);
    }
}
