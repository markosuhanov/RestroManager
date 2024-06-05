package com.suhIT.restroManager.controller;

import com.suhIT.restroManager.dto.SalaryDTO;
import com.suhIT.restroManager.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/salaries")
public class SalaryController {

    private final SalaryService salaryService;

    @Autowired
    public SalaryController(SalaryService salaryService){
        this.salaryService = salaryService;
    }


    @GetMapping("{username}")
    public ResponseEntity<List<SalaryDTO>> getAll(@PathVariable("username") String username) {
        List<SalaryDTO> salaries = salaryService.getAllSalaries(username);
        return new ResponseEntity<>(salaries, HttpStatus.OK);
    }

//    @GetMapping("id/{id}")
//    public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
//        SalaryDTO salaryDTO = salaryService.getSalaryById(id);
//        return new ResponseEntity<>(salaryDTO, HttpStatus.OK);
//    }

    @GetMapping("active/{username}")
    public ResponseEntity<Object> getActive(@PathVariable("username") String username) {
        SalaryDTO salaryDTO = salaryService.getActiveSalary(username);
        return new ResponseEntity<>(salaryDTO, HttpStatus.OK);
    }




}
