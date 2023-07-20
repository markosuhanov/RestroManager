package com.suhIT.restroManager.repository;

import com.suhIT.restroManager.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
