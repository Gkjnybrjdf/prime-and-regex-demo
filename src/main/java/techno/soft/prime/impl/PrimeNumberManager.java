package techno.soft.prime.impl;

import techno.soft.prime.IPrimeChecker;
import techno.soft.prime.IPrimeNumberManager;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class PrimeNumberManager implements IPrimeNumberManager {
    private final IPrimeChecker primeChecker;

    public PrimeNumberManager(IPrimeChecker primeChecker) {
        this.primeChecker = primeChecker;
    }

    @Override
    public Stream<Boolean> arePrimes(int[] inputInts) {
        return Arrays
                .stream(inputInts)
                .mapToObj(primeChecker::isPrime);
    }

    @Override
    public Stream<Integer> primesOnly(Stream<Integer> inputInts) {
        return inputInts
                .filter(primeChecker::isPrime);
    }

    @Override
    public Integer maxPrime(Stream<Integer> inputInts) {
        return primesOnly(inputInts)
                .max(Comparator.nullsFirst(Comparator.naturalOrder()))
                .orElse(null);
    }
}
