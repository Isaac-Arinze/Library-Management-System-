package com.library.library_management_frontend.service;


import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.library.library_management_frontend.config.ApiConfig;
import com.library.library_management_frontend.model.Book;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Collections;

public class BookService {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String baseUrl;

    public BookService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
        this.baseUrl = ApiConfig.BASE_URL + "/api/books";
    }

    public List<Book> getAllBooks() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(baseUrl))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request,
                HttpResponse.BodyHandlers.ofString());

        return objectMapper.readValue(response.body(),
                new TypeReference<List<Book>>() {});
    }

    public Book saveBook(Book book) throws Exception {
        String json = objectMapper.writeValueAsString(book);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(baseUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = httpClient.send(request,
                HttpResponse.BodyHandlers.ofString());

        return objectMapper.readValue(response.body(), Book.class);
    }

    public Book updateBook(Book book) throws Exception {
        String json = objectMapper.writeValueAsString(book);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(baseUrl + "/" + book.getId()))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = httpClient.send(request,
                HttpResponse.BodyHandlers.ofString());

        return objectMapper.readValue(response.body(), Book.class);
    }

    public void deleteBook(Long id) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(baseUrl + "/" + id))
                .DELETE()
                .build();

        httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}