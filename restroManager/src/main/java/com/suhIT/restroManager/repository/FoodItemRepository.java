package com.suhIT.restroManager.repository;

import com.suhIT.restroManager.model.FoodItem;
import com.suhIT.restroManager.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

    Optional<List<FoodItem>> findAllByActiveTrue();

    Optional<Item> findByName(String name);
}

