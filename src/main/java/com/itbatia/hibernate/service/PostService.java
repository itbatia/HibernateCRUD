package com.itbatia.hibernate.service;

import com.itbatia.hibernate.model.*;
import com.itbatia.hibernate.repository.PostRepository;
import com.itbatia.hibernate.repository.database.DatabasePostRepositoryImpl;

import java.util.List;

public class PostService {

    private PostRepository postRepository = new DatabasePostRepositoryImpl();

    public PostService() {
    }

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(String content, PostStatus postStatus, List<Tag> tags) {
        Post post = new Post(null, content, postStatus, tags);
        return postRepository.save(post);
    }

    public Post createPost(String content, PostStatus postStatus, List<Tag> tags, Writer writer) {
        Post post = new Post(null, content, postStatus, tags, writer);
        return postRepository.save(post);
    }

    public Post getPost(Integer id) {
        return postRepository.getById(id);
    }

    public List<Post> getAllPosts() {
        return postRepository.getAll();
    }

    public Post updatePost(Post newPost) {
        return postRepository.update(newPost);
    }

    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }
}