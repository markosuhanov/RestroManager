package com.suhIT.restroManager.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ordering_id", referencedColumnName = "id")
    private List<OrderedItem> orderedItems;

    @OneToOne
    private DinnerTable table;


    @ManyToOne
    private User waiter;


    private boolean isPlaced;
}
