package com.softserve.service.impl;

import com.softserve.exception.NullEntityReferenceException;
import com.softserve.model.Post;
import com.softserve.repository.PostRepository;
import com.softserve.repository.TagRepository;
import com.softserve.service.PostService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;


    public PostServiceImpl(PostRepository postRepository, TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
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
    public List<Post> getAll(int id, int tag_id, String tag_name, String author_name, String sort, int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(sort).ascending());
        if (id > 0) {
            return List.of(readById(id));
        }
        if (tag_id > 0) {
            return postRepository.getPostsByTags(tagRepository.findById(tag_id).get(), pageable).toList();
        }
        if (tag_name != null && !tag_name.isEmpty()) {
            return postRepository.getPostsByTags(tagRepository.getTagByName(tag_name), pageable).toList();
        }
        if (author_name != null && !author_name.isEmpty()) {
            return postRepository.getPostsByAuthorName(author_name, pageable).toList();
        }
        List<Post> posts = postRepository.findAll(pageable).toList();
        return posts.isEmpty() ? new ArrayList<>() : posts;
    }

    @Override
    public List<Post> getPostByAuthor(int authorsId) {
        return postRepository.getPostByAuthorId(authorsId);
    }
}
