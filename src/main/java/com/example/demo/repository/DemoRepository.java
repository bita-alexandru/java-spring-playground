package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface DemoRepository extends ListCrudRepository<Book, Integer> {
    Optional<List<Book>> findByTitle(String title);

    @Query("insert into books (title) values (:title) returning id, title, update_time")
    Optional<Book> postBook(@Param("title") String title);

    @Query("update books set title=:title where id=:id returning id, title, update_time")
    Optional<Book> updateBook(@Param("id") Integer id, @Param("title") String title);

    @Query("delete from books where id=:id returning id, title, update_time")
    Optional<Book> deleteBook(@Param("id") Integer id);
}
