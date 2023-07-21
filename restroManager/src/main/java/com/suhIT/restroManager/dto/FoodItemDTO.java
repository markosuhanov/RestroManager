package com.suhIT.restroManager.dto;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FoodItemDTO extends ItemDTO {

    private String allergens;
    private int prepTime;
    private double portionSize;


}
