package io.czen.personalblog.service;

import io.czen.personalblog.model.Post;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Sql("classpath:DDL_Scripts.sql")
@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    void expectTwoPosts_whenFindAll() {
        List<Post> result = postService.findAll();

        assertThat(result.size(), is(equalTo(2)));
    }

    @Test
    void expectNull_whenFindByNotExistedId() {
        Post result = postService.findById(3L);

        assertThat(result, is(equalTo(null)));
    }

    @Test
    void expectOnePost_whenFindByExistedId() {
        Post result = postService.findById(1L);

        assertThat(result.getId(), is(equalTo(1L)));
        assertThat(result.getContent(), is(equalTo("content1")));
        assertThat(result.getTitle(), is(equalTo("title1")));
        assertThat(result.getDateCreated(), is(equalTo(LocalDate.now())));
    }

    @Test
    void expectNull_whenFindByNotExistedTitle() {
        Post result = postService.findByTitle("title3");

        assertThat(result, is(equalTo(null)));
    }

    @Test
    void expectOnePost_whenFindByExistedTitle() {
        Post result = postService.findByTitle("title1");

        assertThat(result.getId(), is(equalTo(1L)));
        assertThat(result.getContent(), is(equalTo("content1")));
        assertThat(result.getTitle(), is(equalTo("title1")));
        assertThat(result.getDateCreated(), is(equalTo(LocalDate.now())));
    }

    @Test
    void expectUpdateOldPost_whenFindOldPostById() throws Exception {
        Post updatePost = Post.builder().id(2L).title("title2new").content("content2new").build();
        postService.update(updatePost);
        Post result = postService.findById(2L);

        assertThat(result.getTitle(), is(equalTo("title2new")));
        assertThat(result.getContent(), is(equalTo("content2new")));
    }

    @Test
    void expectException_whenPostNotHaveId() {
        Post updatePost = Post.builder().title("title2new").content("content2new").build();
        Exception exception = assertThrows(Exception.class, () -> {
            postService.update(updatePost);
        });

        assertThat(exception.getMessage(), is(equalTo("Must provide post id to update post")));
    }

    @Test
    void expectException_whenCannotFindOldPostById() {
        Post updatePost = Post.builder().id(12L).title("title2new").content("content2new").build();
        Exception exception = assertThrows(Exception.class, () -> {
            postService.update(updatePost);
        });

        assertThat(exception.getMessage(), is(equalTo("Post not exist")));
    }

    @Test
    void expectCreateNewPost_whenSaveNewPost() {
        Post newPost = Post.builder().title("title3").content("content3").build();
        postService.save(newPost);
        List<Post> result = postService.findAll();

        assertThat(result.size(), is(equalTo(3)));
    }
}
