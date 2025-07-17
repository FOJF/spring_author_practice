package com.john.spring_author_practice.post.dto;

import com.john.spring_author_practice.author.domain.Author;
import com.john.spring_author_practice.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateDto {
    private String title;
    private String content;
    private Long authorId;

    public Post toEntity(Author author) {
        return Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
