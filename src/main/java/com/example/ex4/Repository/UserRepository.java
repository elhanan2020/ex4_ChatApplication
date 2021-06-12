package com.example.ex4.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/* default scope of this Bean is "singleton" */
public interface UserRepository extends JpaRepository<User, Long> {

   // add here the queries you may need - in addition to CRUD operations
    void deleteUserByUserName(String s);
    boolean existsByUserName(String userName);
    User findByUserName(String userName);
    User findFirst1ByOrderByIdDesc();
    /* List<User> findUserByUserName(String userName);
    List<User> findByEmail(String email);
    List<User> findByUserNameAndEmail(String userName, String email);
    List<User> findFirst10ByOrderByUserNameDesc(); */


}