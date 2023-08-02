package com.suhIT.restroManager.controller;

import com.suhIT.restroManager.dto.DinnerTableDTO;
import com.suhIT.restroManager.dto.ItemDTO;
import com.suhIT.restroManager.service.DinnerTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tables")
public class DinnerTableController {

    private final DinnerTableService dinnerTableService;

    @Autowired
    public DinnerTableController(DinnerTableService dinnerTableService) {
        this.dinnerTableService = dinnerTableService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DinnerTableDTO>> getAll() {
        List<DinnerTableDTO> tables = dinnerTableService.getAllTables();
        return new ResponseEntity<>(tables, HttpStatus.OK);
    }

        @GetMapping("/allAvailable")
    public ResponseEntity<List<DinnerTableDTO>> getAllAvailable() {
        List<DinnerTableDTO> tables = dinnerTableService.getAllAvailableTables();
        return new ResponseEntity<>(tables, HttpStatus.OK);
    }

    @PatchMapping("/delete/{name}")
    public ResponseEntity<DinnerTableDTO> deleteByName(@PathVariable String name) {
        DinnerTableDTO dto = dinnerTableService.deleteByName(name);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




}
