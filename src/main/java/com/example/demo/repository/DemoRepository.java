package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class DemoRepository {
    private final JdbcTemplate jdbcTemplate;

    public DemoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static Book rawMapper(ResultSet rs, int rowNum) throws SQLException {
        return new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getTimestamp("update_time").toLocalDateTime()
        );
    }

    public List<Book> getBooks() {
        String sql = "select id, title, update_time from books";
        return jdbcTemplate.query(sql, DemoRepository::rawMapper);
    }

    public Optional<Book> getBookById(Integer id) {
        String sql = "select id, title, update_time from books where id = ?";
        return jdbcTemplate.query(sql, DemoRepository::rawMapper, id).stream().findFirst();
    }

    public Optional<Book> getBookByTitle(String title) {
        String sql = "select id, title, update_time from books where title = ?";
        return jdbcTemplate.query(sql, DemoRepository::rawMapper, title).stream().findFirst();
    }

    public Book postBook(String title) {
        String sql = "insert into books (title) values (?) returning id, title, update_time";
        return jdbcTemplate.query(sql, DemoRepository::rawMapper, title).getFirst();
    }

    public Optional<Book> updateBook(Integer id, String title) {
        String sql = "update books set title=? where id=? returning id, title, update_time";
        return jdbcTemplate.query(sql, DemoRepository::rawMapper, title, id).stream().findFirst();
    }

    public Optional<Book> deleteBook(Integer id) {
        String sql = "delete from books where id=? returning id, title, update_time";
        return jdbcTemplate.query(sql, DemoRepository::rawMapper, id).stream().findFirst();
    }
}
