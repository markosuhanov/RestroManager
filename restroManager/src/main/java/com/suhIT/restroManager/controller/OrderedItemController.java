package com.suhIT.restroManager.controller;


import com.suhIT.restroManager.dto.OrderedItemDTO;
import com.suhIT.restroManager.service.OrderedItemService;
import com.suhIT.restroManager.service.implementation.OrderedItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/orderedItems")
public class OrderedItemController {

    private final OrderedItemService orderedItemService;

    @Autowired
    public OrderedItemController(OrderedItemService orderedItemService){
        this.orderedItemService = orderedItemService;
    }

    @PatchMapping("/{id}/prepared")
    public ResponseEntity<OrderedItemDTO> orderedItemPrepared(@PathVariable("id") Long orderedItemId) {
        return new ResponseEntity<>(orderedItemService.orderedItemPrepared(orderedItemId), HttpStatus.OK);
    }
}
