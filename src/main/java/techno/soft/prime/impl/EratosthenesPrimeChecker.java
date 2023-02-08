package techno.soft.prime.impl;

import techno.soft.prime.IPrimeChecker;

import java.util.stream.IntStream;

public class EratosthenesPrimeChecker implements IPrimeChecker {

    /**
     * Проверяет число на простоту используя алгоритм решета Эратосфена
     */
    @Override
    public boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }

        // Решето из [0, N] чисел
        boolean[] filtered = new boolean[number + 1];

        // Подготовка множества [2, sqrt(N)] корневых чисел
        IntStream.rangeClosed(2, (int) Math.sqrt(number))
                // Пропускаем корни если их уже посетили
                .filter(val -> !filtered[val])
                // Начинаем цикл от каждого корня
                .flatMap(val -> IntStream.iterate(
                        val * val,
                        iterVal -> iterVal <= number,
                        iterVal -> iterVal + val
                ))
                // Обозначаем элементы цикла как отфильтрованные в решете
                .forEach(val -> filtered[val] = true);

        // Если значение не отфильтровано, то оно простое
        return !filtered[number];
    }
}
