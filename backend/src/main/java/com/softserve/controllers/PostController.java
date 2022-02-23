package com.softserve.controllers;

import com.softserve.annotations.MinimumAuthorityBlogger;
import com.softserve.annotations.MinimumAuthorityModerator;
import com.softserve.model.Comment;
import com.softserve.model.Post;
import com.softserve.service.CommentService;
import com.softserve.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @MinimumAuthorityBlogger
    public Post createPost(@RequestBody Post post) {
        return postService.create(post);
    }

    @GetMapping("/")
    public List<Post> getPosts(@RequestParam(required = false, defaultValue = "0") int id,
                               @RequestParam(required = false, defaultValue = "0") int tag_id,
                               @RequestParam(required = false) String tag_name,
                               @RequestParam(required = false) String authors_name,
                               @RequestParam(defaultValue = "id") String posts_sort,
                               @RequestParam(defaultValue = "0") int pageNum,
                               @RequestParam(defaultValue = "10") int pageSize) {
        return postService.getAll(id, tag_id, tag_name, authors_name, posts_sort, pageNum, pageSize);
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable int id) {
        return postService.readById(id);
    }

    @PutMapping("/{id}")
    @MinimumAuthorityModerator
    public Post updatePost(@PathVariable int id, @RequestBody Post post) {
        return postService.update(post);
    }

    @DeleteMapping("/{id}")
    @MinimumAuthorityModerator
    public void removePost(@PathVariable int id) {
        postService.delete(id);
    }

    @PostMapping("/{id}/comments")
    @MinimumAuthorityBlogger
    public Comment createComment(@PathVariable int id, @RequestBody Comment comment) {
        comment.setPost(postService.readById(id));
        return commentService.create(comment);
    }

    @GetMapping("/{post_id}/comments")
    public List<Comment> getComments(@PathVariable int post_id,
                                     @RequestParam(required = false) int id,
                                     @RequestParam(required = false) String authors_name,
                                     @RequestParam(defaultValue = "id") String comments_sort,
                                     @RequestParam(defaultValue = "0") int pageNum,
                                     @RequestParam(defaultValue = "10") int pageSize) {

        return commentService.getAll(id, authors_name, comments_sort, pageNum, pageSize);
    }

    @GetMapping("/{postId}/comments/{id}")
    public Comment getComment(@PathVariable int id) {
        return commentService.readById(id);
    }

    @PutMapping("/{postId}/comments/{id}")
    @MinimumAuthorityModerator
    public Comment updateComment(@PathVariable int id, @PathVariable int postId, @RequestBody Comment comment) {
        return commentService.update(comment);
    }

    @DeleteMapping("/{postId}/comments/{id}")
    @MinimumAuthorityModerator
    public void removeComment(@PathVariable int id) {
        commentService.delete(id);
    }
}
