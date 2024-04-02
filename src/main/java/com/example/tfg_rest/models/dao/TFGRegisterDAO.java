package com.example.tfg_rest.models.dao;

import com.example.tfg_rest.models.entity.TFGRegister;

import java.util.List;

public interface TFGRegisterDAO {
    void save(TFGRegister tfgRegister);

    TFGRegister findById(long id);

    List<TFGRegister> findByUserId(long id);

    List<TFGRegister> findAll();

    void update(TFGRegister tfgRegister);

    void deleteAllByUserId(long id);

    void delete(long id);
}
