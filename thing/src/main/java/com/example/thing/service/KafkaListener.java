package com.example.thing.service;

import com.example.thing.model.kafka.SimpleMessage;
import com.example.thing.model.redis.ThingItemHash;
import com.example.thing.repository.redis.ThingItemHashRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ObjectMapper objectMapper;
    private final ThingItemHashRepository thingItemHashRepository;

    @org.springframework.kafka.annotation.KafkaListener(topics = {"newSimpleTopic"},
            groupId = "groupId")
    public void consume(final ConsumerRecord<String, String> record,
                        final @Header(KafkaHeaders.OFFSET) Integer offset,
                        final @Header(KafkaHeaders.RECEIVED_KEY) String key,
                        final @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                        final @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                        final @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts
    ) throws JsonProcessingException {
        SimpleMessage simpleMessage = objectMapper.readValue(record.value(), SimpleMessage.class);
        logger.error("!" + simpleMessage.toString());
        thingItemHashRepository.save(new ThingItemHash(simpleMessage.getItemId(), simpleMessage.getThingName()));
//      тут немного логики по получению сообщения. Я понимаю что надо десериализовать либо черещ internallibs с
//      объектом-сообщением, прикрученным как зависимость к сервисам, либо
//      прикручивать Avro с реестром схем, но у меня не выдерживает ПК такого количества контейнеров :)

    }
}
