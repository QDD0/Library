package org.example.library.controllers;

import org.example.library.dao.PersonDAO;
import org.example.library.source.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public String showPeople(@PathVariable("id") int id, Model model) {
        Person person = personDAO.showPersonById(id);
        if (person != null) {
            model.addAttribute("person", person);
            return "people/showPerson";
        }
        return "redirect:/homePeople";
    }
}
