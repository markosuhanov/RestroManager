package com.suhIT.restroManager.repository;

import com.suhIT.restroManager.model.Ordering;
import com.suhIT.restroManager.model.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderedItemRepository extends JpaRepository<OrderedItem, Long> {

    void deleteAllByOrdering(Ordering ordering);
}
