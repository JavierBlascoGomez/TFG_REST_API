package com.example.tfg_rest.models.dao;

import com.example.tfg_rest.models.entity.User;

import java.util.List;

public interface UserDAO {
    void save(User user);

    User findById(long id);

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findAll();

    void update(User user);

    void delete(long id);
}
