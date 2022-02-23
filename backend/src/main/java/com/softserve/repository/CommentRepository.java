package com.softserve.repository;

import com.softserve.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query(value = "select * from comments where name =?1", nativeQuery = true)
    Page<Comment> getCommentsByName(String name, Pageable pageable);
}
