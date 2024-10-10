package com.example.demo.repository;

import com.example.demo.model.Book;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

@Repository
public class DemoRepository {
    private final Map<Integer, Book> books = new HashMap<>();
    private Integer booksIndex = 0;

    public List<Book> getBooks() {
        return books.values().stream()
                .toList();
    }

    @PostConstruct
    private void initBooks() {
        Stream.of(
                new Book(booksIndex++, "t1", LocalDateTime.now()),
                new Book(booksIndex++, "t2", LocalDateTime.now()),
                new Book(booksIndex++, "t3", LocalDateTime.now())
        ).forEach(book -> books.put(book.id(), book));
    }

    public Optional<Book> getBookById(Integer id) {
        return Optional.ofNullable(books.get(id));
    }

    public Optional<Book> getBookByTitle(String title) {
        return books.values().stream()
                .filter(book -> book.title().equals(title))
                .findFirst();
    }

    public Book postBook(String title) {
        return Stream.of(new Book(booksIndex, title, LocalDateTime.now()))
                .peek(book -> books.put(booksIndex++, book))
                .toList()
                .getFirst();
    }

    public Optional<Book> updateBook(Integer id, String title) {
        return books.values().stream()
                .filter(book -> book.id().equals(id))
                .map(_ -> new Book(id, title, LocalDateTime.now()))
                .peek(book -> books.put(id, book))
                .findFirst();
    }

    public Optional<Book> deleteBook(Integer id) {
        return Optional.ofNullable(books.get(id))
                .map(book -> {
                    books.remove(id);
                    return book;
                });
    }
}
