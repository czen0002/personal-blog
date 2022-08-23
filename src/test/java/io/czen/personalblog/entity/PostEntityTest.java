package io.czen.personalblog.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PostEntityTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    void expectNoConstraintViolations_whenValidPostEntity() {
        PostEntity post = new PostEntity();
        post.setContent("content");
        post.setTitle("title");
        Set<ConstraintViolation<PostEntity>> violations = validator.validate(post);

        assertThat(violations.size(), is(equalTo(0)));
    }

    @Test
    void expectNotBlankConstraintViolation_whenBlankTitle() {
        PostEntity post = new PostEntity();
        post.setContent("content");
        post.setTitle("");
        Set<ConstraintViolation<PostEntity>> violations = validator.validate(post);

        assertThat(violations.size(), is(equalTo(1)));
        assertThat(violations.stream().findAny().get().getMessage(), is(equalTo("Post title can not be blank")));
    }

    @Test
    void expectNotNullConstraintViolation_whenNullContent() {
        PostEntity post = new PostEntity();
        post.setTitle("title");
        Set<ConstraintViolation<PostEntity>> violations = validator.validate(post);

        assertThat(violations.size(), is(equalTo(1)));
        assertThat(violations.stream().findAny().get().getMessage(), is(equalTo("Post content can not be null")));
    }
}
