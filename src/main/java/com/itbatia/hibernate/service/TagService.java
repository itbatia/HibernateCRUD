package com.itbatia.hibernate.service;

import com.itbatia.hibernate.model.Tag;
import com.itbatia.hibernate.repository.TagRepository;
import com.itbatia.hibernate.repository.database.DatabaseTagRepositoryImpl;

import java.util.List;

public class TagService {
    private TagRepository tagRepository = new DatabaseTagRepositoryImpl();

    public TagService() {
    }

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag createTag(String name) {
        Tag tag = new Tag(null, name);
        return tagRepository.save(tag);
    }

    public Tag getTag(Integer id) {
        return tagRepository.getById(id);
    }

    public List<Tag> getAllTags() {
        return tagRepository.getAll();
    }

    public Tag updateTag(Integer id, String newName) {
        Tag newTag = new Tag(id, newName);
        return tagRepository.update(newTag);
    }

    public void deleteTag(Integer id) {
        tagRepository.deleteById(id);
    }
}