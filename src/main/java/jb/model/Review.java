package jb.model;

import javax.persistence.*;

@Entity
public class Review extends AbstractEntity {

    private Short grade;
    private String reviewComment;
    @ManyToOne(cascade = CascadeType.ALL)
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

    public Short getGrade() {
        return grade;
    }

    public void setGrade(Short grade) {
        this.grade = grade;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

}
