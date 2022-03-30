package com.itbatia.hibernate.repository.database;

import com.itbatia.hibernate.model.Writer;
import com.itbatia.hibernate.repository.WriterRepository;
import org.hibernate.*;

import static com.itbatia.hibernate.utils.HibernateUtil.getSessionFactory;

import java.util.List;

public class DatabaseWriterRepositoryImpl implements WriterRepository {
    private final SessionFactory sessionFactory;

    public DatabaseWriterRepositoryImpl() {
        sessionFactory = getSessionFactory();
    }

    @Override
    public Writer save(Writer writer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = (Integer) session.save(writer);
        writer.setId(id);

        transaction.commit();
        session.close();
        return writer;
    }

    @Override
    public Writer getById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Writer writer = session.get(Writer.class, id);

        transaction.commit();
        session.close();
        return writer;
    }

    @Override
    public List<Writer> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Writer> writers = session.createQuery("FROM Writer").list();

        transaction.commit();
        session.close();
        return writers;
    }

    @Override
    public Writer update(Writer writer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(writer);

        transaction.commit();
        session.close();
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Writer writer = session.get(Writer.class, id);
        session.delete(writer);

        transaction.commit();
        session.close();
    }
}
