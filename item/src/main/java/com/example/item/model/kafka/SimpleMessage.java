package com.example.item.model.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SimpleMessage {
    //тут еще тип события м т.п.
    private long itemId;
    private String thingName;
}
