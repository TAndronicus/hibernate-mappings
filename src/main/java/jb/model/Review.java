package jb.model;

import javax.persistence.Entity;

//@Entity
public class Review extends AbstractEntity {

    private short grade;
    private String comment;
    private Book book;

    public Review(short grade, String comment, Book book) {
        this.grade = grade;
        this.comment = comment;
        this.book = book;
    }

    public Review(short grade, String comment) {
        this.grade = grade;
        this.comment = comment;
    }

    public Review() {
    }

    public short getGrade() {
        return grade;
    }

    public void setGrade(short grade) {
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
