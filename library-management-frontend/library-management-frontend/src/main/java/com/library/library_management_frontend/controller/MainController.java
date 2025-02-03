package com.library.library_management_frontend.controller;

import com.library.library_management_frontend.model.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

public class MainController {

    @FXML private TableView<Book> bookTable;
    @FXML private TableColumn<Book, String> titleColumn;
    @FXML private TableColumn<Book, String> authorColumn;
    @FXML private TableColumn<Book, String> isbnColumn;
    @FXML private TableColumn<Book, String> publishedDateColumn;

    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField isbnField;
    @FXML private TextField publishedDateField;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "http://localhost:8080/api/books";

    private ObservableList<Book> bookList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Bind columns to properties
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        isbnColumn.setCellValueFactory(cellData -> cellData.getValue().isbnProperty());
        publishedDateColumn.setCellValueFactory(cellData -> cellData.getValue().publishedDateProperty());

        // Load books from the backend
        loadBooks();
    }

    private void loadBooks() {
        Book[] books = restTemplate.getForObject(BASE_URL, Book[].class);
        bookList.clear();
        if (books != null) {
            bookList.addAll(books);
        }
        bookTable.setItems(bookList);
    }

    @FXML
    private void handleAddBook() {
        Book book = new Book();
        book.setTitle(titleField.getText());
        book.setAuthor(authorField.getText());
        book.setIsbn(isbnField.getText());
        book.setPublishedDate(String.valueOf(LocalDate.parse(publishedDateField.getText())));

        restTemplate.postForObject(BASE_URL, book, Book.class);
        loadBooks();
    }

    @FXML
    private void handleUpdateBook() {
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            selectedBook.setTitle(titleField.getText());
            selectedBook.setAuthor(authorField.getText());
            selectedBook.setIsbn(isbnField.getText());
            selectedBook.setPublishedDate(String.valueOf(LocalDate.parse(publishedDateField.getText())));

            restTemplate.put(BASE_URL + "/" + selectedBook.getId(), selectedBook);
            loadBooks();
        }
    }

    @FXML
    private void handleDeleteBook() {
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            restTemplate.delete(BASE_URL + "/" + selectedBook.getId());
            loadBooks();
        }
    }

    @FXML
    private void handleRefresh() {
        loadBooks();
    }
}