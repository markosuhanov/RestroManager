package com.suhIT.restroManager.controller;

import com.suhIT.restroManager.dto.ItemDTO;
import com.suhIT.restroManager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ItemDTO>> getAll() {
        List<ItemDTO> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/allActive")
    public ResponseEntity<List<ItemDTO>> getAllActive() {
        List<ItemDTO> items = itemService.getAllActiveItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/allFood")
    public ResponseEntity<List<ItemDTO>> getAllFoodItems() {
        List<ItemDTO> items = itemService.getAllFoodItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/allActiveFood")
    public ResponseEntity<List<ItemDTO>> getAllActiveFoodItems() {
        List<ItemDTO> items = itemService.getAllActiveFoodItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }


    @GetMapping("/allDrinks")
    public ResponseEntity<List<ItemDTO>> getAllDrinkItems() {
        List<ItemDTO> items = itemService.getAllDrinkItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    @GetMapping("/allActiveDrink")
    public ResponseEntity<List<ItemDTO>> getAllActiveDrinkItems() {
        List<ItemDTO> items = itemService.getAllActiveDrinkItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
