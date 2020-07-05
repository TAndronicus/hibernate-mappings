package jb.rest;

import jb.model.Book;
import jb.service.BookService;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThan;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookRest {

    private final BookService bookService;

    @GetMapping
    public Page<Book> getBooks(
            @Or({
                    @Spec(path = "title", spec = Equal.class),
                    @Spec(path = "year", spec = GreaterThan.class)
            }) Specification<Book> bookSpecification,
            Pageable pageable
    ) {
        return bookService.getBooks(bookSpecification, pageable);
    }

}
