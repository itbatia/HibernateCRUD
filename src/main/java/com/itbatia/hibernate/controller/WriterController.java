package com.itbatia.hibernate.controller;

import com.itbatia.hibernate.model.*;
import com.itbatia.hibernate.service.WriterService;

import java.util.*;

public class WriterController {
    private final WriterService writerService = new WriterService();
    private final PostController postController = new PostController();

    public Writer createWriter(String name, List<Post> posts) {
        return writerService.createWriter(name, posts);
    }

    public Writer getWriter(Integer id) {
        return writerService.getWriter(id);
    }

    public List<Writer> getAllWriters() {
        return writerService.getAllWriters();
    }

//    public List<Post> getAllFreePosts() {
//        List<Post> allPosts = postController.getAllPosts();
//        List<Post> allWritersPosts = new ArrayList<>();
//        List<Writer> writers = writerService.getAllWriters();
//        for (Writer w : writers) {
//            allWritersPosts.addAll(w.getPosts());
//        }
//        allPosts.removeAll(allWritersPosts);
//        return allPosts;
//    }

    public void updateWriter(Writer writer) {
        writerService.updateWriter(writer);
    }

    public void deleteWriter(Integer id) {
        writerService.deleteWriter(id);
    }
}
