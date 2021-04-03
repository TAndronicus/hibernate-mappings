package jb.service;

import jb.model.AbstractEntity;
import jb.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = {"classpath:db/books.sql"})
public class BookStreamingServiceTest {

    @Autowired
    private BookStreamingService bookStreamingService;

    @Test
    public void shouldStream() {
        List<Book> books = bookStreamingService.getBooksPage(2, 2);
        assertFalse(books.isEmpty());
        assertEquals(
                List.of(3L, 4L),
                books.stream()
                        .map(AbstractEntity::getId)
                        .collect(Collectors.toList())
        );
    }
}
