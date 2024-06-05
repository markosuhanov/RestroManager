package com.suhIT.restroManager.mapper;

import com.suhIT.restroManager.dto.DinnerTableDTO;
import com.suhIT.restroManager.model.DinnerTable;
import org.springframework.stereotype.Component;

@Component
public class DinnerTableMapper implements Mapper<DinnerTable, DinnerTableDTO> {
    @Override
    public DinnerTable toEntity(DinnerTableDTO dinnerTableDto) {
        return null;
    }

    @Override
    public DinnerTableDTO toDTO(DinnerTable dinnerTable) {
        return DinnerTableDTO.builder()
                .id(dinnerTable.getId())
                .name(dinnerTable.getName())
                .x(dinnerTable.getX())
                .y(dinnerTable.getY())
                .width(dinnerTable.getWidth())
                .height(dinnerTable.getHeight())
                .isAvailable(dinnerTable.isAvailable())
                .build();
    }
}
