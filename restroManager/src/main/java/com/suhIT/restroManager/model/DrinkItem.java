package com.suhIT.restroManager.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.Duration;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class DrinkItem extends Item{

    private String allergens;
    private int prepTime;

//    @ManyToOne
//    @JoinColumn(name = "item_category_id")
//    private ItemCategory itemCategory;
}
