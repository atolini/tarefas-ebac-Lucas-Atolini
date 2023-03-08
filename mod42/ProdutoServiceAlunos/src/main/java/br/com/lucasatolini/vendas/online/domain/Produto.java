package br.com.lucasatolini.vendas.online.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produto {
    public enum Status { ATIVO, INATIVO }

    @Id
    private String id;

    @NotNull
    @Size(min = 2, max = 10)
    @Indexed(unique = true, background = true)
    private String codigo;

    @NotNull
    @Size(min = 1, max = 50)
    private String nome;

    @NotNull
    @Size(min = 1, max = 50)
    private String descricao;

    private BigDecimal valor;

    private Status status;
}
