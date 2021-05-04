package jb.service;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class TikaServiceTest {

    Tika tika = new Tika();

    @Test
    public void shouldDetect() {
        assertEquals(
                "text/plain",
                tika.detect("plain.txt")
        );
        assertEquals(
                "image/png",
                tika.detect("cert.png")
        );
        assertEquals(
                "application/pdf",
                tika.detect("measurements.pdf")
        );
        assertEquals(
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                tika.detect("oceny.xlsx")
        );
    }

    @Test
    @Ignore
    public void shouldParseAutomatically() throws IOException, TikaException {
        InputStream inputStream = Files.newInputStream(Paths.get("src/test/resources/plain.txt"));
        System.out.println(tika.parseToString(inputStream));
        System.out.println(tika.parseToString(Files.newInputStream(Paths.get("src/test/resources/measurements.pdf"))));
    }

}
