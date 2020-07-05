package jb;

import com.vladmihalcea.sql.SQLStatementCountValidator;
import jb.service.ReviewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class HibernateMappingsApplicationTests {

    @Autowired
    private ReviewService reviewService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void findNPlusOne() {
        SQLStatementCountValidator.reset();
        reviewService.createMockData();
        SQLStatementCountValidator.assertInsertCount(0);
        SQLStatementCountValidator.assertSelectCount(0);
        SQLStatementCountValidator.assertDeleteCount(0);
        SQLStatementCountValidator.assertUpdateCount(0);
    }

}
