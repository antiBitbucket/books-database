package com.ibragimov.library.service;

import com.ibragimov.library.dao.BookRepository;
import com.ibragimov.library.model.Author;
import com.ibragimov.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book getBook(Integer id) {
        return bookRepository.findOne(id);
    }

    @Transactional
    @Secured("ROLE_ADMIN")
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    @Secured("ROLE_ADMIN")
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getByAuthor(Author author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> getBySubstring(String pattern) {
        return pattern == null ? (List<Book>) bookRepository.findAll() : bookRepository.findByTitleContainingIgnoreCase(pattern);
    }

    public Book getByTitleAndAuthor(String title, Author author) {
        return bookRepository.findByTitleAndAuthor(title, author);
    }

}
