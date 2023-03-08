import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        MyCustomCollection registers = new MyCustomCollection();

        while (true) {
            // Apresentação Console
            System.out.print("|-----------------------------|\n");
            System.out.print("| Opção 1 - Novo Cadastro     |\n");
            System.out.print("| Opção 2 - Clientes Sexo M   |\n");
            System.out.print("| Opção 3 - Clientes Sexo F   |\n");
            System.out.print("| Opção 4 - Sair              |\n");
            System.out.print("|-----------------------------|\n");
            System.out.print("Digite uma opção: ");

            Scanner scanner = new Scanner(System.in);
            Integer option = scanner.nextInt();
            Boolean control = false;

            switch (option) {
                case 1 -> register(registers);
                case 2 -> list(Gender.MAN, registers);
                case 3 -> list(Gender.WOMAN, registers);
                case 4 -> control = true;
            }

            // Valida o fluxo
            if (control) {
                break;
            }
        }
    }

    public static void list(Gender gender, MyCustomCollection registers) {
        List<Register> result = null;

        switch (gender) {
            case MAN -> result = registers.getMan();
            case WOMAN -> result = registers.getWoman();
        }

        if (result.size() == 0) {
            System.out.println("Não há registros!");
        } else {
            System.out.println("Registros:");
        }

        for (Register r : result) {
            System.out.println(r.first);
        }

        System.out.println("______________");
    }

    public static void register(MyCustomCollection registers) {
        // Registro
        Scanner scanner = new Scanner(System.in);
        System.out.print("Por favor, informe um nome: ");
        String name = scanner.nextLine();

        // Valida a entra do genero
        Gender gender = null;
        while (true) {
            System.out.print("Digite 'M' para sexo Masculino e 'F' para sexo feminino: ");
            String letter = scanner.next();

            // Caso a entrada seja válida
            switch (letter) {
                case "M" -> gender = Gender.MAN;
                case "F" -> gender = Gender.WOMAN;
                case "m" -> gender = Gender.MAN;
                case "f" -> gender = Gender.WOMAN;
            }

            // Validação do Loop
            if (gender == null) {
                System.out.println("Sexo invalido!");
            } else {
                break;
            }
        }

        Register r1 = new Register(name, gender);
        registers.add(r1);
        System.out.println("Registro realizado!");
    }
}
