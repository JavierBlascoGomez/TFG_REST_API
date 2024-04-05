package com.example.tfg_rest.models.dao;

import com.example.tfg_rest.models.entity.TFGRegister;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TFGRegisterDAOImpl implements TFGRegisterDAO {

    private final EntityManager entityManager;

    @Autowired
    public TFGRegisterDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(TFGRegister tfgRegister) {
        entityManager.persist(tfgRegister);
    }

    @Override
    public TFGRegister findById(long id) {
        return entityManager.find(TFGRegister.class, id);
    }

    @Override
    public List<TFGRegister> findByUserId(long id) {
        try {
            Query query = entityManager.createQuery("from TFGRegister where user = :id");
            query.setParameter("id", id);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<TFGRegister> findAll() {
        Query query = entityManager.createQuery("from TFGRegister");
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteAllByUserId(long id) {
        List<TFGRegister> tfgRegister = findByUserId(id);
        entityManager.remove(tfgRegister);
    }

    @Override
    @Transactional
    public void delete(long id) {
        TFGRegister tfgRegister = findById(id);
        entityManager.remove(tfgRegister);
    }
}