package com.john.spring_author_practice.author.dto;

import com.john.spring_author_practice.author.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorSummaryDto {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private boolean isDeleted;

    public static AuthorSummaryDto fromEntity(Author author) {
        return AuthorSummaryDto.builder()
                .id(author.getId())
                .name(author.getName())
                .email(author.getEmail())
                .createdAt(author.getCreatedAt())
                .isDeleted(author.getDeleted())
                .build();
    }
}
