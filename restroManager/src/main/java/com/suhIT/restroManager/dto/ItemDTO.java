package com.suhIT.restroManager.dto;

import com.suhIT.restroManager.model.ItemCategory;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDTO {


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
    private String itemType;
    private double portionSize;
    private int prepTime;

    private String allergens;


    private String itemCategoryName;
}
