package jb.service;

import jb.dao.BookRepository;
import jb.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Page<Book> getBooks(Specification<Book> bookSpecification, Pageable pageable) {
        return bookRepository.findAll(bookSpecification, pageable);
    }

    public Book getById(Long bookId) {
        return bookRepository.findById(bookId)
                .get();
    }

}
