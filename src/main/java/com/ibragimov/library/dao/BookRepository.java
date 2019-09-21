package com.ibragimov.library.dao;

import com.ibragimov.library.model.Author;
import com.ibragimov.library.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findByAuthor(Author author);

    List<Book> findByTitleContainingIgnoreCase(String pattern);

    Book findByTitleAndAuthor(String title, Author author);

}
