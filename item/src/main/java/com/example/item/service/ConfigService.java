package com.example.item.service;

import com.example.item.config.Configuration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigService {
    private final Configuration configuration;

    public String getExample() {
        return configuration.getExample();
    }
}
