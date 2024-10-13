package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.repository.DemoRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class DemoController {
    private final DemoRepository demoRepository;

    public DemoController(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    @GetMapping("/books")
    private List<Book> getBooks() {
        return demoRepository.findAll();
    }

    @GetMapping("/books/id/{id}")
    private Book getBookById(@PathVariable Integer id) {
        return demoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/books/title/{title}")
    private List<Book> getBooksByTitle(@PathVariable String title) {
        return demoRepository.findByTitle(title)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/books")
    private Book postBook(@RequestParam @NotBlank String title) {
        return demoRepository.postBook(title)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/books/{id}")
    private Book updateBook(@PathVariable Integer id, @RequestBody String title) {
        return demoRepository.updateBook(id, title)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/books/{id}")
    private Book deleteBook(@PathVariable Integer id) {
        return demoRepository.deleteBook(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
