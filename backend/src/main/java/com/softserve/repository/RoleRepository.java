package com.softserve.repository;

import com.softserve.model.Role;
import com.softserve.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "select * from roles where name =?1", nativeQuery = true)
    Role getRoleByName(String name);
}
