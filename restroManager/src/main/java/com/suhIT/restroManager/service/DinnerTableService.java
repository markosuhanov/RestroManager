package com.suhIT.restroManager.service;

import com.suhIT.restroManager.dto.DinnerTableDTO;
import com.suhIT.restroManager.model.DinnerTable;

import java.util.List;

public interface DinnerTableService {
    List<DinnerTableDTO> getAllTables();
    List<DinnerTableDTO> getAllAvailableTables();

    DinnerTableDTO deleteByName(String name);
    DinnerTableDTO setAsBusy(String name);

    DinnerTableDTO setAsFree(String name);


}
