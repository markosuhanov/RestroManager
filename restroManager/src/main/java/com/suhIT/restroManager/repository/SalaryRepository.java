package com.suhIT.restroManager.repository;

import com.suhIT.restroManager.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SalaryRepository extends JpaRepository<Salary, Long> {

    Optional<List<Salary>> findAllByUserId(Long id);
    Optional<Salary> findByUserIdAndActiveIsTrue(Long id);

    Optional<List<Salary>> findAllByUserIdAndStartDateAfterAndEndDateBefore(Long userId, LocalDate startDate, LocalDate endDate);

}
