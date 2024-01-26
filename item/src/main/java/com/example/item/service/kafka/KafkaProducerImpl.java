package com.example.item.service.kafka;

import com.example.item.model.Item;
import com.example.item.model.kafka.SimpleMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class KafkaProducerImpl implements KafkaProducer {
    private final KafkaTemplate<String, SimpleMessage> kafkaTemplate;
    public static final String ITEM_TOPIC_NAME = "newSimpleTopic";

    @Override
    public void produceNewItemMessage(Item item) {
        CompletableFuture<SendResult<String, SimpleMessage>> completableFuture =
                this.kafkaTemplate.send(ITEM_TOPIC_NAME, "item", new SimpleMessage(item.getId(), item.getThingName()));
        try {
            System.out.println(completableFuture.get().getRecordMetadata());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
