package jb.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Book extends AbstractEntity {

    private String title;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "author_book",
    joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private Set<Author> authors;

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Book title(final String title) {
        this.title = title;
        return this;
    }

    public Book authors(final Set<Author> authors) {
        this.authors = authors;
        return this;
    }

}
