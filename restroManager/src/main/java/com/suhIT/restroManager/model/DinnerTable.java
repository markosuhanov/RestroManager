package com.suhIT.restroManager.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class DinnerTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Table name is necessary!")
    private String name;
    private int x;
    private int y;



}
