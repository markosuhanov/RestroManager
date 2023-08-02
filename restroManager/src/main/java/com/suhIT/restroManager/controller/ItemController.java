package com.suhIT.restroManager.controller;

import com.suhIT.restroManager.dto.ItemDTO;
import com.suhIT.restroManager.dto.UserDTO;
import com.suhIT.restroManager.model.Item;
import com.suhIT.restroManager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/{itemType}")
    public ResponseEntity<ItemDTO> create(@Valid @RequestBody ItemDTO itemDTO, @PathVariable("itemType") String itemType) {
        ItemDTO item = itemService.createItem(itemDTO, itemType);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }



    @GetMapping("/{itemName}")
    public ResponseEntity<ItemDTO> getByName( @PathVariable("itemName") String itemName) {
        ItemDTO itemDto = itemService.getItemByName(itemName);
        return new ResponseEntity<>(itemDto, HttpStatus.OK);
    }

    @GetMapping("/id/{itemId}")
    public ResponseEntity<ItemDTO> getById( @PathVariable("itemId") Long itemId) {
        ItemDTO itemDto = itemService.getItemById(itemId);
        return new ResponseEntity<>(itemDto, HttpStatus.OK);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<ItemDTO> softDeleteItem(@PathVariable Long id) {
        ItemDTO items = itemService.deactivateItem(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ItemDTO> activateItem(@PathVariable Long id) {
        ItemDTO items = itemService.activateItem(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable Long id, @RequestBody ItemDTO item) {
        ItemDTO updatedItem = itemService.updateItem(id, item);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);

    }
}
