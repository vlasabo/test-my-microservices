package com.example.thing.model.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class SimpleMessage {
    private long itemId;
    private String thingName;

    public SimpleMessage() {
    }

    public SimpleMessage(long itemId, String thingName) {
        this.itemId = itemId;
        this.thingName = thingName;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getThingName() {
        return thingName;
    }

    public void setThingName(String thingName) {
        this.thingName = thingName;
    }

    @Override
    public String toString() {
        return "SimpleMessage{" +
                "itemId=" + itemId +
                ", thingName='" + thingName + '\'' +
                '}';
    }
}
