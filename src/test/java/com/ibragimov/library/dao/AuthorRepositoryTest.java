package com.ibragimov.library.dao;

import com.ibragimov.library.Application;
import com.ibragimov.library.model.Author;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.ibragimov.library.model.UserRole.ADMIN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void findByNameNotFound() {
        assertNull(authorRepository.findByName("test"));
    }

    @Test
    public void findByNameFound() {
        Author expected = new Author("admin", null, ADMIN);

        Author actual = authorRepository.findByName("admin");

        assertEquals(expected, actual);
        assertEquals(expected.getRole(), actual.getRole());
    }

    @Test
    public void findByNameContainingIgnoreCaseEmptyList() {
        List<Author> actual = authorRepository.findByNameContainingIgnoreCase("es");

        assertNotNull(actual);
        assertTrue(actual.isEmpty());
    }

    @Test
    public void findByNameContainingIgnoreCaseEmptyString() {
        List<Author> expected = new ArrayList<>();
        expected.add(new Author("admin", null, null));
        expected.add(new Author("user1", null, null));
        expected.add(new Author("user2", null, null));
        expected.add(new Author("user3", null, null));
        expected.add(new Author("user4", null, null));

        List<Author> actual = authorRepository.findByNameContainingIgnoreCase("");

        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        assertTrue(expected.containsAll(actual));
    }

    @Test
    public void findByNameContainingIgnoreCaseFound() {
        List<Author> expected = new ArrayList<>();
        expected.add(new Author("user1", null, null));
        expected.add(new Author("user2", null, null));
        expected.add(new Author("user3", null, null));
        expected.add(new Author("user4", null, null));

        List<Author> actual = authorRepository.findByNameContainingIgnoreCase("sEr");

        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        assertTrue(expected.containsAll(actual));
    }

}
