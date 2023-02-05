package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        /*
         * A lista a seguir pode receber qualquer subclasse de Carro;
         */

        List<Carro> listaDeCarros = new ArrayList<>();

        FiatPalio palio = new FiatPalio("ABC1010", "Branco", "Fiat");
        Carro civid = new HondaCivic("ABC1010", "Branco", "Honda");
        Carro corolla = new ToyotaCorolla("ABC1010", "Branco", "Toyota");

        listaDeCarros.add(palio);
        listaDeCarros.add(civid);
        listaDeCarros.add(corolla);

        /*
         * Chamando a função que aceita um generics;
         */

        print(listaDeCarros);
    }

    public static void print(List<? extends Carro> lista) {
        for (Object o : lista) {
            System.out.println(o);
        }
    }
}