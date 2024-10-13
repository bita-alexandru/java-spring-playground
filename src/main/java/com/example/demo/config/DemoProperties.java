package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "demo")
public record DemoProperties(Integer id, String message) {
}
