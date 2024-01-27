package com.example.thing.repository.redis;

import com.example.thing.model.redis.ThingItemHash;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

/**
 * Created by vladimirsabo on 27.01.2024
 */
public interface ThingItemHashRepository extends KeyValueRepository<ThingItemHash, Long> {
}
