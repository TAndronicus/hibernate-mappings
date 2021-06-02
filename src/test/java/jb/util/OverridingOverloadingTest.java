package jb.util;

import jb.model.BiographyBook;
import jb.model.Book;
import jb.model.ScientificBook;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class OverridingOverloadingTest {

    @Test
    public void shouldResolveStatically() {
        List<Book> books = List.of(
                new Book(),
                new BiographyBook(),
                new ScientificBook()
        );
        books.stream()
                .map(BookUtil::type)
                .allMatch("General"::equals);
    }

    @Test
    public void shouldResolveDynamically() {
        List<Book> books = List.of(
                new Book(),
                new BiographyBook(),
                new ScientificBook()
        );
        assertThat(
                books.stream()
                        .map(Book::type)
                        .collect(Collectors.toList())
        ).containsExactly(
                "General",
                "Biography",
                "Scientific"
        );
    }

}
