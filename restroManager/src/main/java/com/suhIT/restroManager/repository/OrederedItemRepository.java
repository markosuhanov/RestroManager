package com.suhIT.restroManager.repository;

import com.suhIT.restroManager.model.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrederedItemRepository extends JpaRepository<OrderedItem, Long> {
}
