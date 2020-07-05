package jb.service;

import jb.dao.AuthorRepository;
import jb.model.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Author getById(Long id) {
        return authorRepository.getOne(id);
    }
}
