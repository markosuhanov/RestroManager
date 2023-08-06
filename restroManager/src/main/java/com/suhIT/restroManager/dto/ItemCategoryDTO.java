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
    private Long id;
    private String name;
    private boolean active;
}
