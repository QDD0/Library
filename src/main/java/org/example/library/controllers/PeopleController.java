package org.example.library.controllers;

import org.example.library.dao.PersonDAO;
import org.example.library.source.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private PersonDAO personDAO;

    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
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
}
