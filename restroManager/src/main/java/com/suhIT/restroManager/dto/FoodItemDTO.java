package com.suhIT.restroManager.dto;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemDTO extends ItemDTO {

    private String allergens;
    private int prepTime;

}
