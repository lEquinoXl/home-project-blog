package com.softserve.repository;

import com.softserve.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    @Override
    Page<Tag> findAll(Pageable pageable);

    @Query(value = "select * from tags where name =?1", nativeQuery = true)
    Page<Tag> getTagsByName(String name, Pageable pageable);

    Tag getTagByName(String name);
}
