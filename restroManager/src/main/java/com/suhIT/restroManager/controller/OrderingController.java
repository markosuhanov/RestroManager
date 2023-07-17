package com.suhIT.restroManager.controller;

import com.suhIT.restroManager.dto.ItemDTO;
import com.suhIT.restroManager.service.OrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderingController {

    private final OrderingService orderingService;

    @Autowired
    public OrderingController(OrderingService orderingService) {
        this.orderingService = orderingService;
    }

//    @PostMapping("/{itemType}")
//    public ResponseEntity<ItemDTO> create(@Valid @RequestBody ItemDTO itemDTO, @PathVariable("itemType") String itemType) {
//        ItemDTO item = itemService.createItem(itemDTO, itemType);
//        return new ResponseEntity<>(item, HttpStatus.OK);
//    }


//    @PostMapping("/{itemType}")
//    public ResponseEntity<ItemDTO> create(@Valid @RequestBody ItemDTO itemDTO, @PathVariable("itemType") String itemType) {
//        ItemDTO item = itemService.createItem(itemDTO, itemType);
//        return new ResponseEntity<>(item, HttpStatus.OK);
//    }
}
