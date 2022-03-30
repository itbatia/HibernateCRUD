package com.itbatia.hibernate.service;

import com.itbatia.hibernate.model.*;
import com.itbatia.hibernate.repository.WriterRepository;
import com.itbatia.hibernate.repository.database.DatabaseWriterRepositoryImpl;

import java.util.List;

public class WriterService {
    private WriterRepository writerRepository = new DatabaseWriterRepositoryImpl();

    public WriterService() {
    }

    public WriterService(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    public Writer createWriter(String name, List<Post> posts) {
        Writer writer = new Writer(null, name, posts);
        return writerRepository.save(writer);
    }

    public Writer getWriter(Integer id) {
        return writerRepository.getById(id);
    }

    public List<Writer> getAllWriters() {
        return writerRepository.getAll();
    }

    public Writer updateWriter(Writer writer) {
        return writerRepository.update(writer);
    }

    public void deleteWriter(Integer id) {
        writerRepository.deleteById(id);
    }
}
