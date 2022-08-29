package io.czen.personalblog.service;

import io.czen.personalblog.entity.PostEntity;
import io.czen.personalblog.mapper.PostMapper;
import io.czen.personalblog.model.Post;
import io.czen.personalblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    public List<Post> findAll() {
        return StreamSupport.stream(postRepository.findAll().spliterator(), false)
                .map(postMapper::toPost)
                .toList();
    }

    public Post findById(Long id) {
        return postRepository.findById(id).map(postMapper::toPost).orElse(null);
    }

    public Post findByTitle(String title) {
        return postRepository.findByTitle(title).map(postMapper::toPost).orElse(null);
    }

    public Post save(Post post) {
        PostEntity newPostEntity = postMapper.toPostEntity(post);
        return postMapper.toPost(postRepository.save(newPostEntity));
    }

    public Post update(Post post) throws Exception {
        if (post.getId() == null) {
            throw new Exception("Must provide post id to update post");
        }
        Post retrievePost = findById(post.getId());
        if (retrievePost == null) {
            throw new Exception("Post not exist");
        }
        retrievePost.setTitle(post.getTitle());
        retrievePost.setContent(post.getContent());
        PostEntity updatePostEntity = postMapper.toPostEntity(retrievePost);
        return postMapper.toPost(postRepository.save(updatePostEntity));
    }
}
