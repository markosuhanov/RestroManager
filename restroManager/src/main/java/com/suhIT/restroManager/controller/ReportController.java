package com.suhIT.restroManager.controller;

import com.suhIT.restroManager.dto.ItemReportDTO;
import com.suhIT.restroManager.dto.ReportRequestDTO;
import com.suhIT.restroManager.dto.UserReportDTO;
import com.suhIT.restroManager.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/item")
    public ResponseEntity<ItemReportDTO> getItemReport(@RequestBody() ReportRequestDTO reportRequest) {
        return new ResponseEntity<>(reportService.createForItem(reportRequest), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<UserReportDTO> getUserReport(@RequestBody() ReportRequestDTO reportRequest) {
        return new ResponseEntity<>(reportService.createForUser(reportRequest), HttpStatus.OK);
    }


}
