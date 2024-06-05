package com.suhIT.restroManager.mapper;

import com.suhIT.restroManager.dto.SalaryDTO;
import com.suhIT.restroManager.exception.SalaryNotFoundException;
import com.suhIT.restroManager.exception.UserNotFoundException;
import com.suhIT.restroManager.model.Salary;
import com.suhIT.restroManager.model.User;
import com.suhIT.restroManager.repository.SalaryRepository;
import com.suhIT.restroManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class SalaryMapper implements Mapper<Salary, SalaryDTO> {

    private final SalaryRepository salaryRepository;
    private final UserRepository userRepository;

    @Autowired
    public SalaryMapper(SalaryRepository salaryRepository, UserRepository userRepository) {
        this.salaryRepository = salaryRepository;
        this.userRepository = userRepository;
    }


    public SalaryDTO toDTO(Salary salary) {
        return SalaryDTO.builder().id(salary.getId()).amount(salary.getAmount()).startDate(salary.getStartDate())
                .endDate(salary.getEndDate()).userId(salary.getUser().getId()).active(salary.isActive()).build();
    }

    public Salary toEntity(SalaryDTO salaryDTO) {
        Salary salary = salaryRepository.findById(salaryDTO.getId())
                .orElseThrow(() -> new SalaryNotFoundException(HttpStatus.NOT_FOUND, "Salary not found!"));
        salary.setId(salaryDTO.getId());
        salary.setStartDate(salaryDTO.getStartDate());
        salary.setEndDate(salaryDTO.getEndDate());
        salary.setAmount(salaryDTO.getAmount());
        User user = userRepository.findById(salaryDTO.getUserId()).orElseThrow(
                () -> new UserNotFoundException(HttpStatus.NOT_FOUND,
                        "User with id: " + salaryDTO.getUserId() + " not found!"
                ));
        salary.setUser(user);
        return salary;
    }

}
