package com.example.tfg_rest.models.dao;

import com.example.tfg_rest.models.entity.Comment;

import java.util.List;

public interface CommentDAO {

    void save(Comment comment);

    Comment findById(long id);

    List<Comment> findAll();

    List<Comment> findByTipId(long id);

    List<Comment> findByUserId(long id);

    void deleteAllByUserId(long id);

    void delete(long id);
}
