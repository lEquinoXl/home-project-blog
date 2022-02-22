package com.softserve.service.impl;

import com.softserve.exception.NullEntityReferenceException;
import com.softserve.model.Role;
import com.softserve.repository.RoleRepository;
import com.softserve.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role create(Role role) {
        if (role != null) return roleRepository.save(role);
        throw new NullEntityReferenceException("Role cannot be null");
    }

    @Override
    public Role readByName(String name) {
        Role role = roleRepository.getRoleByName(name);
        if(role == null){
            role = new Role(name);
            return roleRepository.save(role);
        }
        return role;
    }

    @Override
    public void delete(int id) {
        roleRepository.delete(roleRepository.getById(id));
        return;
    }
}
