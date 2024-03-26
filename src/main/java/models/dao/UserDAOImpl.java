package models.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private final EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByUsername(String username) {
        try {
            Query query = entityManager.createQuery("from User where username = :username");
            query.setParameter("username", username);
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User findByEmail(String email) {
        try {
            Query query = entityManager.createQuery("from User where email = :email");
            query.setParameter("email", email);
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<User> findAll () {
        Query query = entityManager.createQuery("from User");
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update (User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void delete (long id) {
        User user = findById(id);
        entityManager.remove(user);
    }
}
