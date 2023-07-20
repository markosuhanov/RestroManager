package com.suhIT.restroManager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String waiter;

    @ManyToMany
    private List<Item> items;

    private double price;
    private double cost;
    private LocalDateTime createdAt;
    private boolean paid;
}
