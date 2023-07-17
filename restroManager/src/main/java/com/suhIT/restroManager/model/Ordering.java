package com.suhIT.restroManager.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Ordering {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double price;
    private double cost;


    @OneToMany(mappedBy = "ordering", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderedItem> orderedItems;

    @OneToOne
    private DinnerTable table;


    @ManyToOne
    private User waiter;


    private boolean isPlaced;
}
