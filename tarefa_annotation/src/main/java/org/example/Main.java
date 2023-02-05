package org.example;
import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException {
        /* Pegando a classe Produto */
        Class produtoClasse = Produto.class;

        /* Pegando a propriedade */
        Field propriedade = produtoClasse.getDeclaredField("tabela");

        /* Imprimindo valor enviado na anotação */
        if (propriedade.isAnnotationPresent(Tabela.class)) {
            /* Pegando a anotação */
            Tabela tabelaAnnotation = propriedade.getAnnotation(Tabela.class);

            /* Pegando o valor */
            String value = tabelaAnnotation.value();

            System.out.println(value);
        }
    }
}