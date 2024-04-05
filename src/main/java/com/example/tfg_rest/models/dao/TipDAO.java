package com.example.tfg_rest.models.dao;

import com.example.tfg_rest.models.entity.Tip;

import java.util.List;

public interface TipDAO {

    void save(Tip tip);

    Tip findById(long id);

    List<Tip> findAll();

    void update(Tip tip);

    void delete(long id);
}
