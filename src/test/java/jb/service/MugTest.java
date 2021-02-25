package jb.service;

import com.google.mu.util.stream.BiStream;
import com.google.mu.util.stream.MoreStreams;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class MugTest {

	@Test
	public void biStreamFromList() {
		var bs = BiStream.from(
			List.of(1, 2, 3, 4, 5, 6),
			Function.identity(),
			i -> Math.pow(2, i)
		)
			.map((i, j) -> j - 1, (i, j) -> i)
			.toMap();
		assertEquals(2., bs.get(3d), 10e-6);
	}

	@Test
	public void bsFromMap() {
		var map = Map.of(
			1, 2,
			2, 3,
			3, 5,
			4, 7,
			5, 11,
			6, 13,
			7, 17,
			8, 19
		);
		var mersenne = BiStream.from(map)
			.map((i, j) -> j, (i, j) -> Math.log(j + 1) / Math.log(2))
			.filterValues(j -> Math.abs(j - j.intValue()) < 10e-6)
			.toMap();
		assertEquals(2, mersenne.size());
	}

	@Test
	public void dice() {
		var batches = MoreStreams.dice(IntStream.range(0, 20).boxed(), 5)
			.count();
		assertEquals(4l, batches);
	}

	@Test
	public void shouldGeneratePrimes() {
		var primesCnt = new PrimeGenerator().next(2).iterate()
			.limit(20)
			.takeWhile(i -> i < 20)
			.count();
		assertEquals(8l, primesCnt);
	}

}
