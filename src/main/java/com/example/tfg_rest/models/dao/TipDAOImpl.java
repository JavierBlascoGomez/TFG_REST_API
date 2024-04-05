package com.example.tfg_rest.models.dao;

import com.example.tfg_rest.models.entity.Tip;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipDAOImpl implements TipDAO {

    private final EntityManager entityManager;

    public TipDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Tip tip) {
        entityManager.persist(tip);
    }

    @Override
    public Tip findById(long id) {
        return entityManager.find(Tip.class, id);
    }

    @Override
    public List<Tip> findAll() {
        Query query = entityManager.createQuery("from Tip");
        return query.getResultList();
    }

    @Override
    public void update(Tip tip) {
        entityManager.merge(tip);
    }

    @Override
    public void delete(long id) {
        entityManager.remove(findById(id));
    }
}
