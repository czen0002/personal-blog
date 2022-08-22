package io.czen.personalblog.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "blog_posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private LocalDate dateCreated;
}
