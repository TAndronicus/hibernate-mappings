package jb.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MathServiceTest {

    @Autowired
//    private MathService mathService; must be compiled from xtend

    @Test
    public void returnsPrimes() {
//        assertThat(mathService.getPrimes(10))
//                .containsExactlyInAnyOrder(2, 3, 5, 7);
    }
}
