package com.example.item.config;

import com.example.item.model.Thing;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("thing")
public interface ThingFeignClient {
    @RequestMapping(
            method= RequestMethod.GET,
            value="/v1/thing/itemId/{itemId}",
            consumes="application/json")
    Thing getThing
            (@PathVariable("itemId")
             long itemId);
}
