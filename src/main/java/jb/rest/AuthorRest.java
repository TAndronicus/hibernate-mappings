package jb.rest;

import jb.dao.AuthorRepository;
import jb.model.Author;
import jb.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorRest {

    private final AuthorService authorService;
    private final AuthorRepository authorRepository;

    @GetMapping("/author/{id}")
    public Author getById(@PathVariable Long id) {
        return authorService.getById(id);
    }

    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @PostMapping("/author")
    public Author putAuthor(@RequestBody Author author) {
        return authorRepository.save(author);
    }

}
