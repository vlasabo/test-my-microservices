package com.example.thing.model.redis;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * Created by vladimirsabo on 27.01.2024
 */
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@RedisHash("ThingItemHash")
public class ThingItemHash  implements Serializable {

    @Id
    private Long itemId;
    private String thingName;
}
