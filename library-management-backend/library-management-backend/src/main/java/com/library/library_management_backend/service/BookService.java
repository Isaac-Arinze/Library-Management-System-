package com.library.library_management_backend.service;

import com.library.library_management_backend.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    Book saveBook(Book book);

    //List<Book> getAllBooks(Pageable pageable);

    Book getBook(Long id);
    Book updateBook(Long id, Book book);

    Page<Book> getAllBooks(Pageable pageable);

    void deleteBook(Long id);

    //    List<Book> searchBooks(String searchBook);
    Page<Book> searchBooks(String term, Pageable pageable);





}
