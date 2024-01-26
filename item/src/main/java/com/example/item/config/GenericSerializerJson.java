package com.example.item.config;


import com.example.item.model.kafka.SimpleMessage;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.support.serializer.JsonSerializer;

@NoArgsConstructor
public class GenericSerializerJson extends JsonSerializer<Object> {
    @Override
    public byte[] serialize(String topic, Headers headers, Object data) {

        try (JsonSerializer<SimpleMessage> commandSerializer = new JsonSerializer<>()) {
            return commandSerializer.serialize(topic, headers, (SimpleMessage) data);
        }

    }
}
