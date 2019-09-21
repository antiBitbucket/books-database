package com.ibragimov.library.service;

import com.ibragimov.library.dao.BookRepository;
import com.ibragimov.library.model.Author;
import com.ibragimov.library.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookService bookService;

    @Test
    public void getByIdTest() {
        Author author = new Author("admin", null, null);
        Book book = new Book("A", author);
        when(bookRepository.findOne(1)).thenReturn(book);
        Book actual = bookService.getBook(1);
        assertEquals(book, actual);
        verify(bookRepository, times(1)).findOne(1);
    }

    @Test
    public void createBookTest() {
        Author author = new Author("admin", null, null);
        Book book = new Book("A", author);
        when(bookRepository.save(book)).thenReturn(book);
        Book actual = bookService.createBook(book);
        assertEquals(book, actual);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void updateAccountTest() {
        Author author = new Author("admin", null, null);
        Book book = new Book("A", author);
        bookService.updateBook(book);
        verify(bookRepository).save(book);
    }

    @Test
    public void getByAuthorTest() {
        Author author = new Author("admin", null, null);
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(1, "A", author));
        expected.add(new Book(2, "B", author));
        expected.add(new Book(3, "C", author));
        when(bookRepository.findByAuthor(author)).thenReturn(expected);
        List<Book> actual = bookService.getByAuthor(author);
        assertEquals(expected, actual);
        verify(bookRepository, times(1)).findByAuthor(author);
    }

    @Test
    public void getByTitleAndAuthorTest() {
        Author author = new Author("admin", null, null);
        Book expected = new Book(1, "A", author);
        when(bookRepository.findByTitleAndAuthor("A", author)).thenReturn(expected);
        Book actual = bookService.getByTitleAndAuthor("A", author);
        assertEquals(expected, actual);
        verify(bookRepository, times(1)).findByTitleAndAuthor("A", author);
    }

}
