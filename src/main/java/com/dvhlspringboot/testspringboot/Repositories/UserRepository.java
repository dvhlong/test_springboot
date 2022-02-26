package com.dvhlspringboot.testspringboot.Repositories;

import java.util.List;

import com.dvhlspringboot.testspringboot.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long>{
    List<User> findByUsername(String username);
    List<User> findByUsernameAndPassword(String username, String password);
    List<User> findByUsernameContaining(String key);
}
