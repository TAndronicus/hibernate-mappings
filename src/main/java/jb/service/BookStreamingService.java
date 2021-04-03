package jb.service;

import com.speedment.jpastreamer.application.JPAStreamer;
import jb.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookStreamingService {

    private final JPAStreamer jpaStreamer;

    public List<Book> getBooksPage(int pageNumber, int pageSize) {
        return jpaStreamer.stream(Book.class)
                .skip((long) (pageNumber - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

}
