package com.example.item.service;

import com.example.item.config.KafkaConfig;
import com.example.item.config.ThingFeignClient;
import com.example.item.model.Item;
import com.example.item.model.Thing;
import com.example.item.repository.ItemRepository;
import com.example.item.service.kafka.KafkaProducer;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ThingFeignClient thingFeignClient;
    private final KafkaProducer kafkaProducer;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Item getItemById(long itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        Thing thing = thingFeignClient.getThing(itemId);
        item.orElseThrow(NotFoundException::new).setThingName(thing.getName());
        return item.get();
    }

    public Item add(Item item) {
        kafkaProducer.produceNewItemMessage(item);
        logger.warn("some item {} create", item);
        return itemRepository.save(item);

    }
}
