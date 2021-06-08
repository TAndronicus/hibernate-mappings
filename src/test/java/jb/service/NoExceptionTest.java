package jb.service;

import com.machinezoo.noexception.Exceptions;
import com.machinezoo.noexception.WrappedException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class NoExceptionTest {

    @Test
    public void shouldLogException() {
        Exceptions.log()
                .fromObjDoubleConsumer((o, d) -> System.out.println(d > 0 ? o.toString() : "other"))
                .accept(null, 1d);
    }

    @Test
    public void shouldProvideDefault() {
        assertEquals(
                "Default",
                Exceptions.silence()
                        .get(() -> {
                            throw new IllegalStateException();
                        })
                        .orElse("Default")
        );
    }

    @Test
    public void shouldIgnoreCheckedException() {
        Exceptions.sneak()
                .fromBooleanSupplier(this::throwingException);
    }

    @Test(expected = WrappedException.class)
    public void wrapInUnchecked() {
        Exceptions.wrap()
                .fromBooleanSupplier(this::throwingException)
                .getAsBoolean();
    }

    private boolean throwingException() throws IOException {
        throw new IOException();
    }

}
