package com.library.library_management_frontend.model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Book {
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty author = new SimpleStringProperty();
    private final StringProperty isbn = new SimpleStringProperty();
    private final StringProperty publishedDate = new SimpleStringProperty();

    // Getters and Setters for Properties
    public String getId() { return id.get(); }
    public void setId(String id) { this.id.set(id); }
    public StringProperty idProperty() { return id; }

    public String getTitle() { return title.get(); }
    public void setTitle(String title) { this.title.set(title); }
    public StringProperty titleProperty() { return title; }

    public String getAuthor() { return author.get(); }
    public void setAuthor(String author) { this.author.set(author); }
    public StringProperty authorProperty() { return author; }

    public String getIsbn() { return isbn.get(); }
    public void setIsbn(String isbn) { this.isbn.set(isbn); }
    public StringProperty isbnProperty() { return isbn; }

    public String getPublishedDate() { return publishedDate.get(); }
    public void setPublishedDate(String publishedDate) { this.publishedDate.set(publishedDate); }
    public StringProperty publishedDateProperty() { return publishedDate; }
}