package com.john.spring_author_practice.post.controller;

import com.john.spring_author_practice.common.dto.ResponseDto;
import com.john.spring_author_practice.post.dto.PostCreateDto;
import com.john.spring_author_practice.post.dto.PostDetailDto;
import com.john.spring_author_practice.post.dto.PostSummaryDto;
import com.john.spring_author_practice.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> create(@RequestBody PostCreateDto postCreateDto) {
        PostSummaryDto dto = this.postService.save(postCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ResponseDto.builder()
                                .isSuccess(true)
                                .result(dto)
                                .statusCode(HttpStatus.CREATED.value())
                                .statusMessage("post is created")
                                .build()
                );
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ResponseDto> findById(@PathVariable Long id) {
        PostDetailDto dto = this.postService.findById(id);
        return ResponseEntity.ok(
                ResponseDto.builder()
                        .isSuccess(true)
                        .result(dto)
                        .statusCode(HttpStatus.OK.value())
                        .statusMessage("post found")
                        .build()
        );
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDto> findAll() {
        List<PostSummaryDto> dtos = this.postService.findAll();
        return ResponseEntity.ok(
                ResponseDto.builder()
                        .isSuccess(true)
                        .result(dtos)
                        .statusCode(HttpStatus.OK.value())
                        .statusMessage("posts found")
                        .build()
        );
    }
}
