package com.john.spring_author_practice.author.dto;

import com.john.spring_author_practice.author.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorCreateDto {
    private String name;
    private String email;
    private String password;
    private String nickName;
    private String phoneNumber;
    private String address;

    public Author toEntity() {
        return Author.builder()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .nickName(this.nickName)
                .phoneNumber(this.phoneNumber)
                .address(this.address)
                .build();
    }
}
