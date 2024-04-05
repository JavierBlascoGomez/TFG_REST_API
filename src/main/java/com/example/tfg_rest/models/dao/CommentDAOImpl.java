package com.example.tfg_rest.models.dao;

import com.example.tfg_rest.models.entity.Comment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDAOImpl implements CommentDAO {

    private final EntityManager entityManager;

    public CommentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Comment comment) {
        entityManager.persist(comment);
    }

    @Override
    public Comment findById(long id) {
        return entityManager.find(Comment.class, id);
    }

    @Override
    public List<Comment> findByTipId(long id) {
        try {
            Query query = entityManager.createQuery("from Comment where tip = :id");
            query.setParameter("id", id);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Comment> findByUserId(long id) {
        try {
            Query query = entityManager.createQuery("from Comment where user = :id");
            query.setParameter("id", id);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void deleteAllByUserId(long id) {
        List<Comment> comments = findByUserId(id);
        entityManager.remove(comments);
    }

    @Override
    public void delete(long id) {
        Comment comment = findById(id);
        entityManager.remove(comment);
    }
}
