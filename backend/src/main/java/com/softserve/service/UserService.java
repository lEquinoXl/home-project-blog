package com.softserve.service;

import java.util.List;
import com.softserve.model.User;

public interface UserService {
    User create(User user);
    User readById(int id);
    User readByUserName(String username);
    User update(User user);
    void delete(int id);
    List<User> getAll();
}
