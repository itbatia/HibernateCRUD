package com.itbatia.hibernate.repository.database;

import com.itbatia.hibernate.model.Tag;
import com.itbatia.hibernate.repository.TagRepository;
import org.hibernate.*;

import static com.itbatia.hibernate.utils.HibernateUtil.getSession;

import java.util.List;

public class DatabaseTagRepositoryImpl implements TagRepository {

    @Override
    public Tag save(Tag tag) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            Integer id = (Integer) session.save(tag);
            tag.setId(id);
            transaction.commit();
            return tag;
        }
    }

    @Override
    public Tag getById(Integer id) {
        try (Session session = getSession()) {
            return session.get(Tag.class, id);
        }
    }

    @Override
    public List<Tag> getAll() {
        try (Session session = getSession()) {
            return session.createQuery("SELECT t FROM Tag t ORDER BY t.id ASC", Tag.class).getResultList();
        }
    }

    @Override
    public Tag update(Tag tag) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(tag);
            transaction.commit();
            return tag;
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            Tag tag = session.get(Tag.class, id);
            session.delete(tag);
            transaction.commit();
        }
    }
}