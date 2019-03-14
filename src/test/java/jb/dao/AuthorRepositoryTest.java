package jb.dao;

import jb.model.Author;
import jb.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @Transactional
    public void shouldLazyLoadToMany() {
        Author hawking = new Author("Stephen", "Hawking");
        Author penrose = new Author("Roger", "Penrose");

        hawking = authorRepository.save(hawking);
        penrose = authorRepository.save(penrose);

        Author finalHawking = hawking;
        Author finalPenrose = penrose;

        Book nst = new Book("The nature of space and time");
        Book rr = new Book("The road to reality");
        Book bht = new Book("Brief history of Time");

        nst = bookRepository.save(nst);
        rr = bookRepository.save(rr);
        bht = bookRepository.save(bht);

        nst.authors(new HashSet<Author>() {{
            add(finalHawking);
            add(finalPenrose);
        }});
        rr.authors(new HashSet<Author>() {{
            add(finalPenrose);
        }});
        bht.authors(new HashSet<Author>() {{
            add(finalHawking);
        }});

        Book finalNst = nst;
        Book finalBht = bht;
        Book finalRr = rr;
        hawking.setBooks(new HashSet<Book>() {{
            add(finalNst);
            add(finalBht);
        }});
        penrose.setBooks(new HashSet<Book>() {{
            add(finalNst);
            add(finalRr);
        }});

        List<Book> spaceBooks = bookRepository.findBooksByTitleLike("space");
        assertThat(spaceBooks.size(), is(equalTo(1)));
        assertThat(spaceBooks.get(0).getAuthors().size(), is(equalTo(2)));
    }
}