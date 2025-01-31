package com.library.library_management_backend.repository;

import com.library.library_management_backend.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Additional query for search functionality
    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(concat('%', :searchTerm, '%')) " +
            "OR LOWER(b.author) LIKE LOWER(concat('%', :searchTerm, '%'))")
    List<Book> searchBooks(@Param("searchTerm") String searchTerm);
}