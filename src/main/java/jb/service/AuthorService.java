package jb.service;

import jb.dao.AuthorRepository;
import jb.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author getById(Long id) {
        return authorRepository.getOne(id);
    }
}
