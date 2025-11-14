package org.example.library.controllers;

import org.example.library.dao.BookDAO;
import org.example.library.service.PersonService;
import org.example.library.source.Book;
import org.example.library.source.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonService personService;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonService personService) {
        this.bookDAO = bookDAO;
        this.personService = personService;
    }

    @GetMapping("")
    public String showAllBooks(Model model) {
        model.addAttribute("booksList", bookDAO.showAllBooks());
        return "books/homeBooks";
    }

    @GetMapping("/addBook")
    public String saveBook(Model model) {
        model.addAttribute("addBook", new Book());
        return "books/addBook";
    }

    @PostMapping()
    public String saveBook(@ModelAttribute("addBook") Book book) {
        bookDAO.insertBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model) {
        Book book = bookDAO.showBookById(id);
        List<Person> personList = personService.findAll();
        model.addAttribute("people", personList);

        if (book != null) {
            model.addAttribute("book", book);
            return "books/showBook";
        }
        return "redirect:/books";
    }

    @PostMapping("/{id}/assign")
    public String assignBook(@PathVariable("id") int bookId,
                             @RequestParam("personId") Integer personId) {
        bookDAO.updateBookOwner(bookId, personId);
        return "redirect:/books/" + bookId;
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model) {
        Book book = bookDAO.showBookById(id);

        if (book != null) {
            model.addAttribute("book", book);
            return "books/editBook";
        }
        return "redirect:/books";
    }

    @PostMapping("/{id}/update")
    public String updateBook(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
        bookDAO.editBookAuthor(id, book);
        return "redirect:/books";
    }

    @PostMapping("/{id}/delete")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.deleteBookById(id);
        return "redirect:/books";
    }
}