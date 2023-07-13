package com.suhIT.restroManager.repository;

import com.suhIT.restroManager.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {


}
