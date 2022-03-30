package com.itbatia.hibernate.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tag", schema = "public", catalog = "HibernateCRUD")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;
}
