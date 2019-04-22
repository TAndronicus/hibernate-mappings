package jb.model;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
public class Book extends AbstractEntity {

    private String title;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "author_book",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private Set<Author> authors;

    @OneToMany(mappedBy = "book")
    @MapKey
    private Map<Long, Review> reviews;
//    @OneToMany(mappedBy = "book")
//    private List<Review> reviews;

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

    public Map<Long, Review> getReviews() {
        return reviews;
    }

    public void setReviews(Map<Long, Review> reviews) {
        this.reviews = reviews;
    }


//    public List<Review> getReviews() {
//        return reviews;
//    }
//
//    public void setReviews(List<Review> reviews) {
//        this.reviews = reviews;
//    }

    public Book title(final String title) {
        this.title = title;
        return this;
    }

    public Book authors(final Set<Author> authors) {
        this.authors = authors;
        return this;
    }

    public Book() {
    }
}
