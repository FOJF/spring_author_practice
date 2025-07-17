package com.john.spring_author_practice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity

@SQLDelete(sql = "UPDATE author SET deleted = true WHERE id=?")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String nickName;
    private String phoneNumber;
    private String address;
    @Column(length = 50, unique = true, nullable = false)
    private String email;
    private String password;
    private Boolean deleted;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private LocalDateTime passwordUpdatedAt;

    public Author(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.deleted = false;
    }

    public void updatePW(String password) {
        this.password = password;
        this.passwordUpdatedAt = LocalDateTime.now();
    }
}
