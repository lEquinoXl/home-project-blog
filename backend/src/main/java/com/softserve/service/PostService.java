package com.softserve.service;

import com.softserve.model.Post;

import java.util.List;

public interface PostService {
    Post create(Post post);

    Post readById(int id);

    Post update(Post post);

    void delete(int id);

    List<Post> getAll(int id, int tag_id, String tag_name, String author_name, String sort, int pageNum, int pageSize);

    List<Post> getPostByAuthor(int authorsId);
}
