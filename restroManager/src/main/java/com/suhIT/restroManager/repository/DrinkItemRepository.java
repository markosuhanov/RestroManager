package com.suhIT.restroManager.repository;

import com.suhIT.restroManager.model.DrinkItem;
import com.suhIT.restroManager.model.FoodItem;
import com.suhIT.restroManager.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DrinkItemRepository extends JpaRepository<DrinkItem, Long> {

    Optional<List<DrinkItem>> findAllByActiveTrue();

    Optional<Item> findByName(String name);
}
