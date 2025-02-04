package com.library.library_management_frontend.controller;

import com.library.library_management_frontend.model.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    @FXML private Button prevPageButton;
    @FXML private Button nextPageButton;
    @FXML private Label pageInfoLabel;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "http://localhost:8085/api/books";

    private ObservableList<Book> bookList = FXCollections.observableArrayList();

    private int currentPage = 0;
    private int pageSize = 10;
    private int totalPages = 0;
    private List<Book> allBooks;

    @FXML
    public void initialize() {
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        isbnColumn.setCellValueFactory(cellData -> cellData.getValue().isbnProperty());
        publishedDateColumn.setCellValueFactory(cellData -> cellData.getValue().publishedDateProperty());

        prevPageButton.setOnAction(event -> loadPreviousPage());
        nextPageButton.setOnAction(event -> loadNextPage());

        loadBooks();
    }

    private void loadBooks() {
        try {
            ResponseEntity<List<Book>> response = restTemplate.exchange(
                    BASE_URL,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Book>>() {}
            );
            bookList.clear();
            bookList.addAll(response.getBody());
            bookTable.setItems(bookList);
        } catch (RestClientException e) {
            showErrorAlert("Error Loading Books", "Could not fetch books from the server.", e.getMessage());
        }
    }

    @FXML
    private void handleDeleteBook() {
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            try {
                restTemplate.delete(BASE_URL + "/" + selectedBook.getId());
                loadBooks();
            } catch (RestClientException e) {
                showErrorAlert("Delete Book Error", "Failed to delete book", e.getMessage());
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "No Book Selected", "Please select a book to delete.");
        }
    }

    @FXML
    private void handleRefresh() {
        loadBooks();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void handleUpdateBook() {
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            selectedBook.setTitle(titleField.getText());
            selectedBook.setAuthor(authorField.getText());
            selectedBook.setIsbn(isbnField.getText());
            selectedBook.setPublishedDate(LocalDate.parse(publishedDateField.getText(), formatter));

            restTemplate.put(BASE_URL + "/" + selectedBook.getId(), selectedBook);
            loadBooks();
        }
    }


    private void updatePagedBooks() {
        bookList.clear();
        if (allBooks == null || allBooks.isEmpty()) {
            bookTable.setItems(bookList);
            updatePaginationControls();
            return;
        }
        int startIndex = currentPage * pageSize;
        int endIndex = Math.min(startIndex + pageSize, allBooks.size());

        bookList.addAll(allBooks.subList(startIndex, endIndex));
        bookTable.setItems(bookList);

        updatePaginationControls();
    }

    private void updatePaginationControls() {
        prevPageButton.setDisable(currentPage <= 0);
        nextPageButton.setDisable(currentPage >= totalPages - 1);
        pageInfoLabel.setText(String.format("Page %d of %d", currentPage + 1, totalPages));
    }

    private void loadPreviousPage() {
        if (currentPage > 0) {
            currentPage--;
            updatePagedBooks();
        }
    }

    private void loadNextPage() {
        if (currentPage < totalPages - 1) {
            currentPage++;
            updatePagedBooks();
        }
    }

    private void showErrorAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void handleAddBook() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            Book book = new Book();
            book.setTitle(titleField.getText());
            book.setAuthor(authorField.getText());
            book.setIsbn(isbnField.getText());
            book.setPublishedDate(LocalDate.parse(publishedDateField.getText(), formatter));

            restTemplate.postForObject(BASE_URL, book, Book.class);
            loadBooks(); // Reload all books after adding
        } catch (Exception e) {
            showErrorAlert("Add Book Error", "Failed to add book", e.getMessage());
        }
    }

}
