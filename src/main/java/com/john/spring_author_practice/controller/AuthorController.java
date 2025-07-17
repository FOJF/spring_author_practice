package com.john.spring_author_practice.controller;

import com.john.spring_author_practice.dto.AuthorDetailDto;
import com.john.spring_author_practice.dto.AuthorListDto;
import com.john.spring_author_practice.dto.AuthorSaveDto;
import com.john.spring_author_practice.dto.AuthorUpdatePwDto;
import com.john.spring_author_practice.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("/save")
    public void save(@RequestBody AuthorSaveDto authorSaveDto) {
        this.authorService.save(authorSaveDto);
    }

    @GetMapping("detail/{id}")
    public AuthorDetailDto findById(@PathVariable Long id) {
        return this.authorService.findById(id);
    }

    @GetMapping("/list")
    public List<AuthorListDto> findAll() {
        return this.authorService.findAll();
    }

    @GetMapping("/deletedList")
    public List<AuthorListDto> findAllIsDeleted() {
        return this.authorService.findByDeleted(true);
    }

    @GetMapping("/undeletedList")
    public List<AuthorListDto> findAllIsUnDeleted() {
        return this.authorService.findByDeleted(false);
    }

    @PatchMapping("/updatePW")
    public void updatePW(@RequestBody AuthorUpdatePwDto authorUpdatePwDto) {
        this.authorService.updatePW(authorUpdatePwDto);
    }

    @PatchMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        this.authorService.delete(id);
    }


}
