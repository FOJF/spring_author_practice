package com.john.spring_author_practice.service;

import com.john.spring_author_practice.domain.Author;
import com.john.spring_author_practice.dto.AuthorDetailDto;
import com.john.spring_author_practice.dto.AuthorListDto;
import com.john.spring_author_practice.dto.AuthorSaveDto;
import com.john.spring_author_practice.dto.AuthorUpdatePwDto;
import com.john.spring_author_practice.repository.AuthorRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public void save(AuthorSaveDto authorSaveDto) {
        if (this.authorRepository.findByEmail(authorSaveDto.getEmail()).isPresent()) throw new EntityExistsException("중복된 이메일입니다.");

        this.authorRepository.save(authorSaveDto.toEntity());
    }

    public AuthorDetailDto findById(Long id) {
        return AuthorDetailDto.fromEntity(this.authorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("없는 유저 ID 입니다.")));
    }

    public List<AuthorListDto> findAll() {
        return this.authorRepository.findAll().stream().map(AuthorListDto::fromEntity).toList();
    }

    public List<AuthorListDto> findByDeleted(Boolean deleted) {
        return this.authorRepository.findByDeleted(deleted).stream().map(AuthorListDto::fromEntity).toList();
    }

    public void updatePW(AuthorUpdatePwDto authorUpdatePwDto) {
        this.authorRepository.findByEmail(authorUpdatePwDto.getEmail()).orElseThrow(() -> new IllegalArgumentException("업는 이메일 입니다."))
                .updatePW(authorUpdatePwDto.getPassword());

    }

    public void delete(Long id) {
        Author author = this.authorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("없는 유저 ID 입니다."));
        this.authorRepository.delete(author);
    }
}
