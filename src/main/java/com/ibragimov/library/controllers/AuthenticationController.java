package com.ibragimov.library.controllers;

import com.ibragimov.library.model.Author;
import com.ibragimov.library.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Objects;

import static com.ibragimov.library.config.SecurityUtils.getCurrentUser;
import static com.ibragimov.library.model.UserRole.USER;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthorService authorService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/")
    public String index() {
        logger.debug("Home page request received");
        return "redirect:/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Author> register(@ModelAttribute Author author,
                                           @RequestParam("password2") String approvedPassword) {
        String username = author.getUsername();
        String password = author.getPassword();
        logger.debug("Going to create new user for {}", username);
        if (!Objects.equals(password, approvedPassword)) {
            logger.debug("Password does not match the confirm password");
            return new ResponseEntity<>(BAD_REQUEST);
        }
        Author newAuthor = authorService.getByName(username);
        if (newAuthor != null) {
            logger.debug("Account with specified username {} already exists", username);
            return new ResponseEntity<>(BAD_REQUEST);
        }
        newAuthor = authorService.createAuthor(new Author(username, passwordEncoder.encode(password), USER));
        logger.debug("User has been created: {}", newAuthor);
        return ResponseEntity.ok(newAuthor);
    }

}
