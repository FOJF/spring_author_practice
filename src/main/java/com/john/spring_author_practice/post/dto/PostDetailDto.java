package com.john.spring_author_practice.post.dto;

import com.john.spring_author_practice.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDetailDto {
    private Long id;
    private String title;
    private String content;
    private String authorEmail;

    public static PostDetailDto fromEntity(Post post) {
        return PostDetailDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .authorEmail(post.getAuthor().getEmail())
                .build();
    }
}
