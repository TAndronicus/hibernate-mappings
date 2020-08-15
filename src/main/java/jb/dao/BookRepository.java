package jb.dao;

import jb.model.Book;
import org.checkerframework.checker.optional.qual.Present;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    List<Book> findBooksByTitleLike(String title);

    @Override
    @Present Optional<Book> findById(Long aLong);

}
