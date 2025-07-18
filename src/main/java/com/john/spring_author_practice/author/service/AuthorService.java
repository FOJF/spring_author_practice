package com.john.spring_author_practice.author.service;

import com.john.spring_author_practice.author.domain.Author;
import com.john.spring_author_practice.author.dto.AuthorDetailDto;
import com.john.spring_author_practice.author.dto.AuthorSummaryDto;
import com.john.spring_author_practice.author.dto.AuthorCreateDto;
import com.john.spring_author_practice.author.dto.AuthorUpdatePwDto;
import com.john.spring_author_practice.author.repository.AuthorRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorDetailDto save(AuthorCreateDto authorCreateDto) {
        if (this.authorRepository.findByEmail(authorCreateDto.getEmail()).isPresent()) throw new EntityExistsException("중복된 이메일입니다.");

        Author author = this.authorRepository.save(authorCreateDto.toEntity());
        return AuthorDetailDto.fromEntity(author);
    }

    public AuthorDetailDto findById(Long id) {
        return AuthorDetailDto.fromEntity(this.authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("없는 유저 ID 입니다.")));
    }

    public List<AuthorSummaryDto> findAll() {
        return this.authorRepository.findAll().stream().map(AuthorSummaryDto::fromEntity).toList();
    }

    public List<AuthorSummaryDto> findByDeleted(Boolean deleted) {
        return this.authorRepository.findByDeleted(deleted).stream().map(AuthorSummaryDto::fromEntity).toList();
    }

    public void updatePW(AuthorUpdatePwDto authorUpdatePwDto) {
        this.authorRepository.findByEmail(authorUpdatePwDto.getEmail()).orElseThrow(() -> new EntityNotFoundException("없는 이메일 입니다."))
                .updatePW(authorUpdatePwDto.getPassword());
    }

    public void delete(Long id) {
        Author author = this.authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("없는 유저 ID 입니다."));
        this.authorRepository.delete(author);
    }
}
