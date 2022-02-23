package com.softserve.service.impl;

import com.softserve.exception.NullEntityReferenceException;
import com.softserve.model.User;
import com.softserve.repository.UserRepository;
import com.softserve.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        if (user != null) {
            return userRepository.save(user);
        }
        throw new NullEntityReferenceException("User cannot be 'null'");
    }

    @Override
    public User readById(int id) {
        return userRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("User with id " + id + " not found")
                );
    }

    @Override
    public User readByUserName(String username) {
        return userRepository.getUserByName(username).orElseThrow(
                () -> new UsernameNotFoundException("User with username " + username + " not found")
        );
    }

    @Override
    public User update(User user) {
        if (user != null) {
            readById(user.getId());
            return userRepository.save(user);
        }
        throw new NullEntityReferenceException("User cannot be 'null'");
    }

    @Override
    public void delete(int id) {
        User user = readById(id);
        userRepository.delete(user);
    }

    @Override
    public List<User> getAll(int id, String name, String sort, int num, int size) {
        Pageable pageable = PageRequest.of(num, size, Sort.by(sort).ascending());
        if (id > 0) {
            return List.of(readById(id));
        }
        if (name != null && !name.isEmpty()) {
            return userRepository.getUsersByName(name, pageable).toList();
        }
        List<User> users = userRepository.findAll(pageable).toList();
        return users.isEmpty() ? new ArrayList<>() : users;
    }
}
