package io.czen.personalblog.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PostTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void expectNoConstraintViolations_whenValidPost() {
        Post post = Post.builder().content("content").title("title").build();
        Set<ConstraintViolation<Post>> violations = validator.validate(post);

        assertThat(violations.size(), is(equalTo(0)));
    }

    @Test
    void expectNotBlankConstraintViolation_whenBlankTitle() {
        Post post = Post.builder().content("content").title("").build();
        Set<ConstraintViolation<Post>> violations = validator.validate(post);

        assertThat(violations.size(), is(equalTo(1)));
        assertThat(violations.stream().findAny().get().getMessage(), is(equalTo("Post title can not be blank")));
    }

    @Test
    void expectNotNullConstraintViolation_whenNullContent() {
        Post post = Post.builder().title("title").build();
        Set<ConstraintViolation<Post>> violations = validator.validate(post);

        assertThat(violations.size(), is(equalTo(1)));
        assertThat(violations.stream().findAny().get().getMessage(), is(equalTo("Post content can not be null")));
    }
}
