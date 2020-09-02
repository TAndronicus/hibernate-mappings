package jb.service;

import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
class MathService {

    def getPrimes(int n) {
        IntStream.range(2, n + 1)
                .filter([i | isPrime(i)])
                .boxed()
                .collect(Collectors.toList());
    }

    private def isPrime(int i) {
        IntStream.range(2, Math.floor(Math.sqrt(i)) as int + 1)
                .noneMatch([divisor | i % divisor == 0]);
    }
}
