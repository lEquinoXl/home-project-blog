package com.softserve.controllers;

import com.softserve.annotations.MinimumAuthorityBlogger;
import com.softserve.annotations.MinimumAuthorityModerator;
import com.softserve.model.Tag;
import com.softserve.service.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    @MinimumAuthorityBlogger
    public Tag createTag(@RequestBody Tag tag) {
        return tagService.create(tag);
    }

    @GetMapping("/")
    public List<Tag> getTags(@RequestParam(required = false, defaultValue = "0") int id,
                             @RequestParam(required = false) String name,
                             @RequestParam(defaultValue = "id") String sort,
                             @RequestParam(defaultValue = "0") int pageNum,
                             @RequestParam(defaultValue = "10") int pageSize) {
        return tagService.getAll(id, name, sort, pageNum, pageSize);
    }

    @GetMapping("/{id}")
    public Tag getTag(@PathVariable int id) {
        return tagService.readById(id);
    }

    @DeleteMapping("/{id}")
    @MinimumAuthorityModerator
    public void removeTag(@PathVariable int id) {
        tagService.delete(id);
    }
}
