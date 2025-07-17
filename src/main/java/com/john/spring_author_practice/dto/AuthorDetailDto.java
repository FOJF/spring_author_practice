package com.john.spring_author_practice.dto;

import com.john.spring_author_practice.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDetailDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime passwordUpdatedAt;
    private Boolean deleted;

    public static AuthorDetailDto fromEntity(Author author) {
        return new AuthorDetailDto(
                author.getId(),
                author.getName(),
                author.getEmail(),
                author.getPassword(),
                author.getCreatedAt(),
                author.getUpdatedAt(),
                author.getPasswordUpdatedAt(),
                author.getDeleted()
        );
    }
}
