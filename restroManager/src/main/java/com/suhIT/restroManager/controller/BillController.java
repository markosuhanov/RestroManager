package com.suhIT.restroManager.controller;

import com.suhIT.restroManager.dto.BillDTO;
import com.suhIT.restroManager.dto.ItemDTO;
import com.suhIT.restroManager.dto.OrderingDTO;
import com.suhIT.restroManager.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bills")
public class BillController {

    private final BillService billService;

    @Autowired
    public BillController(BillService billService){
        this.billService = billService;
    }

    @PostMapping
    public ResponseEntity<BillDTO> create(@Valid @RequestBody OrderingDTO orderingDTO) {
        BillDTO billDto = billService.createBill(orderingDTO);
        return new ResponseEntity<>(billDto, HttpStatus.OK);
    }
}
