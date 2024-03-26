package models.dao;

import models.entity.User;

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
