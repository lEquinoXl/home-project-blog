package com.softserve.controllers;

import com.softserve.annotations.MinimumAuthorityBlogger;
import com.softserve.annotations.OnlyAdmin;
import com.softserve.model.*;
import com.softserve.service.CommentService;
import com.softserve.service.PostService;
import com.softserve.service.RoleService;
import com.softserve.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder encoder;
    private final PostService postService;
    private final CommentService commentService;

    public UserController(UserService userService, RoleService roleService, PasswordEncoder encoder, PostService postService, CommentService commentService) {
        this.userService = userService;
        this.roleService = roleService;
        this.encoder = encoder;
        this.postService = postService;
        this.commentService = commentService;
    }

    @PostMapping("/")
    @PermitAll
    public User createUser(@RequestBody User userBody) {
        userBody.setRole(roleService.readByName("blogger"));
        userBody.setPassword(encoder.encode(userBody.getPassword()));
        return userService.create(userBody);
    }

    @GetMapping("/")
    @OnlyAdmin
    public List<User> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    @OnlyAdmin
    public User getUser(@PathVariable int id) {
        return userService.readById(id);
    }

    @PutMapping("/{id}")
    @OnlyAdmin
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        user.setId(id);
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    @OnlyAdmin
    public void removeUser(@PathVariable int id) {
        userService.delete(id);
    }

    @GetMapping("/{id}/role")
    @OnlyAdmin
    public Role getUserRole(@PathVariable int id) {
        return userService.readById(id).getRole();
    }

    @PutMapping("/{id}/role")
    @OnlyAdmin
    public User updateUserRole(@PathVariable int id, @RequestBody Role role) {
        User user = userService.readById(id);
        user.setRole(roleService.readByName(role.getName()));
        return userService.update(user);
    }

    @GetMapping("/current")
    @MinimumAuthorityBlogger
    public User getCurrentUser(Authentication authentication) {
        return userService.readByUserName(authentication.getName());
    }

    @PutMapping("/current")
    @MinimumAuthorityBlogger
    public User updateCurrentUser(Authentication authentication, @RequestBody User userBody) {
        userBody.setId(userService.readByUserName(authentication.getName()).getId());
        return userService.update(userBody);
    }

    @PutMapping("/current/password")
    @MinimumAuthorityBlogger
    public User updateCurrentUserPassword(Authentication authentication, @RequestBody ChangePassword changePassword) {
        User user = userService.readByUserName(authentication.getName());
        if(user.getPassword() == encoder.encode(changePassword.getOldPassword()))
            user.setPassword(encoder.encode(changePassword.getNewPassword()));
        return userService.update(user);
    }

    @GetMapping("/current/posts")
    @MinimumAuthorityBlogger
    public List<Post> getPostsByCurrentUser(Authentication authentication) {
        return postService.getPostByAuthor(userService.readByUserName(authentication.getName()).getId());
    }

    @GetMapping("/current/post/{id}")
    @MinimumAuthorityBlogger
    public Post getPostByCurrentUser(@PathVariable int id) {
        return postService.readById(id);
    }

    @PutMapping("/current/post/{id}")
    @MinimumAuthorityBlogger
    public Post updatePostByCurrentUser(@PathVariable int id, @RequestBody Post post) {
        post.setId(id);
        return postService.update(post);
    }

    @DeleteMapping("/current/post/{id}")
    @MinimumAuthorityBlogger
    public void removePostByCurrentUser(@PathVariable int id) {
        postService.delete(id);
        return;
    }

    @GetMapping("/current/comments")
    @MinimumAuthorityBlogger
    public List<Comment> getCommentsByCurrentUser(Authentication authentication) {
        return commentService.getAll().stream().filter(c -> c.getAuthor().getId() == userService.readByUserName(authentication.getName()).getId()).collect(Collectors.toList());
    }

    @GetMapping("/current/comments/{id}")
    @MinimumAuthorityBlogger
    public Comment getCommentByCurrentUser(@PathVariable int id) {
        return commentService.readById(id);
    }

    @PutMapping("/current/comments/{id}")
    @MinimumAuthorityBlogger
    public Comment updateCommentByCurrentUser(@PathVariable int id, @RequestBody Comment comment) {
        comment.setId(id);
        return commentService.update(comment);
    }

    @DeleteMapping("/current/comments/{id}")
    @MinimumAuthorityBlogger
    public void removeCommentByCurrentUser(@PathVariable int id) {
        commentService.delete(id);
        return;
    }
}
