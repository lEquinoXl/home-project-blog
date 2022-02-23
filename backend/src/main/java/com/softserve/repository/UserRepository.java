package com.softserve.repository;

import com.softserve.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select * from users where email =?1", nativeQuery = true)
    User getUserByEmail(String email);

    @Query(value = "select * from users where name =?1", nativeQuery = true)
    Optional<User> getUserByName(String name);

    Page<User> getUsersByName(String name, Pageable pageable);

    @Override
    Page<User> findAll(Pageable pageable);
}
