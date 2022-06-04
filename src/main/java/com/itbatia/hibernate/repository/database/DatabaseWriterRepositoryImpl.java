package com.itbatia.hibernate.repository.database;

import com.itbatia.hibernate.model.Writer;
import com.itbatia.hibernate.repository.WriterRepository;
import org.hibernate.*;

import static com.itbatia.hibernate.utils.HibernateUtil.getSession;

import java.util.List;
import java.util.stream.Collectors;

public class DatabaseWriterRepositoryImpl implements WriterRepository {

    @Override
    public Writer save(Writer writer) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            Integer id = (Integer) session.save(writer);
            writer.setId(id);
            transaction.commit();
            return writer;
        }
    }

    @Override
    public Writer getById(Integer id) {
        try (Session session = getSession()) {
            Writer writer = session.get(Writer.class, id);
            if (writer != null) {
                writer.getPosts().forEach(post -> Hibernate.initialize(post.getTags()));
            }
            return writer;
        }
    }

    @Override
    public List<Writer> getAll() {
        try (Session session = getSession()) {
            List<Writer> writers = session.createQuery("SELECT w FROM Writer w LEFT JOIN FETCH w.posts ORDER BY w.id ASC", Writer.class)
                    .getResultList().stream().distinct().collect(Collectors.toList());
            writers.forEach(writer -> writer.getPosts().forEach(post -> Hibernate.initialize(post.getTags())));
            return writers;
        }
    }

    @Override
    public Writer update(Writer writer) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(writer);
            transaction.commit();
            return writer;
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            Writer writer = session.get(Writer.class, id);
            session.delete(writer);
            transaction.commit();
        }
    }
}