package io.czen.personalblog.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Post {

    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "{post.title.NotBlank}")
    private String title;

    @NotBlank(message = "{post.content.NotNull}")
    private String content;

    private LocalDate dateCreated;
}
