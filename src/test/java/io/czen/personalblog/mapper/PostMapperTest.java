package io.czen.personalblog.mapper;

import io.czen.personalblog.entity.PostEntity;
import io.czen.personalblog.model.Post;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PostMapperTest {

    private final String TITLE = "TITLE";
    private final String CONTENT = "## content";
    private final Long ID = 1L;

    @Autowired
    private PostMapper postMapper;

    @Test
    void shouldMapPostEntityToPost() {
        PostEntity postEntity = new PostEntity(ID, TITLE, CONTENT, LocalDate.now());
        Post post = postMapper.toPost(postEntity);

        assertThat(post.getId(), is(equalTo(ID)));
        assertThat(post.getTitle(), is(equalTo(TITLE)));
        assertThat(post.getContent(), is(equalTo(CONTENT)));
    }

    @Test
    void shouldMapPostToPostEntity() {
        Post post = Post.builder().id(ID).title(TITLE).content(CONTENT).dateCreated(LocalDate.now()).build();
        PostEntity postEntity = postMapper.toPostEntity(post);

        assertThat(postEntity.getId(), is(equalTo(ID)));
        assertThat(postEntity.getTitle(), is(equalTo(TITLE)));
        assertThat(postEntity.getContent(), is(equalTo(CONTENT)));
    }
}
