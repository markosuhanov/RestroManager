package com.suhIT.restroManager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportRequestDTO {

    private LocalDateTime dateFrom;
    private LocalDateTime dateUntil;
    private ItemDTO itemDTO;
    private UserDTO userDTO;
}
