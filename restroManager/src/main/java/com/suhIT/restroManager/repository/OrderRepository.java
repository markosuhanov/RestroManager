package com.suhIT.restroManager.repository;

import com.suhIT.restroManager.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
