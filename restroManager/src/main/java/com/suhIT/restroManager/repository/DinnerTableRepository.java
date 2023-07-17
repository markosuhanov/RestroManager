package com.suhIT.restroManager.repository;

import com.suhIT.restroManager.model.DinnerTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DinnerTableRepository extends JpaRepository<DinnerTable, Long> {

    Optional<DinnerTable> findByName(String name);

}
