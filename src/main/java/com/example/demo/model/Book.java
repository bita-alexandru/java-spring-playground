package com.example.demo.model;

import java.time.LocalDateTime;

public record Book(
        Integer id,
        String title,
        LocalDateTime updateDate
) {
}
