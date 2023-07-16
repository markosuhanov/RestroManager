package com.suhIT.restroManager.model;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("drink")
public class DrinkItem extends Item{

    private String allergens;
    private int prepTime;

}
