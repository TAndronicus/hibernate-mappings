package jb.service;

import jb.dao.ReviewRepository;
import jb.model.Book;
import jb.model.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public void createMockData() {
        Random random = new Random();
        Book book = new Book("book_title");
        for (int i = 0; i < 10; i++) {
            Review review = new Review((short) random.nextInt(6),
                    UUID.randomUUID().toString().replace('-', ' '),
                    book);
            reviewRepository.save(review);
        }
        reviewRepository.flush();
    }

}
