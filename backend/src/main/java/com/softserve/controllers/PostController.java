package com.softserve.controllers;

import com.softserve.model.Comment;
import com.softserve.model.Post;
import com.softserve.service.CommentService;
import com.softserve.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @PostMapping("/")
    public Post createPost(@RequestBody Post post) {
        return postService.create(post);
    }

    @GetMapping("/")
    public List<Post> getPosts() {
        return postService.getAll();
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable int id){
        return postService.readById(id);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable int id, @RequestBody Post post){
        return postService.update(post);
    }

    @DeleteMapping("/{id}")
    public void removePost(@PathVariable int id){
        postService.delete(id);
    }

    @GetMapping("/{id}/comments")
    public List<Comment> getComments(@PathVariable int id){
        return commentService.getAll()
                .stream().filter(x-> x.getPost().getId() == id)
                .collect(Collectors.toList());
    }

    @PostMapping("/{id}/comments")
    public Comment createComment(@PathVariable int id, @RequestBody Comment comment){
        comment.setPost(postService.readById(id));
        return commentService.create(comment);
    }

    @GetMapping("/{postId}/comments/{id}")
    public Comment getComment(@PathVariable int id){
        return commentService.readById(id);
    }

    @PutMapping("/{postId}/comments/{id}")
    public Comment updateComment(@PathVariable int id, @PathVariable int postId, @RequestBody Comment comment){
        return commentService.update(comment);
    }

    @DeleteMapping("/{postId}/comments/{id}")
    public void removeComment(@PathVariable int id){
        commentService.delete(id);
    }
}
