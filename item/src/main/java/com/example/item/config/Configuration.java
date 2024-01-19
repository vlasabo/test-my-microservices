package com.example.item.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@NoArgsConstructor
@ConfigurationProperties(prefix = "item")
@Component
@org.springframework.context.annotation.Configuration
public class Configuration {
    private String example;

}
