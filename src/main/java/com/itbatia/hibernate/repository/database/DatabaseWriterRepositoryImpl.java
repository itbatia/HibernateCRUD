package com.itbatia.hibernate.repository.database;

import com.itbatia.hibernate.model.Writer;
import com.itbatia.hibernate.repository.WriterRepository;
import org.hibernate.*;

import static com.itbatia.hibernate.utils.HibernateUtil.getSession;

import java.util.List;

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
            return (Writer) session.createQuery("FROM Writer w LEFT JOIN FETCH w.posts WHERE w.id=" +id).uniqueResult();
        }
    }

    @Override
    public List<Writer> getAll() {
        try (Session session = getSession()) {
            return session.createQuery("FROM Writer w LEFT JOIN FETCH w.posts").list();
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
