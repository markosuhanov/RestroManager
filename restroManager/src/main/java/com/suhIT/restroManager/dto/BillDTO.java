package com.suhIT.restroManager.dto;

import com.suhIT.restroManager.model.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillDTO {
    private Long id;
    private String waiter;
    private List<ItemDTO> items;
    private double price;
    private double cost;
    private LocalDateTime createdAt;
    private boolean paid;
}