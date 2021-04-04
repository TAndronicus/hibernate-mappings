package jb.service;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.constraint.CharSequenceConstraint;
import am.ik.yavi.constraint.MapConstraint;
import am.ik.yavi.core.Validator;
import jb.model.Author;
import jb.model.Book;
import jb.model.Review;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class YaviTest {

    Validator<Book> bookValidator = ValidatorBuilder.<Book>of()
            ._string(Book::getTitle, "title", CharSequenceConstraint::notBlank)
            ._integer(Book::getYear, "year", c -> c.notNull()
                    .greaterThan(1900)
                    .lessThan(2022))
            ._collection(Book::getAuthors, "authors", c -> c.notNull()
                    .notEmpty())
            ._map(Book::getReviews, "reviews", MapConstraint::notNull)
            .build();

    Validator<Review> reviewValidator = ValidatorBuilder.<Review>of()
            ._short(Review::getGrade, "grade", c -> c.greaterThan((short) 0).lessThanOrEqual((short) 5))
            .nest(Review::getBook, "book", bookValidator)
            .build();

    Book validBook = new Book("Some title", 2000, Collections.singleton(new Author("First", "Last")), Collections.emptyMap());

    @Test
    public void shouldValidateEntity() {
        assertFalse(bookValidator.validate(new Book("Just a title")).isValid());
        assertFalse(bookValidator.validate(new Book("Some title", 1500, Collections.emptySet(), Collections.emptyMap())).isValid());
        assertTrue(bookValidator.validate(validBook).isValid());
    }

    @Test
    public void shouldValidateNestedEntity() {
        assertFalse(reviewValidator.validate(new Review()).isValid());
        assertFalse(reviewValidator.validate(new Review((short) 0, null, validBook)).isValid());
        assertFalse(reviewValidator.validate(new Review((short) 1, null, new Book())).isValid());
        assertTrue(reviewValidator.validate(new Review((short) 1, null, validBook)).isValid());
    }

}
