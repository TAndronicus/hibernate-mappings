package jb.dao;

import org.junit.Test;

import static java.lang.Math.pow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.doubles;
import static org.quicktheories.generators.SourceDSL.integers;

public class QuickTheoriesTest {

    @Test
    public void withAssert() {
        qt()
                .forAll(integers().all(), integers().all())
                .checkAssert((a, b) -> assertTrue(pow(a, 2) + pow(b, 2) <= pow(a + b, 2)));
    }

    @Test
    public void withAssumption() {
        qt()
                .forAll(integers().allPositive(), doubles().positive())
                .asWithPrecursor((a, b) -> b - 1)
                .check((a, bPrev, b) -> pow((1 + b), a) >= 1 + a * b);
    }

}
