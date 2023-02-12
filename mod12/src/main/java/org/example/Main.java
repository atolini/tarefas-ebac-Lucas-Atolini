package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        task1();
        task2();
    }

    public static void task1() {
        System.out.println("TAREFA 1");
        System.out.println("Digite nome de pessoas separado por vírgula: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<String> names = new ArrayList<>(List.of(input.split(",")));
        names.sort(String::compareTo);
        names.forEach(System.out::println);
    }

    public static void task2() {
        System.out.println("TAREFA 2");
        System.out.println("Digite uma lista separada por vírgula, cada item da lista deve conter um nome traço 'm' ou " +
                "'f': ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<String> names = new ArrayList<>(List.of(input.split(",")));
        List<String> m = new ArrayList<>();
        List<String> f = new ArrayList<>();
        names.forEach((n) -> {
            List<String> temp = List.of(n.split("-"));
            switch (temp.get(1).trim()) {
                case "M" -> m.add(temp.get(0).trim());
                case "m" -> m.add(temp.get(0).trim());
                case "F" -> f.add(temp.get(0).trim());
                case "f" -> f.add(temp.get(0).trim());
            }
        });
        System.out.println("Mulheres:");
        f.forEach(System.out::println);
        System.out.println("Homens:");
        m.forEach(System.out::println);
    }
}