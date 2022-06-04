package com.itbatia.hibernate.model;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "writers", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Writer implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany (mappedBy = "owner", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Post> posts;

    public Writer(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}