package jb.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Author extends AbstractEntity {

    private String firstName;

    private String lastName;

//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "authors")
//    private Set<Book> books;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    public Set<Book> getBooks() {
//        return books;
//    }
//
//    public void setBooks(Set<Book> books) {
//        this.books = books;
//    }

    public Author firstName(final String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Author lastName(final String lastName) {
        this.lastName = lastName;
        return this;
    }

//    public Author books(final Set<Book> books) {
//        this.books = books;
//        return this;
//    }

}
