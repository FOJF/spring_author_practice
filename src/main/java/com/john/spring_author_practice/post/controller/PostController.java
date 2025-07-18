package com.john.spring_author_practice.post.controller;

import com.john.spring_author_practice.common.dto.ResponseDto;
import com.john.spring_author_practice.common.dto.StatusDto;
import com.john.spring_author_practice.post.dto.PostCreateDto;
import com.john.spring_author_practice.post.dto.PostDetailDto;
import com.john.spring_author_practice.post.dto.PostSummaryDto;
import com.john.spring_author_practice.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<?> create(@RequestBody PostCreateDto postCreateDto) {
        PostSummaryDto dto = this.postService.save(postCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ResponseDto.builder()
                                .isSuccess(true)
                                .data(dto)
                                .status(new StatusDto(HttpStatus.CREATED.value(), "post is created"))
                                .build()
                );
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        PostDetailDto dto = this.postService.findById(id);
        return ResponseEntity.ok(
                ResponseDto.builder()
                        .isSuccess(true)
                        .data(dto)
                        .status(new StatusDto(HttpStatus.OK.value(), "post found"))
                        .build()
        );
    }

    @GetMapping("/list-all")
    public ResponseEntity<?> findAll() {
        List<PostSummaryDto> dtos = this.postService.findAll();
        return ResponseEntity.ok(
                ResponseDto.builder()
                        .isSuccess(true)
                        .data(dtos)
                        .status(new StatusDto(HttpStatus.OK.value(), "posts found"))
                        .build()
        );
    }

    @GetMapping("/list-undeleted-pageable")
    // 데이터 요청 예시 : 8080/post/list?page=0&size=20&sort=title,asc

    public ResponseEntity<?> findAll(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostSummaryDto> dtos = this.postService.findAll(pageable);
        return ResponseEntity.ok(
                ResponseDto.builder()
                        .isSuccess(true)
                        .data(dtos)
                        .status(new StatusDto(HttpStatus.OK.value(), "posts found"))
                        .build()
        );
    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        PostDetailDto dto = this.postService.deleteById(id);

        return ResponseEntity.ok(
                ResponseDto.builder()
                        .isSuccess(true)
                        .data(dto)
                        .status(new StatusDto(HttpStatus.OK.value(), "post is deleted"))
                        .build()
        );
    }
}
