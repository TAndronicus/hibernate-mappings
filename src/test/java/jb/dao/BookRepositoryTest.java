package jb.dao;

import jb.model.Book;
import jb.model.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void shouldSaveData() {
        Book nst = new Book("The nature of space and time");

        Review r1 = new Review((short) 3, "meh", nst);
        reviewRepository.save(r1);
        Review r2 = new Review((short) 4, "not best but alright");
        r2 = reviewRepository.save(r2);
        r2.setBook(nst);
        reviewRepository.save(r2);
        Review r3 = new Review((short) 5, "best ever");
        r3 = reviewRepository.save(r3);
        r3.setBook(nst);
        reviewRepository.save(r3);

        Book book = entityManager.createQuery(
                "select b " +
                        "from " + Book.class.getName() + " b " +
                        "left join fetch b.reviews " +
                        "where b.id = :id", Book.class)
                .setParameter("id", nst.getId())
                .getSingleResult();
        assertNotNull(book);

        Map<Long, Review> reviews = book.getReviews();
        assertThat(reviews.size(), is(equalTo(3)));
    }

}
