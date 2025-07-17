package com.john.spring_author_practice.author.controller;

import com.john.spring_author_practice.author.dto.AuthorDetailDto;
import com.john.spring_author_practice.author.dto.AuthorListDto;
import com.john.spring_author_practice.author.dto.AuthorCreateDto;
import com.john.spring_author_practice.author.dto.AuthorUpdatePwDto;
import com.john.spring_author_practice.author.service.AuthorService;
import com.john.spring_author_practice.common.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody AuthorCreateDto authorCreateDto) {
        AuthorDetailDto dto = this.authorService.save(authorCreateDto);
        return new ResponseEntity<>(new ResponseDto(true, dto, HttpStatus.CREATED.value(), "author is created"), HttpStatus.CREATED);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<ResponseDto> findById(@PathVariable Long id) {
        AuthorDetailDto dto = this.authorService.findById(id);
        return new ResponseEntity<>(new ResponseDto(true, dto, HttpStatus.OK.value(), "author is found"), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDto> findAll() {
        List<AuthorListDto> dtos = this.authorService.findAll();
        return new ResponseEntity<>(new ResponseDto(true, dtos, HttpStatus.OK.value(), "all authors list is found"), HttpStatus.OK);
    }

    @GetMapping("/deletedList")
    public ResponseEntity<ResponseDto> findAllIsDeleted() {
        List<AuthorListDto> dtos = this.authorService.findByDeleted(true);
        return new ResponseEntity<>(new ResponseDto(true, dtos, HttpStatus.OK.value(), "deleted authors list is found"), HttpStatus.OK);
    }

    @GetMapping("/undeletedList")
    public ResponseEntity<ResponseDto> findAllIsUnDeleted() {
        List<AuthorListDto> dtos = this.authorService.findByDeleted(false);
        return new ResponseEntity<>(new ResponseDto(true, dtos, HttpStatus.OK.value(), "undeleted authors list is found"), HttpStatus.OK);
    }

    @PatchMapping("/updatePW")
    public ResponseEntity<?> updatePW(@RequestBody AuthorUpdatePwDto authorUpdatePwDto) {
        this.authorService.updatePW(authorUpdatePwDto);
        return new ResponseEntity<>(new ResponseDto(true, authorUpdatePwDto, HttpStatus.OK.value(), "password is updated"),HttpStatus.OK);
    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        this.authorService.delete(id);
        return new ResponseEntity<>(new ResponseDto(true, id, HttpStatus.OK.value(), "user is deleted"), HttpStatus.OK);
    }


}
