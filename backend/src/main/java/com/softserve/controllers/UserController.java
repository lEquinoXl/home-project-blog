package com.softserve.controllers;

import com.softserve.model.Role;
import com.softserve.model.User;
import com.softserve.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public User createUser(@RequestBody User userBody) {
//        userBody.setRole(new Role("blogger"));
        return userService.create(userBody);
    }

    @GetMapping("/")
    public List<User> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return userService.readById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable int id) {
        userService.delete(id);
    }

    @GetMapping("/{id}/role")
    public Role getUserRole(@PathVariable int id) {
        return new Role(userService.readById(id).getRole());
    }

    @PutMapping("/{id}/role")
    public User updateUserRole(@PathVariable int id, @RequestBody Role role) {
        User user = userService.readById(id);
        user.setRole(role.getName());
        return userService.update(user);
    }

    @GetMapping("/current")
    public String getCurrentUser() {
        return "#/components/responses/UserResponse";
    }

    @PutMapping("/current")
    public String updateCurrentUser(@RequestBody User userBody) {
        return "user";
    }

    @PutMapping("/current/password")
    public String updateCurrentUserPassword() {
        return "pass";
    }

    @GetMapping("/current/posts")
    public String getPostsByCurrentUser() {
        return "posts";
    }

    @GetMapping("/current/post/{id}")
    public String getPostByCurrentUser() {
        return "posts";
    }

    @PutMapping("/current/post/{id}")
    public String updatePostByCurrentUser() {
        return "posts";
    }

    @DeleteMapping("/current/post/{id}")
    public String removePostByCurrentUser() {
        return "posts";
    }

    @GetMapping("/current/comments")
    public String getCommentsByCurrentUser() {
        return "comm";
    }

    @GetMapping("/current/comments/{id}")
    public String getCommentByCurrentUser() {
        return "comm";
    }

    @PutMapping("/current/comments/{id}")
    public String updateCommentByCurrentUser() {
        return "comm";
    }

    @DeleteMapping("/current/comments/{id}")
    public String removeCommentByCurrentUser() {
        return "comm";
    }
}
