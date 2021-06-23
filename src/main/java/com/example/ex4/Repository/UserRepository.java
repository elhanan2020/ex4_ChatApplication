package com.example.ex4.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserName(String userName);
    User findByUserName(String userName);
}