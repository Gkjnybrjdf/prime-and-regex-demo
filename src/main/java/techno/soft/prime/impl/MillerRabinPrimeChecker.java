package techno.soft.prime.impl;

import techno.soft.prime.IPrimeChecker;

import java.math.BigInteger;
import java.util.Random;

public class MillerRabinPrimeChecker implements IPrimeChecker {
    private final Random random = new Random();

    /**
     * <p>Проверяет число на простоту используя вероятностный тест Миллера-Рабина</p>
     *
     * <p>
     * Алгоритм взят из
     * <a href="https://ru.wikipedia.org/wiki/Тест_Миллера_—_Рабина#Алгоритм_Миллера_—_Рабина">wikipedia</a>
     * </p>
     */
    @Override
    public boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }

        if (number == 2 || number == 3) {
            return true;
        }

        // Четные числа помимо "2" (последний бит = 0) не являются простыми
        if ((number & 1) == 0) {
            return false;
        }

        // Количество раундов выбирается как log2(N)
        // Поскольку    bitLength = log2(N) + 1
        // Тогда        log2(N) = bitLength - 1
        int rounds = BigInteger.valueOf(number).bitLength() - 1;

        // Разложение N - 1 = (2 в степени S) * t
        StParams stParams = calculateStParams(number);

        // Цикл по количеству раундов
        for (int i = 0; i < rounds; i++) {
            // Выполнение раунда тестирования
            boolean isProbablyPrime = isProbablyPrime(number, stParams);

            // Если тест не пройден - то число составное
            if (!isProbablyPrime) {
                return false;
            }
        }

        // Если раунды прошли успешно, то число вероятно простое
        return true;
    }

    /**
     * Выполнение раунда тестирования Миллера-Рабина числа на простоту
     */
    private boolean isProbablyPrime(int number, StParams stParams) {
        int witness = random.nextInt(2, number - 1);
        int poweredWitness = modularPow(witness, stParams.getT(), number);

        if (poweredWitness == 1 || poweredWitness == number - 1) {
            return true;
        }

        for (int j = 0; j < stParams.getS() - 1; j++) {
            poweredWitness = modularPow(poweredWitness, 2, number);

            if (poweredWitness == 1) {
                return false;
            }

            if (poweredWitness == number - 1) {
                return true;
            }
        }

        return false;
    }

    /**
     * Возведение в степень по модулю
     */
    private int modularPow(int number, int power, int modulus) {
        BigInteger powerValue = BigInteger.valueOf(power);
        BigInteger modulusValue = BigInteger.valueOf(modulus);

        return BigInteger.valueOf(number)
                .modPow(powerValue, modulusValue)
                .intValueExact();
    }

    /**
     * Представление значения (N - 1) в виде 2<sup>s</sup> * t
     */
    private StParams calculateStParams(int number) {
        // Поскольку N - нечетное, то t = N - 1 - четное
        int t = number - 1;
        int s = 0;

        // Делим t / 2 пока сохраняется четность,
        // каждое деление будет увеличивать S (степень двойки) на 1
        while ((t & 1) == 0) {
            t = t >> 1;
            s++;
        }

        return new StParams(s, t);
    }

    private static class StParams {
        private final int s;
        private final int t;

        private StParams(int s, int t) {
            this.s = s;
            this.t = t;
        }

        public int getS() {
            return s;
        }

        public int getT() {
            return t;
        }
    }
}
