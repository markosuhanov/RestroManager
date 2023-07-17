package com.suhIT.restroManager.dto;


import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderingDTO {

    @NotNull(message = "Table has to be selected!")
    private String tableName;

//    @NotNull(message = "Waiter username has to be selected!")
//    private String waiterUsername;

    @NotNull(message = "There are no items selected in order!")
    private List<OrderedItemDTO> orderedItemDTOS;

    private double price;

    private boolean isPlaced;
}
