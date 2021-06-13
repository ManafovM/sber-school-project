package counterparties.service.validator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ValidatorUtils {
    /**
     * Создает список целых чисел из входной строки.
     *
     * @param string строка с числами
     * @return список целых чисел
     */
    static List<Integer> stringToIntegers(String string) {
        return Arrays.stream(string.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    /**
     * Считает контрольную сумму.
     *
     * @param field   список целых чисел
     * @param weights список весов
     * @return скалярное произведение.
     */
    static int getControlSum(List<Integer> field, List<Integer> weights) {
        return IntStream.range(0, weights.size())
                .map(i -> field.get(i) * weights.get(i))
                .sum();
    }
}
