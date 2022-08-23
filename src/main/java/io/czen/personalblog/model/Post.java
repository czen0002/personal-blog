package io.czen.personalblog.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Builder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Post {

    @EqualsAndHashCode.Include
    private Long id;

    private String title;

    private String content;

    private LocalDate dateCreated;
}
