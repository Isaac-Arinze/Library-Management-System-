package com.library.library_management_frontend.controller;

import com.library.library_management_frontend.model.Book;
import com.library.library_management_frontend.service.BookService;

import com.library.library_management_frontend.util.AlertUtils;
import com.library.library_management_frontend.util.ValidationUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;

public class MainController {
    @FXML private TableView<Book> bookTable;
    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField isbnField;
    @FXML private DatePicker publishedDatePicker;

    private final BookService bookService;
    private Book selectedBook;

    public MainController() {
        this.bookService = new BookService();
    }

    @FXML
    public void initialize() {
        setupTable();
        loadBooks();
    }

    private void setupTable() {
        // Setup table columns
        TableColumn<Book, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getTitle()));

        // Add more columns similarly
        bookTable.getColumns().addAll(titleCol);

        // Handle selection
        bookTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    selectedBook = newSelection;
                    populateFields();
                });
    }

    @FXML
    private void handleSave() {
        try {
            if (!ValidationUtils.validateBookFields(titleField.getText(),
                    authorField.getText(), isbnField.getText())) {
                AlertUtils.showError("Validation Error",
                        "Please fill all required fields");
                return;
            }

            Book book = new Book();
            book.setTitle(titleField.getText());
            book.setAuthor(authorField.getText());
            book.setIsbn(isbnField.getText());
            book.setPublishedDate(publishedDatePicker.getValue());

            bookService.saveBook(book);
            AlertUtils.showInfo("Success", "Book saved successfully");
            loadBooks();
            clearFields();
        } catch (Exception e) {
            AlertUtils.showError("Error", "Failed to save book: " + e.getMessage());
        }
    }

    private void loadBooks() {
        try {
            var books = bookService.getAllBooks();
            bookTable.setItems(FXCollections.observableArrayList(books));
        } catch (Exception e) {
            AlertUtils.showError("Error", "Failed to load books: " + e.getMessage());
        }
    }

    private void populateFields() {
        if (selectedBook != null) {
            titleField.setText(selectedBook.getTitle());
            authorField.setText(selectedBook.getAuthor());
            isbnField.setText(selectedBook.getIsbn());
            publishedDatePicker.setValue(selectedBook.getPublishedDate());
        }
    }

    private void clearFields() {
        titleField.clear();
        authorField.clear();
        isbnField.clear();
        publishedDatePicker.setValue(null);
        selectedBook = null;
    }
}