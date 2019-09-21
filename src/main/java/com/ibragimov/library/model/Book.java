package com.ibragimov.library.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "BOOK_TBL")
public class Book extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Name can't be empty")
    @Size(max = 20, message = "Title length should be max 20 symbols")
    private String title;
    @ManyToOne
    private Author author;

    public Book() {
    }

    public Book(Integer id, String title, Author author) {
        setId(id);
        this.title = title;
        this.author = author;
    }

    public Book(String title, Author author) {
        this(null, title, author);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }
        Book book = (Book) o;
        return Objects.equals(getId(), book.getId()) &&
                Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), title);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id= '" + getId() + '\'' +
                "title='" + title + '\'' +
                ", author=" + author +
                '}';
    }

}
