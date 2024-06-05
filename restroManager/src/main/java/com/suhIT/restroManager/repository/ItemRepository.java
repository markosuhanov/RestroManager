package com.suhIT.restroManager.repository;

import com.suhIT.restroManager.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i WHERE TYPE(i) = DrinkItem")
    Optional<List<Item>> findAllDrinkItems();

    @Query("SELECT i FROM Item i WHERE TYPE(i) = DrinkItem AND i.active = true")
    Optional<List<Item>> findAllActiveDrinkItems();
    @Query("SELECT i FROM Item i WHERE TYPE(i) = FoodItem")
    Optional<List<Item>> findAllFoodItems();

    @Query("SELECT i FROM Item i WHERE TYPE(i) = FoodItem AND i.active = true")
    Optional<List<Item>> findAllActiveFoodItems();

    Optional<List<Item>> findAllByActiveIsTrue();
    Optional<Item> findByName(String name);

}
