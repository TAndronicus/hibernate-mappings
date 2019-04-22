package jb.model;

import javax.persistence.*;

@Entity
public class Review extends AbstractEntity {

    private Short grade;
    private String comment;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    public Review(Short grade, String comment, Book book) {
        this.grade = grade;
        this.comment = comment;
        this.book = book;
    }

    public Review(Short grade, String comment) {
        this.grade = grade;
        this.comment = comment;
    }

    public Review() {
    }

    public Short getGrade() {
        return grade;
    }

    public void setGrade(Short grade) {
        this.grade = grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

}
