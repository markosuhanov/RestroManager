package com.suhIT.restroManager.repository;

import com.suhIT.restroManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndIdNot(String username, Long id);


}
