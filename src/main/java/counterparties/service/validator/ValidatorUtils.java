package counterparties.service.validator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ValidatorUtils {
    static List<Integer> stringToIntegers(String string) {
        return Arrays.stream(string.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    static int getControlSum(List<Integer> field, List<Integer> weights) {
        return IntStream.range(0, weights.size())
                .map(i -> field.get(i) * weights.get(i))
                .sum();
    }
}
