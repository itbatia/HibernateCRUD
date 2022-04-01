package com.itbatia.hibernate.view;

import com.itbatia.hibernate.controller.PostController;
import com.itbatia.hibernate.model.*;

import static com.itbatia.hibernate.utils.Messages.*;
import static com.itbatia.hibernate.utils.ShowInConsole.*;

import java.util.*;

public class PostView {
    private final TagView tagView = new TagView();
    private final PostController postController = new PostController();
    private final Scanner scanner = new Scanner(System.in);

    public Post createPost() {
        System.out.println("Enter new post content:");
        String postContent = scanner.nextLine();
        System.out.println("The post is created. Add tags to it?\n1 - YES\n2 - NO");
        List<Tag> tags = new ArrayList<>();
        while (answerFromUser()) {
            tags.add(tagView.createTag());
            System.out.println("Add another tag?\n1 - YES\n2 - NO");
        }
        Post newPost = postController.createPost(postContent, PostStatus.ACTIVE, tags);
        System.out.println("Post created:");
        showPost(newPost);
        return newPost;
    }

    private boolean answerFromUser() {
        String userInput = scanner.nextLine();
        return userInput.equals("1");
    }

    public Post getPost() {
        int id = idFromUser();
        if (id != 0) {
            System.out.println("Found Post:");
            showPost(postController.getPost(id));
            return postController.getPost(id);
        } else return null;
    }

    public List<Post> getAllPosts() {
        List<Post> posts = postController.getAllPosts();
        showPosts(posts);
        return posts;
    }

    public void updatePostById() {
        int id = idFromUser();
        if (id != 0) {
            System.out.println("Found post:");
            Post post = postController.getPost(id);
            showPost(post);
            System.out.println(UPDATE_POST_SELECTION_1.getMessage());
            updatePost(post);
        }
    }

    public void updatePostByPost(Post post) {
        System.out.println(UPDATE_POST_SELECTION_1.getMessage());
        updatePost(post);
    }

    private void updatePost(Post post) {
        String userInput = scanner.nextLine();
        if (userInput.matches("[1-4]")) {
            while (userInput.matches("[1-4]")) {
                switch (Integer.parseInt(userInput)) {
                    case 1:
                        updatePostCase1_UpdateContent(post);
                        break;
                    case 2:
                        updatePostCase2_AddNewTag(post);
                        break;
                    case 3:
                        updatePostCase3_DeleteTag(post);
                        break;
                    case 4:
                        updatePostCase4_UpdatePostStatus(post);
                        break;
                }
                System.out.println(UPDATE_POST_SELECTION_2.getMessage());
                userInput = scanner.nextLine();
            }
            System.out.println("Changes applied!");
        } else if (userInput.equals("q")) {
            System.out.println("Back.");
        } else {
            System.out.println("Incorrect data.");
        }
    }

    private void updatePostCase1_UpdateContent(Post post) {
        System.out.println("Enter new post content:");
        post.setContent(scanner.nextLine());
        postController.updatePost(post);
        System.out.println("Content is changed!");
    }

    private void updatePostCase2_AddNewTag(Post post) {
        Tag tag = tagView.createTag();
        List<Tag> tags = post.getTags();
        tags.add(tag);
        post.setTags(tags);
        postController.updatePost(post);
        System.out.println("Tag added!");
    }

    private void updatePostCase3_DeleteTag(Post post) {
        System.out.print("List of tags for this post:");
        showTags(post.getTags());
        System.out.println("Enter tag ID to remove it:");
        String tagId = scanner.nextLine();
        if (tagId.matches("\\d+")) {
            Integer id = Integer.parseInt(tagId);
            List<Tag> tags = post.getTags();
            boolean result = tags.removeIf(tag -> tag.getId().equals(id));
            if (result) {
                post.setTags(tags);
                postController.updatePost(post);
                System.out.println("Tag deleted!");
            } else {
                System.out.println("Post doesn't have this tag.");
            }
        } else {
            System.out.println("Incorrect data.");
        }
    }

    private void updatePostCase4_UpdatePostStatus(Post post) {
        System.out.println("Make your choice:\n1 - ACTIVE\n2 - DELETED");
        String userInput = scanner.nextLine();
        if (userInput.equals("1")) {
            post.setStatus(PostStatus.ACTIVE);
            postController.updatePost(post);
            System.out.println("Done!");
        } else if (userInput.equals("2")) {
            post.setStatus(PostStatus.DELETED);
            postController.updatePost(post);
            System.out.println("Done!");
        } else {
            System.out.println("Incorrect data.");
        }
    }

    public void deletePostById() {
        int id = idFromUser();
        if (id != 0) {
            System.out.println("Found post. " + CONFIRMATION.getMessage());
            deletePost(id);
        }
    }

    public void deletePostByPost(Post post) {
        System.out.println(CONFIRMATION_2.getMessage());
        deletePost(post.getId());
    }

    private void deletePost(Integer id) {
        String userChoice = scanner.nextLine();
        if (userChoice.equals("1")) {
            postController.deletePost(id);
            System.out.println("Post successfully deleted!");
        } else {
            System.out.println("Back to Post menu.");
        }
    }

    private Integer idFromUser() {
        System.out.println("Enter post id:");
        String stringID = scanner.nextLine();
        if (!stringID.matches("\\d+")) {
            System.out.println("Incorrect data.");
            return 0;
        }
        int intID = Integer.parseInt(stringID);
        if (postController.getPost(intID) == null) {
            System.out.printf("Post %s doesn't exist\n", intID);
            return 0;
        } else return intID;
    }
}
