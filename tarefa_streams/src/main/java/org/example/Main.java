package org.example;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // Atomic Integer
        AtomicInteger count = new AtomicInteger(0);

        // Criando 25 perfis aleatorios;
        List<Perfil> randomList = Stream.generate(() -> new Random().nextInt(2))
                .limit(10)
                .map((randomNumber) -> {
                    Gender gender = Gender.WOMAN;
                    if (randomNumber == 1) {
                        gender = Gender.MAN;
                    }
                    count.incrementAndGet();
                    return new Perfil("Name" + count, gender);
                }).toList();

        // Imprimindo lista aleatoria
        System.out.println("_______ LISTA ALEATORIA FEITA COM STREAMS _______");
        randomList.forEach(System.out::println);

        // Usando Streams para filtrar
        System.out.println("_______ FILTRANDO A LISTA COM STREAMS _______");
        randomList.stream().filter((b) -> b.gender == Gender.WOMAN).forEach(System.out::println);
    }
}