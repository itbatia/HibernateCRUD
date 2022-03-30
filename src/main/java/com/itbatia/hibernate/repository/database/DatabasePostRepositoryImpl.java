package com.itbatia.hibernate.repository.database;

import com.itbatia.hibernate.model.Post;
import com.itbatia.hibernate.repository.PostRepository;
import org.hibernate.*;

import static com.itbatia.hibernate.utils.HibernateUtil.getSessionFactory;

import java.util.List;

public class DatabasePostRepositoryImpl implements PostRepository {
    private final SessionFactory sessionFactory;

    public DatabasePostRepositoryImpl() {
        sessionFactory = getSessionFactory();
    }

    @Override
    public Post save(Post post) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = (Integer) session.save(post);
        post.setId(id);

        transaction.commit();
        session.close();
        return post;
    }

    @Override
    public Post getById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Post post = session.get(Post.class, id);

        transaction.commit();
        session.close();
        return post;
    }

    @Override
    public List<Post> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Post> posts = session.createQuery("FROM Post").list();

        transaction.commit();
        session.close();
        return posts;
    }

    @Override
    public Post update(Post post) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(post);

        transaction.commit();
        session.close();
        return post;
    }

    @Override
    public void deleteById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Post post = session.get(Post.class, id);
        session.delete(post);

        transaction.commit();
        session.close();
    }
}