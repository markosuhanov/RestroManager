package com.suhIT.restroManager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillDTO {
    private UUID id;
    private UUID waiter;
    private double price;
    private double cost;
    private Date createdAt;
    private boolean paid;
}