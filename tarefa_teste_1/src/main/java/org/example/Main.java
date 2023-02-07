package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {}

    public static List<Perfil> generateList() {
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

        return randomList.stream().filter((b) -> b.gender == Gender.WOMAN).toList();
    }

    /*
     * Teste solicitado na tarefa!
     */
    @Test
    public void test() {
        List<Perfil> randomList = generateList();
        randomList.forEach((a) -> Assertions.assertEquals(Gender.WOMAN, a.getGender()));
    }
}