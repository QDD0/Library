package org.example.library.dao;

import org.example.library.source.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> showAllPeople() {
        return jdbcTemplate.query("SELECT * FROM people", new BeanPropertyRowMapper<>(Person.class));
    }

    public void insertPeople(Person person) {
        jdbcTemplate.update("INSERT INTO people(first_name, last_name, sur_name, year) VALUES (?,?,?,?)",
                person.getFirstName(), person.getLastName(), person.getSurName(), person.getYear());
    }

    public Person showPersonById(int id) {
        return jdbcTemplate.query("SELECT * FROM people WHERE id = ?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void editPerson(Person person) {
        jdbcTemplate.update("UPDATE people SET first_name = ?, last_name = ?, sur_name= ?, year = ? WHERE id = ?",
                person.getFirstName(), person.getLastName(), person.getSurName(), person.getYear(), person.getId());
    }

    public void deletePeopleById(int id) {
        jdbcTemplate.update("DELETE FROM people WHERE id = ?", id);
    }
}