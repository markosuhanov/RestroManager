package com.suhIT.restroManager.service.implementation;

import com.suhIT.restroManager.dto.SalaryDTO;
import com.suhIT.restroManager.exception.ActiveSalaryHasAnEndDateError;
import com.suhIT.restroManager.exception.NoSuchElementException;
import com.suhIT.restroManager.exception.SalaryNotFoundException;
import com.suhIT.restroManager.exception.UserNotFoundException;
import com.suhIT.restroManager.mapper.SalaryMapper;
import com.suhIT.restroManager.model.Salary;
import com.suhIT.restroManager.model.User;
import com.suhIT.restroManager.repository.SalaryRepository;
import com.suhIT.restroManager.repository.UserRepository;
import com.suhIT.restroManager.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalaryServiceImpl implements SalaryService {

    private final SalaryRepository salaryRepository;
    private final UserRepository userRepository;
    private final SalaryMapper salaryMapper;

    @Autowired
    public SalaryServiceImpl(SalaryRepository salaryRepository, UserRepository userRepository, SalaryMapper salaryMapper) {
        this.salaryRepository = salaryRepository;
        this.userRepository = userRepository;
        this.salaryMapper = salaryMapper;
    }


    @Override
    public SalaryDTO createSalary(SalaryDTO salaryDTO) {
        /*
        * TODO:
        *  1) ako je prva plata, onda nova
        *  2) ako vec ima, onda na nju end date je sadasnji i na novoj start date isti, a end date prazno
        * */
        Optional<List<Salary>> salaries = salaryRepository.findAllByUserId(salaryDTO.getUserId());
        if (salaries.isPresent() && salaries.get().isEmpty()) {
            // List is present but empty
            // create new salaty
        } else {
            // List is present and not empty
            // set false to active salary (deactivate salary)
            //create new salary
        }
        return null;
    }

    @Override
    public SalaryDTO updateSalary(Long id, SalaryDTO updatedSalaryDTO) {
        return null;
    }

    @Override
    public SalaryDTO getSalaryById(Long id) {
        Salary salary = salaryRepository.findById(id).orElseThrow(
                () -> new SalaryNotFoundException(HttpStatus.NOT_FOUND, "Salary with id " + id + " not found"));
        return salaryMapper.toDTO(salary);
    }

    @Override
    public List<SalaryDTO> getAllSalaries(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException((HttpStatus.NOT_FOUND),
                        "User with username " + username + " not found!"
                ));
        List<Salary> salaries = salaryRepository.findAllByUserId(user.getId()).orElseThrow(() -> new NoSuchElementException(HttpStatus.NOT_FOUND, "Salaries for user " + username + " not found." ));
        return salaries.stream().map(salaryMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public SalaryDTO getActiveSalary(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException((HttpStatus.NOT_FOUND),
                        "User with username " + username + " not found!"));
        Salary salary = salaryRepository.findByUserIdAndActiveIsTrue(user.getId()).orElseThrow(() ->  new NoSuchElementException(HttpStatus.NOT_FOUND, "Active salary for user " + username + " doesn't exists."));
        if(salary.getEndDate()!= null){
            throw new ActiveSalaryHasAnEndDateError(HttpStatus.OK, "Active salary can't have an end date.");
        }
        return salaryMapper.toDTO(salary);
    }

    @Override
    public void deactivateSalary(String username) {

    }
}
