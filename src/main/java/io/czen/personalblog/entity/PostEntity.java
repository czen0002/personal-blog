package io.czen.personalblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "blog_posts")
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "title", nullable = false)
    @NotBlank(message = "{post.title.NotBlank}")
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    @NotNull(message = "{post.content.NotNull}")
    private String content;

    @Column(name = "date_created", nullable = false)
    @CreatedDate
    private LocalDate dateCreated;
}
