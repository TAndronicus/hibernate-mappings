package jb.util;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

import static org.awaitility.Awaitility.await;
import static org.junit.Assert.assertTrue;

public class AwaitilityTest {

    private final LongAdder longAdder = new LongAdder();

    @Test
    public void test() throws Exception {
        CompletableFuture.runAsync(this::startUpdatingAdder);
        await().pollInterval(100, TimeUnit.MILLISECONDS).until(() -> longAdder.sum() > 5);
        assertTrue(longAdder.sum() > 5);
    }

    private void startUpdatingAdder() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            longAdder.add(1);
            System.out.println(longAdder.sum() % 2 == 1 ? "Tick" : "Tock");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
