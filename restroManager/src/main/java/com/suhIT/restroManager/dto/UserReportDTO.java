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
public class UserReportDTO {

    private UserDTO userDTO;
    private LocalDateTime dateFrom;
    private LocalDateTime dateUntil;
    private int numberOfBills;
    private double totalPricePerUser;
    private double totalCostPerUser;
    private double totalSalary;

}
