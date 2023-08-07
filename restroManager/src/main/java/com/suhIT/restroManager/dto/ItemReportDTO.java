package com.suhIT.restroManager.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemReportDTO {

    private ItemDTO itemDTO;
    private LocalDateTime dateFrom;
    private LocalDateTime dateUntil;
    private int totalItemSold;
    private double totalPrice;
    private double totalCost;
    private int totalNoOfBillItemShowed;

}
