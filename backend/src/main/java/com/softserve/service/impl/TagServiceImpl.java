package com.softserve.service.impl;

import com.softserve.exception.NullEntityReferenceException;
import com.softserve.model.Tag;
import com.softserve.repository.TagRepository;
import com.softserve.service.TagService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
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
                        () -> new EntityNotFoundException("Tag with id " + id + " not found")
                );
    }

    @Override
    public void delete(int id) {
        Tag tag = readById(id);
        tagRepository.delete(tag);

    }

    @Override
    public List<Tag> getAll(int id, String name, String sort, int num, int size) {
        Pageable pageable = PageRequest.of(num, size, Sort.by(sort).ascending());
        if (id > 0) {
            return List.of(readById(id));
        }
        if (name != null && !name.isEmpty()) {
            return tagRepository.getTagsByName(name, pageable).toList();
        }
        List<Tag> tags = tagRepository.findAll(pageable).toList();
        return tags.isEmpty() ? new ArrayList<>() : tags;
    }
}
