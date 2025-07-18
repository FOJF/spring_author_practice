package com.john.spring_author_practice.post.repository;

import com.john.spring_author_practice.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByDeleted(Pageable pageable, Boolean deleted);
}
