package com.softserve.service;

import com.softserve.model.Role;

public interface RoleService {
    Role create(Role role);
    Role readByName(String name);
    void delete(int id);

}
