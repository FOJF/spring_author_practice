package com.john.spring_author_practice.author.domain;

import com.john.spring_author_practice.common.domain.BaseTimeEntity;
import com.john.spring_author_practice.post.domain.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Builder
@SQLDelete(sql = "UPDATE author SET deleted = true WHERE id=?")
public class Author extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "이름은 공백일 수 없습니다.")
    private String name;
    private String nickName;
    private String phoneNumber;
    private String address;
    @Email
    @Column(length = 50, unique = true, nullable = false)
    private String email;
    @NotBlank(message = "비밀번호는 공백일 수 없습니다.")
    @Size(min = 6)
    private String password;
    @Builder.Default
    private Boolean deleted = Boolean.FALSE;

    private LocalDateTime passwordUpdatedAt;

    @Builder.Default
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    public void updatePW(String password) {
        this.password = password;
        this.passwordUpdatedAt = LocalDateTime.now();
    }
}
