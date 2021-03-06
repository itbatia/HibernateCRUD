package com.itbatia.hibernate.repository.database;

import com.itbatia.hibernate.model.Post;
import com.itbatia.hibernate.repository.PostRepository;
import org.hibernate.*;

import static com.itbatia.hibernate.utils.HibernateUtil.getSession;

import java.util.List;
import java.util.stream.Collectors;

public class DatabasePostRepositoryImpl implements PostRepository {

    @Override
    public Post save(Post post) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            Integer id = (Integer) session.save(post);
            post.setId(id);
            transaction.commit();
            return post;
        }
    }

    @Override
    public Post getById(Integer id) {
        try (Session session = getSession()) {
            Post post = session.get(Post.class, id);
            if (post != null) {
                Hibernate.initialize(post.getTags());
            }
            return post;
        }
    }

    @Override
    public List<Post> getAll() {
        try (Session session = getSession()) {
            return session.createQuery("SELECT p FROM Post p LEFT JOIN FETCH p.tags ORDER BY p.id ASC", Post.class)
                    .getResultList().stream().distinct().collect(Collectors.toList());
        }
    }

    @Override
    public Post update(Post post) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(post);
            transaction.commit();
            return post;
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            Post post = session.get(Post.class, id);
            session.delete(post);
            transaction.commit();
        }
    }
}