package com.ibragimov.library.service;

import com.ibragimov.library.dao.AuthorRepository;
import com.ibragimov.library.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author getById (Integer id) {
        return authorRepository.findOne(id);
    }

    @Transactional
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Transactional
    @Secured("ROLE_ADMIN")
    public Author updateAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Iterable<Author> getBySubstring(String pattern) {
        return pattern == null ? authorRepository.findAll() : authorRepository.findByNameContainingIgnoreCase(pattern);
    }

    public Author getByName(String name) {
        return authorRepository.findByName(name);
    }

}
