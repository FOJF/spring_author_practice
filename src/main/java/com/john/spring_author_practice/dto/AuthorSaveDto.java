package com.john.spring_author_practice.dto;

import com.john.spring_author_practice.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorSaveDto {
    private String name;
    private String email;
    private String password;

    public Author toEntity() {
        return new Author(this.name, this.email, this.password);
    }
}
