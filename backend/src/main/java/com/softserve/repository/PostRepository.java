package com.softserve.repository;

import com.softserve.model.Post;
import com.softserve.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(value = "select * from posts where author_Id =?1", nativeQuery = true)
    List<Post> getPostByAuthorId(int id);

    @Query(value = "select * from posts where author_name =?1", nativeQuery = true)
    Page<Post> getPostsByAuthorName(String name, Pageable pageable);

    Page<Post> getPostsByTags(Tag tag, Pageable pageable);

    @Override
    Page<Post> findAll(Pageable pageable);
}
