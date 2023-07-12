package com.suhIT.restroManager.service;

import com.suhIT.restroManager.dto.SalaryDTO;

import java.util.List;

public interface SalaryService {
    SalaryDTO createSalary(SalaryDTO salaryDTO);

    SalaryDTO updateSalary(Long id, SalaryDTO updatedSalaryDTO); // sta cemi? postoji deactivate

    SalaryDTO getSalaryById(Long id);

    List<SalaryDTO> getAllSalaries(String username);

    SalaryDTO getActiveSalary(String username);

    void deactivateSalary(String username);

    //checkIsStartDateCorrect

    /*
    * TODO: za novu platu, treba da proveri da li je vec u tom periodu imao nekad neku platu za start date
    * */
}

