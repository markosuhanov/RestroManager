package com.suhIT.restroManager.repository;

import com.suhIT.restroManager.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {

    @Query("SELECT b FROM Bill b WHERE (:dateFrom IS NULL OR b.createdAt >= :dateFrom) AND (:dateUntil IS NULL OR b.createdAt <= :dateUntil)")
    List<Bill> findBillsByDateRange(LocalDateTime dateFrom, LocalDateTime dateUntil);
}
