package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("books")
public record Book(
        @Id Integer id,
        String title,
        @Column("update_time") LocalDateTime updateTime
) {
}
