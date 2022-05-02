package com.itbatia.hibernate.controller;

import com.itbatia.hibernate.model.*;
import com.itbatia.hibernate.service.*;

import java.util.List;

public class PostController {
    private final PostService postService = new PostService();

    public Post createPost(String content, PostStatus postStatus, List<Tag> tags) {
        return postService.createPost(content, postStatus, tags);
    }
    public Post createPost(String content, PostStatus postStatus, List<Tag> tags, Writer writer) {
        return postService.createPost(content, postStatus, tags, writer);
    }

    public Post getPost(Integer id) {
        return postService.getPost(id);
    }

    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    public Post updatePost(Post newPost) {
        return postService.updatePost(newPost);
    }

    public void deletePost(Integer id) {
        postService.deletePost(id);
    }
}
