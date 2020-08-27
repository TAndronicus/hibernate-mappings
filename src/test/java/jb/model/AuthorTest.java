package jb.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorTest {

    @Test
    public void testEquals() {
        EqualsVerifier
                .configure()
                .withPrefabValues(Book.class, new Book(), new Book())
                .suppress(Warning.NONFINAL_FIELDS, Warning.SURROGATE_KEY)
                .forClass(Author.class)
                .verify();
    }

}
