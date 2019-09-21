package com.ibragimov.library.dao;

import com.ibragimov.library.Application;
import com.ibragimov.library.model.Author;
import com.ibragimov.library.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void findByAuthorNotFound() {
        Author author = new Author(100, "test", null, null);
        assertTrue(bookRepository.findByAuthor(author).isEmpty());
    }

    @Test
    public void findByAuthorFound() {
        List<Book> expected = new ArrayList<>();
        Author author = new Author(1, "admin", null, null);
        expected.add(new Book(1, "A", author));
        expected.add(new Book(2, "B", author));
        expected.add(new Book(3, "C", author));

        List<Book> actual = bookRepository.findByAuthor(author);
        assertEquals(expected, actual);
    }

    @Test
    public void findByTitleContainingIgnoreCaseNotFound() {
        assertTrue(bookRepository.findByTitleContainingIgnoreCase("test").isEmpty());
    }

    @Test
    public void findByTitleContainingIgnoreCaseFound() {
        List<Book> expected = new ArrayList<>();
        Author author = new Author(1, "admin", null, null);
        expected.add(new Book(1, "A", author));
        List<Book> actual = bookRepository.findByTitleContainingIgnoreCase("A");

        assertEquals(expected, actual);
    }

    @Test
    public void findByTitleAndAuthorNotFound() {
        Author author = new Author(1, "admin", null, null);
        String title = "test";

        assertNull(bookRepository.findByTitleAndAuthor(title, author));
    }

    @Test
    public void findByTitleAndAuthorFound() {
        Author author = new Author(1, "admin", null, null);
        Book expected = new Book(1, "A", author);
        Book actual = bookRepository.findByTitleAndAuthor("A", author);

        assertEquals(expected, actual);
    }

}
