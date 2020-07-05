package jb.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Review extends AbstractEntity {

    private Short grade;
    private String reviewComment;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    public Review(Short grade, String reviewComment, Book book) {
        this.grade = grade;
        this.reviewComment = reviewComment;
        this.book = book;
    }

    public Review(Short grade, String reviewComment) {
        this.grade = grade;
        this.reviewComment = reviewComment;
    }

    public Review() {
    }

}
