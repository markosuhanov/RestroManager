package com.suhIT.restroManager.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DrinkItemDTO extends ItemDTO {
    private String allergens;
    private int prepTime;

}


