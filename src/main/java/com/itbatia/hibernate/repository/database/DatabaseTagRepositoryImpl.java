package com.itbatia.hibernate.repository.database;

import com.itbatia.hibernate.model.Tag;
import com.itbatia.hibernate.repository.TagRepository;
import org.hibernate.*;

import static com.itbatia.hibernate.utils.HibernateUtil.getSessionFactory;

import java.util.List;

public class DatabaseTagRepositoryImpl implements TagRepository {

    private final SessionFactory sessionFactory;

    public DatabaseTagRepositoryImpl() {
        sessionFactory = getSessionFactory();
    }

    @Override
    public Tag save(Tag tag) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Tag checkedTag = checkForUnique(tag);
        if (checkedTag == null) {
            Integer id = (Integer) session.save(tag);
            tag.setId(id);
        } else {
            tag = checkedTag;
        }

        transaction.commit();
        session.close();
        return tag;
    }

    private Tag checkForUnique(Tag tag) {
        if (getAll().size() > 0) {
            for (Tag t : getAll()) {
                if (t.getName().equals(tag.getName())) {
                    return t;
                }
            }
        }
        return null;
    }

    @Override
    public Tag getById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Tag tag = session.get(Tag.class, id);

        transaction.commit();
        session.close();
        return tag;
    }

    @Override
    public List<Tag> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Tag> tags = session.createQuery("FROM Tag").list();

        transaction.commit();
        session.close();
        return tags;
    }

    @Override
    public Tag update(Tag tag) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(tag);

        transaction.commit();
        session.close();
        return tag;
    }

    @Override
    public void deleteById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Tag tag = session.load(Tag.class, id);
        session.delete(tag);

        transaction.commit();
        session.close();
    }
}
