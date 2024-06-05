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
    private String name;
    private String description;
    private double price;
    private double cost;
    private boolean active;
    private String imgPath;
    private String itemType;
    private double portionSize;
    private int prepTime;

    private String allergens;


    private String itemCategoryName;
}
