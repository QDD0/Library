package org.example.library.source;

public class Book {
    private Integer id;
    private String title;
    private String firstNameAuthor;
    private String lastNameAuthor;
    private String surNameAuthor;
    private Integer year;

    public Book() {
    }

    private Book(Integer id, String title, String firstNameAuthor, String surNameAuthor, String lastNameAuthor, Integer year) {
        this.id = id;
        this.title = title;
        this.firstNameAuthor = firstNameAuthor;
        this.lastNameAuthor = lastNameAuthor;
        this.surNameAuthor = surNameAuthor;
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstNameAuthor() {
        return firstNameAuthor;
    }

    public void setFirstNameAuthor(String firstNameAuthor) {
        this.firstNameAuthor = firstNameAuthor;
    }

    public String getLastNameAuthor() {
        return lastNameAuthor;
    }

    public void setLastNameAuthor(String lastNameAuthor) {
        this.lastNameAuthor = lastNameAuthor;
    }

    public String getSurNameAuthor() {
        return surNameAuthor;
    }

    public void setSurNameAuthor(String sureNameAuthor) {
        this.surNameAuthor = sureNameAuthor;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
