package com.suhIT.restroManager.controller;

import com.suhIT.restroManager.dto.OrderingDTO;
import com.suhIT.restroManager.exception.OrderingNotFoundException;
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

    @PostMapping
    public ResponseEntity<OrderingDTO> create(@Valid @RequestBody OrderingDTO orderingDTO) {
        return new ResponseEntity<>(orderingService.createOrder(orderingDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderingDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(orderingService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        try {
            orderingService.deleteById(id);
        } catch (Exception e) {
            throw new OrderingNotFoundException(HttpStatus.NOT_FOUND, "Order with ID:" + id + " not found!");
        }
        return new ResponseEntity<>("Order with ID " + id + "successfully deleted!", HttpStatus.OK);
    }


}
