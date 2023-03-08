import java.util.List;

public class Calculator {
    public Double averageArithmetic(List<Integer> numbers) {
        Integer sum = 0;
        if (numbers.size() > 0) {
            for (Integer i : numbers) {
                sum += i;
            }
        }
        return numbers.size() > 0 ? sum / numbers.size() : 0.0;
    }
}
