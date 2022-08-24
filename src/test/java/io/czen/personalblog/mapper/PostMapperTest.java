package io.czen.personalblog.mapper;

import io.czen.personalblog.entity.PostEntity;
import io.czen.personalblog.model.Post;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PostMapperTest {

    private final String TITLE = "TITLE";
    private final String CONTENT = "## content";
    private final Long ID1 = 1L;
    private final Long ID2 = 2L;

    private PostMapper postMapper = Mappers.getMapper(PostMapper.class);

    @Test
    void shouldMapPostEntityToPost() {
        PostEntity postEntity = new PostEntity(ID1, TITLE, CONTENT, LocalDate.now());
        Post post = postMapper.toPost(postEntity);

        assertThat(post.getId(), is(equalTo(ID1)));
        assertThat(post.getTitle(), is(equalTo(TITLE)));
        assertThat(post.getContent(), is(equalTo(CONTENT)));
    }

    @Test
    void shouldMapPostToPostEntity() {
        Post post = Post.builder().id(ID1).title(TITLE).content(CONTENT).dateCreated(LocalDate.now()).build();
        PostEntity postEntity = postMapper.toPostEntity(post);

        assertThat(postEntity.getId(), is(equalTo(ID1)));
        assertThat(postEntity.getTitle(), is(equalTo(TITLE)));
        assertThat(postEntity.getContent(), is(equalTo(CONTENT)));
    }

    @Test
    void shouldMapPostEntitiesToPosts() {
        PostEntity postEntity1 = new PostEntity(ID1, TITLE, CONTENT, LocalDate.now());
        PostEntity postEntity2 = new PostEntity(ID2, TITLE, CONTENT, LocalDate.now());
        List<PostEntity> postEntities = Arrays.asList(postEntity1, postEntity2);
        List<Post> posts = postMapper.toPosts(postEntities);

        assertThat(posts.size(), is(equalTo(2)));
        assertThat(posts.get(0).getId(), is(equalTo(ID1)));
        assertThat(posts.get(1).getId(), is(equalTo(ID2)));
    }

    @Test
    void shouldMapPostsToPostEntities() {
        Post post1 = Post.builder().id(ID1).title(TITLE).content(CONTENT).dateCreated(LocalDate.now()).build();
        Post post2 = Post.builder().id(ID2).title(TITLE).content(CONTENT).dateCreated(LocalDate.now()).build();
        List<Post> posts = Arrays.asList(post1, post2);
        List<PostEntity> postEntities = postMapper.toPostEntities(posts);

        assertThat(postEntities.size(), is(equalTo(2)));
        assertThat(postEntities.get(0).getId(), is(equalTo(ID1)));
        assertThat(postEntities.get(1).getId(), is(equalTo(ID2)));
    }
}
