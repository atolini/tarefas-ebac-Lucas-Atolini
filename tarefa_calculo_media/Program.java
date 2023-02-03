import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        List<Integer> numbers = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            System.out.printf("Informe um número inteiro:\n");
            Scanner scanner = new Scanner(System.in);
            try {
                Integer input = scanner.nextInt();
                numbers.add(input);
            } catch(InputMismatchException e) {
                i--;
                System.out.println("Número invalido, informe um número inteiro positivo.");
            }
        }
        Double result = calculator.averageArithmetic(numbers);
        System.out.printf("A média é: %s", result);
    }
}
