package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findById(Long Id);
    Optional<User> findByUsername(String username);
    boolean existsByEmailAndIdNot(String email,Long Id);
    boolean existsByUsername(String email);
    boolean existsByEmail(String username);
    void deleteById(Long Id);
}
