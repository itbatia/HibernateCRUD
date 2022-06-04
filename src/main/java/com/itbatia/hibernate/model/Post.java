package com.itbatia.hibernate.model;

import javax.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "posts", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "post_status")
    @Enumerated(EnumType.STRING)
    private PostStatus status;

    @ManyToMany
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @ManyToOne
    @JoinColumn(name = "writer_id", referencedColumnName = "id")
    private Writer owner;

    public Post(Integer id, String content, PostStatus status, List<Tag> tags) {
        this.id = id;
        this.content = content;
        this.status = status;
        this.tags = tags;
    }
}