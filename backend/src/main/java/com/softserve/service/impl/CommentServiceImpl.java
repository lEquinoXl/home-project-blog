package com.softserve.service.impl;

import com.softserve.exception.NullEntityReferenceException;
import com.softserve.model.Comment;
import com.softserve.repository.CommentRepository;
import com.softserve.service.CommentService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment create(Comment comment) {
        if(comment != null){
            return commentRepository.save(comment);
        }
        throw new NullEntityReferenceException("Comment cannot be 'null'");
    }

    @Override
    public Comment readById(int id) {
        return commentRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException
                        ("Comment with id " + id+" not found"));
    }

    @Override
    public Comment update(Comment comment) {
        if(comment != null){
            readById(comment.getId());
            return commentRepository.save(comment);
        }
        throw new NullEntityReferenceException("Comment cannot be 'null'");
    }

    @Override
    public void delete(int id) {
        Comment comment = readById(id);
        commentRepository.delete(comment);
    }

    @Override
    public List<Comment> getAll() {
        List<Comment> comments = commentRepository.findAll();
        return comments.isEmpty() ? new ArrayList<>() : comments;
    }
}
