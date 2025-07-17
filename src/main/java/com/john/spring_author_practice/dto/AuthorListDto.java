package com.john.spring_author_practice.dto;

import com.john.spring_author_practice.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorListDto {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private boolean isDeleted;

    public static AuthorListDto fromEntity(Author author) {
        return new AuthorListDto(author.getId(), author.getName(), author.getEmail(), author.getCreatedAt(), author.getDeleted());
    }
}
