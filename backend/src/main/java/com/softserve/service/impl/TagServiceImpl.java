package com.softserve.service.impl;

import com.softserve.exception.NullEntityReferenceException;
import com.softserve.model.Tag;
import com.softserve.repository.TagRepository;
import com.softserve.service.TagService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag create(Tag tag) {
        if (tag != null) {
            return tagRepository.save(tag);
        }
        throw new NullEntityReferenceException("Tag cannot be 'null'");
    }

    @Override
    public Tag readById(int id) {
        return tagRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("User with id " + id + " not found")
                );
    }

    @Override
    public void delete(int id) {
        Tag tag = readById(id);
        tagRepository.delete(tag);

    }

    @Override
    public List<Tag> getAll() {
        List<Tag> tags = tagRepository.findAll();
        return tags.isEmpty()? new ArrayList<>() : tags;
    }
}
