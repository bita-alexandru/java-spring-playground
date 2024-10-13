package com.example.demo;

import com.example.demo.config.DemoProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@SpringBootApplication
@EnableConfigurationProperties(DemoProperties.class)
@Service
public class Demo {

	public static void main(String[] args) {
		SpringApplication.run(Demo.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return _ -> System.out.println("hello world");
	}

}
