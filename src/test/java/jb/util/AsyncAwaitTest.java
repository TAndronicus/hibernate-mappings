package jb.util;

import com.ea.async.Async;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static com.ea.async.Async.await;

public class AsyncAwaitTest {

    @Before
    public void init() {
        Async.init();
    }

    @Test
    public void test() {
        if (await(randomBoolean())) {
            System.out.println("Greater");
        } else {
            System.out.println("Smaller");
        }
    }

    private CompletableFuture<Boolean> randomBoolean() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(Math.random() > .5);
    }
}
