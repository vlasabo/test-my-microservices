package com.example.item.service.kafka;

import com.example.item.model.Item;

public interface KafkaProducer {
    void produceNewItemMessage(Item item);
}
