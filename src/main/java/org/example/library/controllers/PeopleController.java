package org.example.library.controllers;

import org.example.library.dao.PersonDAO;
import org.example.library.service.BookService;
import org.example.library.source.Book;
import org.example.library.source.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private PersonDAO personDAO;
    private BookService bookService;

    @Autowired
    public PeopleController(PersonDAO personDAO, BookService bookService) {
        this.personDAO = personDAO;
        this.bookService = bookService;
    }

    @GetMapping("")
    public String showAllPeople(Model model) {
        model.addAttribute("peopleList", personDAO.showAllPeople());
        return "people/homePeople";
    }

    @GetMapping("/addPerson")
    public String savePeople(Model model) {
        model.addAttribute("addPerson", new Person());
        return "people/addPerson";
    }

    @PostMapping()
    public String savePeople(@ModelAttribute("addPerson") Person person) {
        personDAO.insertPeople(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String showPeople(@PathVariable("id") int id, Model model) {
        Person person = personDAO.showPersonById(id);
        List<Book> bookList = bookService.showBooks(id);
        model.addAttribute("bookList", bookList);

        if (person != null) {
            model.addAttribute("person", person);
            return "people/showPerson";
        }
        return "redirect:/homePeople";
    }

    @GetMapping("/{id}/edit")
    public String editPeople(@PathVariable("id") int id, Model model) {
        Person person = personDAO.showPersonById(id);

        if (person != null) {
            model.addAttribute("person", person);
            return "people/editPerson";
        }
        return "redirect:/homePeople";
    }

    @PostMapping("/{id}/update")
    public String updatePeople(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        personDAO.editPerson(id, person);
        return "redirect:/people";
    }

    @PostMapping("/{id}/delete")
    public String deletePeople(@PathVariable("id") int id) {
        personDAO.deletePeopleById(id);
        return "redirect:/people";
    }
}
