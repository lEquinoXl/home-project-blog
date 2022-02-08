package com.softserve.service;

import com.softserve.model.Tag;

import java.util.List;

public interface TagService {
    Tag create(Tag tag);
    Tag readById(int id);
    void delete(int id);
    List<Tag> getAll();
}
