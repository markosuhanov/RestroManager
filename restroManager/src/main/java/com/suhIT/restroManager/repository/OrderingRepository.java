package com.suhIT.restroManager.repository;

import com.suhIT.restroManager.model.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderingRepository extends JpaRepository<Ordering, Long> {

    void deleteById(Long id);
    Optional<Ordering> findByTable_Name(String tableName);
}
