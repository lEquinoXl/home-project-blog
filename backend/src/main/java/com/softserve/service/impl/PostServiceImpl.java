package com.softserve.service.impl;

import com.softserve.exception.NullEntityReferenceException;
import com.softserve.model.Post;
import com.softserve.repository.PostRepository;
import com.softserve.service.PostService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post create(Post post) {
        if (post != null) return postRepository.save(post);
        throw new NullEntityReferenceException("Post cannot be 'null'");
    }

    @Override
    public Post readById(int id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException
                        ("Post with id " + id + " not found"));
    }

    @Override
    public Post update(Post post) {
        if (post != null) {
            readById(post.getId());
            return postRepository.save(post);
        }
        throw new NullEntityReferenceException("Post cannot be 'null'");
    }

    @Override
    public void delete(int id) {
        Post post = readById(id);
        postRepository.delete(post);
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = postRepository.findAll();
        return posts.isEmpty() ? new ArrayList<>() : posts;
    }

    @Override
    public List<Post> getPostByAuthor(int authorsId) {
        return null;
    }
}
