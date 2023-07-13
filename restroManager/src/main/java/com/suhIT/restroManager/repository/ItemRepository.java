package com.suhIT.restroManager.repository;

import com.suhIT.restroManager.model.Item;
import com.suhIT.restroManager.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

//    Optional<List<Item>> findAllByActiveTrue();
}
