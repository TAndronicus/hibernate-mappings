package jb.service;

import org.agrona.collections.MutableBoolean;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertTrue;

public class AgronaTest {

    @Test
    public void testMutableBoolean() {
        MutableBoolean mutableBoolean = new MutableBoolean(false);
        IntStream.range(10, 20)
                .map(i -> {
                    if (i % 7 == 0) mutableBoolean.set(true);
                    return Math.floorMod(i, 5);
                })
                .forEach(System.out::println);
        assertTrue(mutableBoolean.get());
    }

}
