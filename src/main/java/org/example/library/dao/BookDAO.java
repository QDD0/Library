package org.example.library.dao;

import org.example.library.source.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> showAllBooks() {
        return jdbcTemplate.query("SELECT * FROM books", new BeanPropertyRowMapper<>(Book.class));
    }

    public void insertBook(Book book) {
        jdbcTemplate.update("INSERT INTO books(title, first_name_author,last_name_author, sur_name_author, year ) VALUES (?,?,?,?,?)",
                book.getTitle(), book.getFirstNameAuthor(), book.getLastNameAuthor(), book.getSurNameAuthor(), book.getYear());
    }

    public Book showBookById(int id) {
        return jdbcTemplate.query("SELECT * FROM books WHERE id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void updateBookOwner(int bookId, Integer personId) {
        jdbcTemplate.update("UPDATE books SET id_person = ? WHERE id = ?", personId, bookId);
    }
}