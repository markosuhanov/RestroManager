package com.suhIT.restroManager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaryDTO {

    private Long id;
    @DecimalMin(value = "0.0", message = "Amount must be a positive number!")
    private double amount;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Nullable
    private LocalDate endDate;
    private Long userId;
    @NotNull(message = "Salary activity is required")
    private boolean active;
}
