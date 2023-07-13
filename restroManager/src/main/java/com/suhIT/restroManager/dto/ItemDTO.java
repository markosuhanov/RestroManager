package com.suhIT.restroManager.dto;

import com.suhIT.restroManager.model.ItemCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDTO {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Item name is required")
    private String name;
    private String description;
    @DecimalMin(value = "0.0", message = "Price must be a positive number!")
    private double price;
    @DecimalMin(value = "0.0", message = "Cost of item must be a positive number!")
    private double cost;
    @NotNull(message = "You have set existence of item!")
    private boolean active;
    private String imgPath;
//    @ManyToOne
//    @JoinColumn(name = "item_category_id")
//    @NotNull(message = "Item category is required")
    private Long itemCategoryId;
}
