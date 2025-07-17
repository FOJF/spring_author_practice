package com.john.spring_author_practice.post.dto;

import com.john.spring_author_practice.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSummaryDto {
    private Long id;
    private String title;

    public static PostSummaryDto fromEntity(Post post) {
        return PostSummaryDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .build();
    }
}
