package com.suhIT.restroManager.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OrderedItem {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @NotNull(message = "Item has to be choosen!")
    private Item item;

    @NotNull(message = "Item prepared status has to be set!")
    private boolean prepared;


    @ManyToOne
    @JoinColumn(name = "ordering_id")
    private Ordering ordering;

    private boolean active;
}
