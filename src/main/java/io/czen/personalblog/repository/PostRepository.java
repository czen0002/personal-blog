package io.czen.personalblog.repository;

import io.czen.personalblog.entity.PostEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends PagingAndSortingRepository<PostEntity, Long> {
}
