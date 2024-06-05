package com.suhIT.restroManager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "item_type")
public class Item {

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
    @ManyToOne
    @JoinColumn(name = "item_category_id")
    @NotNull(message = "Item category is required")
    private ItemCategory itemCategory;
}
