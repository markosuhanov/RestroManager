package com.suhIT.restroManager.controller;

import com.suhIT.restroManager.dto.ItemCategoryDTO;
import com.suhIT.restroManager.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/itemCategories")
public class ItemCategoryController {

    private final ItemCategoryService itemCategoryService;

    @Autowired
    public ItemCategoryController(ItemCategoryService itemCategoryService) {
        this.itemCategoryService = itemCategoryService;
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody ItemCategoryDTO dto) {

        return new ResponseEntity<>(itemCategoryService.createItemCategory(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id,
                                         @RequestBody ItemCategoryDTO itemCategoryDTO) {

        ItemCategoryDTO updatedItemCategory = itemCategoryService.updateItemCategory(id, itemCategoryDTO);
        return new ResponseEntity<>(updatedItemCategory, HttpStatus.OK);
    }


    @GetMapping("/{name}")
    public ResponseEntity<ItemCategoryDTO> getByName(@PathVariable String name) {
        ItemCategoryDTO itemCategory = itemCategoryService.getByName(name);
        return new ResponseEntity<>(itemCategory, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<ItemCategoryDTO>> getAll() {
        List<ItemCategoryDTO> categories = itemCategoryService.getAllItemCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/allActive")
    public ResponseEntity<List<ItemCategoryDTO>> getAllActive() {
        List<ItemCategoryDTO> categories = itemCategoryService.getAllActiveItemsCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/deactivate/{name}")
    public ResponseEntity<Object> deactivate(@PathVariable String name) {
        return new ResponseEntity<>(itemCategoryService.deactivateItemCategory(name), HttpStatus.OK);
    }

    @PostMapping("/activate/{name}")
    public ResponseEntity<Object> activate(@PathVariable String name) {
        return new ResponseEntity<>(itemCategoryService.activateItemCategory(name), HttpStatus.OK);
    }



}
