package techno.soft.prime;

import java.util.stream.Stream;

public interface IPrimeNumberManager {
    /**
     * Конвертирует массив чисел в поток результатов их проверки на простоту
     */
    Stream<Boolean> arePrimes(int[] inputInts);

    /**
     * Фильтрует поток чисел, удаляя из него все не простые числа
     */
    Stream<Integer> primesOnly(Stream<Integer> inputInts);

    /**
     * Находит в потоке чисел максимальное простое число
     */
    Integer maxPrime(Stream<Integer> inputInts);
}
