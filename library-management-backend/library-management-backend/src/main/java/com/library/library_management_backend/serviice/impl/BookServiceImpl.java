package com.library.library_management_backend.serviice.impl;

import com.library.library_management_backend.entity.Book;
import com.library.library_management_backend.repository.BookRepository;
import com.library.library_management_backend.serviice.BookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book getBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Book with Id not found " + id));
    }

    @Override
    public Book updateBook(Long id, Book book) {

        Book existingBook = getBook(id);

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setPublishedDate(book.getPublishedDate());
        return bookRepository.save(existingBook);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);

    }

    @Override
    public List<Book> searchBooks(String searchBook) {
        return bookRepository.searchBooks(searchBook);
    }
}
