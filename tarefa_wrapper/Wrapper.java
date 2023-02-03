import java.util.Scanner;

public class Wrapper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Informe um número inteiro:\n");
        int input = scanner.nextInt();
        Integer inputWraper = input;
        System.out.println(inputWraper);
    }
}
