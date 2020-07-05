package jb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PopulatingService {

    /*
    private final EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void populateDB() {
        var entityManager = entityManagerFactory.createEntityManager();
        var trx = entityManager.getTransaction();
        trx.begin();
        var author1 = Author.builder()
                .firstName("First")
                .lastName("Author")
                .build();
        var author2 = Author.builder()
                .firstName("Second")
                .lastName("Author")
                .build();
        var book1 = Book.builder()
                .title("Life of Second author")
                .year(2010)
                .authors(Collections.singleton(author1))
                .build();
        var book2 = Book.builder()
                .title("Life of First author")
                .year(2015)
                .authors(Collections.singleton(author2))
                .build();
        var book3 = Book.builder()
                .title("Together forever")
                .year(2020)
                .authors(Set.of(author1, author2))
                .build();
        author1.books(Set.of(book1, book3));
        author2.books(Set.of(book2, book3));
        entityManager.persist(author1);
        entityManager.persist(author2);
//        entityManager.persist(book1);
//        entityManager.persist(book2);
//        entityManager.persist(book3);
        trx.commit();
    }
     */

}
