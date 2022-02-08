package com.softserve.service;

import com.softserve.model.Comment;

import java.util.List;

public interface CommentService {
    Comment create(Comment comment);
    Comment readById(int id);
    Comment update(Comment comment);
    void delete(int id);
    List<Comment> getAll();
}
