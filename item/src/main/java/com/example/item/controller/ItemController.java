package com.example.item.controller;

import com.example.item.model.Item;
import com.example.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/item/")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping(value = "getItem/{itemId}")
    public Item getItemById(@PathVariable long itemId) {
        return itemService.getItemById(itemId); //упрощено
    }

    @PostMapping(value = "add")
    public Item addItem(@RequestBody Item item) {
        return itemService.add(item);
    }
}
