package com.ibragimov.library.controllers;

import com.ibragimov.library.model.Author;
import com.ibragimov.library.model.Book;
import com.ibragimov.library.service.AuthorService;
import com.ibragimov.library.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ibragimov.library.config.SecurityUtils.getCurrentUser;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping(value = "/rest/books/")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

    @RequestMapping(value = "byPattern/{filter}", method = RequestMethod.GET)
    public List<Book> getByPattern(@PathVariable String filter) {
        logger.debug("Going to get list of books by filter = {}", filter);
        return bookService.getBySubstring(filter);
    }

    @RequestMapping(value = "byAuthor/{author}", method = RequestMethod.GET)
    public List<Book> getByAuthor(@PathVariable String author) {
        logger.debug("Going to get list of books by author = {}", author);
        return bookService.getByAuthor(authorService.getByName(author));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Book> createNewBook(@ModelAttribute Book book) {
        Author author = getCurrentUser();
        String title = book.getTitle();
        logger.debug("Going to create new book for {}", title);
        if (bookService.getByTitleAndAuthor(title, author) != null) {
            logger.debug("Book is already exists");
            return new ResponseEntity<>(BAD_REQUEST);
        }
        book.setId(null);
        book.setAuthor(author);
        book = bookService.createBook(book);
        logger.debug("Book has been created: {}", book);
        return ResponseEntity.ok(book);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{entryId}")
    public ResponseEntity<Book> updateBook(@ModelAttribute Book book, @PathVariable("entryId") int entryId) {
        String title = book.getTitle();
        logger.debug("Going to update book {} with id {}", book, entryId);
        Book entry = bookService.getBook(entryId);
        if (entry == null) {
            logger.info("Book with specified entry id {} doesn't exist", entryId);
            return new ResponseEntity<>(NOT_FOUND);
        }
        Author currentUser = getCurrentUser();
        entry = bookService.getByTitleAndAuthor(title, currentUser);
        if (entry != null && entry.getId() != entryId) {
            logger.debug("Book by title = {} is already exists", title);
            return new ResponseEntity<>(BAD_REQUEST);
        }
        book.setId(entryId);
        book.setAuthor(currentUser);
        book = bookService.updateBook(book);
        logger.debug("Book entry has been successfully updated: {}", book);
        return ResponseEntity.ok(book);
    }

}
