package jb.rest;

import jb.model.Book;
import jb.service.BookService;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThan;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
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

    @GetMapping("/books")
    public Page<Book> getBooks(
            @Or({
                    @Spec(path = "title", spec = Like.class),
                    @Spec(path = "year", spec = GreaterThan.class)
            }) Specification<Book> bookSpecification,
            Pageable pageable
    ) {
        return bookService.getBooks(bookSpecification, pageable);
    }

    @GetMapping("/booksByAuthor")
    public Page<Book> getBooksByAuthor(
            @Join(path = "authors", alias = "a")
            @Spec(path = "a.firstName", params = "firstName", spec = Like.class)
                    Specification<Book> bookSpecification,
            Pageable pageable
    ) {
        return bookService.getBooks(bookSpecification, pageable);
    }

}
