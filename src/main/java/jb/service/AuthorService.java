package jb.service;

import jb.dao.AuthorRepository;
import jb.model.Author;
import lombok.RequiredArgsConstructor;
import org.checkerframework.common.value.qual.IntRange;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Author getById(@IntRange(from = 0, to = 5) Long id) {
        return authorRepository.getOne(id);
    }
}
