package jb.rest;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.restassured.RestAssured.post;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthorRestTest {

    @Test
    @Ignore("throws internal errors")
    public void test() {
        post("/author/grade/1/5")
                .then()
                .assertThat()
                .statusCode(404);
    }

}
