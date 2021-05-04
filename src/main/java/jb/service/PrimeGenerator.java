package jb.service;

import com.google.mu.util.stream.Iteration;

public class PrimeGenerator extends Iteration<Integer> {
	private static boolean isPrime(int num) {
		for (int i = 2; i <= (int) Math.sqrt(num); i++) {
			if (num % i == 0) return false;
		}
		return true;
	}

	PrimeGenerator next(int current) {
		if (isPrime(current)) yield(current);
		yield(() -> next(current + 1));
		return this;
	}
}
