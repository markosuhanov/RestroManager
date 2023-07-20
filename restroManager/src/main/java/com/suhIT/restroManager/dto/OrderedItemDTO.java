package com.suhIT.restroManager.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderedItemDTO {

    @NotNull(message = "Ordered item has to be chosen! ")
    private ItemDTO item;
    private boolean prepared;
    private boolean active;

}
