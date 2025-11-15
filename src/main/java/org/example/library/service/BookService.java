package org.example.library.service;

import org.example.library.source.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final JdbcTemplate jdbcTemplate;

    public BookService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> showBooks(int id) {
        return jdbcTemplate.query("SELECT * FROM books WHERE id_person = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
}
