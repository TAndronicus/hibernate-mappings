package jb.service;

import cyclops.control.Eval;
import cyclops.control.Trampoline;
import cyclops.data.HList;
import org.junit.Test;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CyclopsTest {

    @Test
    public void hlist() {
        HList.HCons<Object, HList.HCons<Double, HList.HCons<String, HList.HCons<Long, HList.HNil>>>> hlist = HList.empty().prepend(1L).prepend("some string").prepend(1d).prepend(new Object());
        List<Class<?>> classes = List.of(Object.class, Double.class, String.class, Long.class);
        assertHlist(hlist, classes, 0, 3);
    }

    private void assertHlist(HList<?> hlist, List<Class<?>> classes, int index, int max) {
        if (index > max) {
            assertTrue(hlist instanceof HList.HNil);
        } else {
            assertTrue(hlist instanceof HList.HCons);
            assertTrue(classes.get(index).isInstance(((HList.HCons) hlist).head));
            assertHlist(((HList.HCons) hlist).tail, classes, index + 1, max);
        }
    }

    @Test
    public void trampolineFactorial() {
        assertEquals(BigInteger.ONE, factorial(1));
        assertEquals(BigInteger.valueOf(3_628_800), factorial(10));
        factorial(100); // stack-safe
    }

    private BigInteger factorial(int n) {
        return factorialTailrec(n, BigInteger.ONE).get();
    }

    private Trampoline<BigInteger> factorialTailrec(int n, BigInteger acc) {
        if (n < 2) return Trampoline.done(acc);
        else return Trampoline.more(Trampoline.done(factorialTailrec(n - 1, BigInteger.valueOf(n).multiply(acc))));
    }

    @Test
    public void evalCollatz() {
        System.out.println(collatz(25));
        System.out.println(collatz(50));
        System.out.println(collatz(75));
        System.out.println(collatz(1_000_000));
    }

    private Integer collatz(int number) {
        return collatzTail(number, 0).get();
    }

    private Eval<Integer> collatzTail(Integer number, int steps) {
        if (number == 1) return Eval.now(steps);
        else return Eval.now(number)
                .flatMap(n -> {
                    if (n % 2 == 0) return collatzTail(n / 2, steps + 1);
                    else return collatzTail(3 * n + 1, steps + 1);
                });
    }

}
