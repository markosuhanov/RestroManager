package com.suhIT.restroManager.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemCategoryDTO {

    @NotBlank(message = "Category name of item is required!")
    private String name;
    @NotNull(message = "Item activity is required")
    private boolean active;
}
