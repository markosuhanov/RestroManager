package com.suhIT.restroManager.dto;

import com.suhIT.restroManager.model.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrinkItemDTO extends ItemDTO {
    private String allergens;
    private int prepTime;

}


