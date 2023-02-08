package techno.soft.prime;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.commons.math3.primes.Primes;
import techno.soft.prime.impl.EratosthenesPrimeChecker;

import java.util.stream.IntStream;


public class EratosthenesPrimeCheckerTest extends TestCase {
    private final IPrimeChecker checker = new EratosthenesPrimeChecker();

    public void test_single_sample() {
        var prime = 1453;

        Assert.assertTrue(checker.isPrime(prime));
    }

    public void test_multiple_samples() {
        var samples = 100_000;

        IntStream.range(0, samples)
                .forEach(value -> Assert.assertEquals(
                        String.valueOf(value),
                        Primes.isPrime(value),
                        checker.isPrime(value))
                );
    }
}
