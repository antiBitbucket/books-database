package com.ibragimov.library.dao;

import com.ibragimov.library.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {

    Author findByName(String name);

    List<Author> findByNameContainingIgnoreCase(String pattern);

}
