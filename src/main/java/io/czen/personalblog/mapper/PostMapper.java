package io.czen.personalblog.mapper;

import io.czen.personalblog.entity.PostEntity;
import io.czen.personalblog.model.Post;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post toPost(PostEntity postEntity);

    PostEntity toPostEntity(Post post);

    List<Post> toPosts(List<PostEntity> postEntities);

    List<PostEntity> toPostEntities(List<Post> posts);
}
