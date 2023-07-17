package com.suhIT.restroManager.repository;

import com.suhIT.restroManager.model.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderingRepository extends JpaRepository<Ordering, Long> {
}
