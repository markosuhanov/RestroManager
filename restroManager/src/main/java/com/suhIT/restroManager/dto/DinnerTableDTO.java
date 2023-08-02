package com.suhIT.restroManager.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DinnerTableDTO {

    private Long id;
    @NotBlank(message = "Table name is necessary!")
    private String name;
    private int x;
    private int y;
    private Integer width;
    private Integer height;
    private boolean isAvailable;
}
