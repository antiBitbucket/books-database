package com.ibragimov.library.controllers;

import com.ibragimov.library.model.Author;
import com.ibragimov.library.model.UserRole;
import com.ibragimov.library.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/rest/authors/", produces = APPLICATION_JSON_UTF8_VALUE)
public class AuthorController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    private AuthorService authorService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Author> getAuthors(@RequestParam(value = "filter", required = false) String filter) {
        logger.debug("Going to get author list by filter {}", filter);
        return authorService.getBySubstring(filter);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{entryId}")
    public ResponseEntity<Author> updateAuthor(@PathVariable("entryId") int authorId,
                                               @RequestParam("role") UserRole role,
                                               @RequestParam(value = "password", required = false) String password) {
        logger.debug("Going to change role of the author {} to {}", authorId, role);
        Author author = authorService.getById(authorId);
        if (author == null) {
            logger.warn("No author by id {} has been found", authorId);
            return new ResponseEntity<>(NOT_FOUND);
        }
        author.setRole(role);
        if (password != null) {
            logger.debug("Author's password will be reset {}", author);
            author.setPassword(passwordEncoder.encode(password));
        }
        author = authorService.updateAuthor(author);
        logger.debug("Author has been successfully updated: {}", author);
        return ResponseEntity.ok(author);
    }

}
