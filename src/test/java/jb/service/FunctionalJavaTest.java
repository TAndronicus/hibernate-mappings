package jb.service;

import fj.F;
import fj.F1W;
import fj.Monoid;
import fj.data.IOFunctions;
import fj.data.IOW;
import fj.data.Stream;
import org.junit.Test;

import static fj.data.IOFunctions.stdinReadLine;
import static fj.data.IOFunctions.stdoutPrintln;
import static org.junit.Assert.assertEquals;

public class FunctionalJavaTest {

    public static void main(String[] args) {
        IOW.lift(stdoutPrintln("Hi"))
                .append(stdoutPrintln("Name:"))
                .append(stdinReadLine())
                .bind(F1W.lift((F<String, String>) String::toUpperCase).andThen(IOFunctions::stdoutPrintln))
                .safe()
                .run();
    }

    @Test
    public void shouldCalculateSumOfPowers() {
        assertEquals(
                Math.pow(2, 10) - 1,
                Stream.range(0, 10)
                        .mapM((F<Integer, F<Double, Double>>) power -> base -> Math.pow(base, power))
                        .f(2d)
                        .foldLeft(Monoid.doubleAdditionMonoid.sum(), Monoid.doubleAdditionMonoid.zero()),
                10e-6
        );
    }

}
