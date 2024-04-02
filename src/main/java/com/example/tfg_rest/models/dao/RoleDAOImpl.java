package com.example.tfg_rest.models.dao;

import com.example.tfg_rest.models.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOImpl implements RoleDAO {

    private final EntityManager entityManager;

    @Autowired
    public RoleDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Role findByRoleName(String roleName) {
        Query query = entityManager.createQuery("SELECT r FROM Role r WHERE r.name = :roleName");

        query.setParameter("roleName", roleName);

        return (Role) query.getSingleResult();
    }
}