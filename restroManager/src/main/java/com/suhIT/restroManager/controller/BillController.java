package com.suhIT.restroManager.controller;

import com.suhIT.restroManager.dto.BillDTO;
import com.suhIT.restroManager.dto.ItemDTO;
import com.suhIT.restroManager.dto.OrderingDTO;
import com.suhIT.restroManager.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/bills")
public class BillController {

    private final BillService billService;

    @Autowired
    public BillController(BillService billService){
        this.billService = billService;
    }

    @PostMapping
    public ResponseEntity<BillDTO> create(@RequestBody OrderingDTO orderingDTO) {
        System.out.println("AAA");
        System.out.println(orderingDTO.toString());
        BillDTO billDto = billService.createBill(orderingDTO);
        return new ResponseEntity<>(billDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BillDTO>> getAll() {
        List<BillDTO> bills = billService.getAll();
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    @GetMapping("/{billId}")
    public ResponseEntity<BillDTO> getById( @PathVariable("billId") Long billId) {
        BillDTO billDTO = billService.getById(billId);
        return new ResponseEntity<>(billDTO, HttpStatus.OK);
    }
}
