package com.suhIT.restroManager.repository;

import com.suhIT.restroManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndIdNot(String username, Long id);

    @Query("SELECT u.username FROM User u WHERE u.role = 'Waiter'")
    List<String> findUsernamesByWaiterRole();

}
