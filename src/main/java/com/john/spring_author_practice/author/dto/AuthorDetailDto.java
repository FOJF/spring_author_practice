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
public class AuthorDetailDto {
    private Long id;
    private String name;
    private String nickName;
    private String phoneNumber;
    private String address;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime passwordUpdatedAt;
    private Boolean deleted;

    public static AuthorDetailDto fromEntity(Author author) {
        return AuthorDetailDto.builder()
                .id(author.getId())
                .name(author.getName())
                .email(author.getEmail())
                .password(author.getPassword())
                .createdAt(author.getCreatedAt())
                .updatedAt(author.getUpdatedAt())
                .passwordUpdatedAt(author.getPasswordUpdatedAt())
                .deleted(author.getDeleted())
                .nickName(author.getNickName())
                .phoneNumber(author.getPhoneNumber())
                .address(author.getAddress())
                .build();

    }
}
