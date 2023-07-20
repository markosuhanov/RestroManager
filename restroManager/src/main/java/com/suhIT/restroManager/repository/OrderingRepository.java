package com.suhIT.restroManager.repository;

import com.suhIT.restroManager.model.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderingRepository extends JpaRepository<Ordering, Long> {

    void deleteById(Long id);
}
