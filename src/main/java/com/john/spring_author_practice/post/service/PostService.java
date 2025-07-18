package com.john.spring_author_practice.post.service;

import com.john.spring_author_practice.author.domain.Author;
import com.john.spring_author_practice.author.repository.AuthorRepository;
import com.john.spring_author_practice.post.domain.Post;
import com.john.spring_author_practice.post.dto.PostCreateDto;
import com.john.spring_author_practice.post.dto.PostDetailDto;
import com.john.spring_author_practice.post.dto.PostSummaryDto;
import com.john.spring_author_practice.post.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;

    public PostSummaryDto save(PostCreateDto postCreateDto) {
        Author author = this.authorRepository.findById(postCreateDto.getAuthorId()).orElseThrow(() -> new EntityNotFoundException("없는 유저 ID 입니다."));
        Post post = this.postRepository.save(postCreateDto.toEntity(author));
        return PostSummaryDto.fromEntity(post);
    }

    public PostDetailDto findById(Long id) {
        return PostDetailDto.fromEntity(this.postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("없는 포스트 ID 입니다.")));
    }

    public List<PostSummaryDto> findAll() {
        return this.postRepository.findAll().stream().map(PostSummaryDto::fromEntity).toList();
    }

    public Page<PostSummaryDto> findAll(Pageable pageable) {
        return this.postRepository.findAllByDeleted(pageable, Boolean.FALSE).map(PostSummaryDto::fromEntity);
    }

    public PostDetailDto deleteById(Long id) {
        Post post = this.postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("없는 포스트 ID 입니다."));
        post.delete();
        return PostDetailDto.fromEntity(post);
    }
}
