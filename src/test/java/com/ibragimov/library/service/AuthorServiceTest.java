package com.ibragimov.library.service;

import com.ibragimov.library.dao.AuthorRepository;
import com.ibragimov.library.model.Author;
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
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;
    @InjectMocks
    private AuthorService authorService;

    @Test
    public void getByIdTest() {
        Author author = new Author("admin", null, null);
        when(authorRepository.findOne(1)).thenReturn(author);
        Author actual = authorService.getById(1);
        assertEquals(author, actual);
        verify(authorRepository, times(1)).findOne(1);
    }

    @Test
    public void createAuthorTest() {
        Author author = new Author("admin", null, null);
        when(authorRepository.save(author)).thenReturn(author);
        Author actual = authorService.createAuthor(author);
        assertEquals(author, actual);
        verify(authorRepository, times(1)).save(author);
    }

    @Test
    public void updateAccountTest() {
        Author author = new Author("admin", null, null);
        authorService.updateAuthor(author);
        verify(authorRepository).save(author);
    }

    @Test
    public void getBySubstringTest() {
        List<Author> expected = new ArrayList<>();
        expected.add(new Author("Ivanov", null, null));
        expected.add(new Author("Sidorov", null, null));
        when(authorRepository.findByNameContainingIgnoreCase("ov")).thenReturn(expected);
        List<Author> actual = (List<Author>) authorService.getBySubstring("ov");
        assertEquals(expected, actual);
        verify(authorRepository, times(1)).findByNameContainingIgnoreCase("ov");
    }

    @Test
    public void getByNameTest() {
        Author author = new Author("admin", null, null);
        when(authorRepository.findByName("admin")).thenReturn(author);
        Author actual = authorService.getByName("admin");
        assertEquals(author, actual);
        verify(authorRepository, times(1)).findByName("admin");
    }

}
