package io.czen.personalblog.repository;

import io.czen.personalblog.entity.PostEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends PagingAndSortingRepository<PostEntity, Long> {

    Optional<PostEntity> findByTitle(String title);
}
