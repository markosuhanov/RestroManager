package com.suhIT.restroManager.repository;

import com.suhIT.restroManager.model.Item;
import com.suhIT.restroManager.model.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {

    Optional<List<ItemCategory>> findAllByActiveIsTrue();
    Optional<ItemCategory> findByName(String name);
    Optional<ItemCategory> findByNameAndActiveIsTrue(String name);
}
