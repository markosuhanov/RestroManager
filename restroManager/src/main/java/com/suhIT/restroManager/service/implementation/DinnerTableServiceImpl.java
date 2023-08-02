package com.suhIT.restroManager.service.implementation;

import com.suhIT.restroManager.dto.DinnerTableDTO;
import com.suhIT.restroManager.exception.DinnerTableNotFoundException;
import com.suhIT.restroManager.exception.TableNotFoundException;
import com.suhIT.restroManager.mapper.DinnerTableMapper;
import com.suhIT.restroManager.model.DinnerTable;
import com.suhIT.restroManager.repository.DinnerTableRepository;
import com.suhIT.restroManager.service.DinnerTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DinnerTableServiceImpl implements DinnerTableService {

    private DinnerTableRepository dinnerTableRepository;
    private DinnerTableMapper dinnerTableMapper;

    @Autowired
    public DinnerTableServiceImpl(DinnerTableRepository dinnerTableRepository, DinnerTableMapper dinnerTableMapper) {
        this.dinnerTableRepository = dinnerTableRepository;
        this.dinnerTableMapper = dinnerTableMapper;
    }

    @Override
    public List<DinnerTableDTO> getAllTables() {
        return this.dinnerTableRepository.findAll().stream().map(dinnerTableMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<DinnerTableDTO> getAllAvailableTables() {
        List<DinnerTable> dinnerTables = dinnerTableRepository.findAllByIsAvailableIsTrue()
                .orElseThrow(() -> new TableNotFoundException(HttpStatus.NOT_FOUND, "Available tables not found!"));
        return dinnerTables.stream().map(dinnerTableMapper::toDTO).collect(Collectors.toList());
//        return this.dinnerTableRepository.findAllByIsAvailableIsTrue().stream().map(dinnerTableMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public DinnerTableDTO deleteByName(String name) {
        DinnerTable dinnerTable = dinnerTableRepository.findByName(name)
                .orElseThrow(() -> new DinnerTableNotFoundException(HttpStatus.NOT_FOUND,
                "Table with name + " + name + " not found!"
        ));
        dinnerTable.setAvailable(false);
        return dinnerTableMapper.toDTO(dinnerTableRepository.save(dinnerTable));
    }
}
