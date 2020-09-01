package jb.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MathService {

    public List<Integer> getPrimes(int n) {
        return IntStream.range(2, n + 1)
                .filter(this::isPrime)
                .boxed()
                .collect(Collectors.toList());
    }

    private boolean isPrime(int i) {
        return IntStream.range(2, (int) Math.sqrt(i) + 1)
                .noneMatch(divisor -> i % divisor == 0);
    }
}
