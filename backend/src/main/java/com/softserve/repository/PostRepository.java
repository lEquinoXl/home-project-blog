package com.softserve.repository;

import com.softserve.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(value = "select * from posts where author_Id =?1", nativeQuery = true)
    List<Post> getPostByAuthorId(int id);
}
