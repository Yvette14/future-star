package com.thoughtworks.repository;

import com.thoughtworks.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findUsersByAge(int age);

    User findUserByUsername(String username);

    User findUserByUsernameAndPassword(String username, String password);

}
