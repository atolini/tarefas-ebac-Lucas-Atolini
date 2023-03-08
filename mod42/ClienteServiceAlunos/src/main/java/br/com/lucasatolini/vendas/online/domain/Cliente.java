package br.com.lucasatolini.vendas.online.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

import io.swagger.v3.oas.annotations.media.Schema;

@Document(collection = "cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Cliente", description = "Cliente")
public class Cliente {

    @Id
    @Schema(description = "Identificador único")
    private String id; 

    @NotNull
    @Size(min = 1, max = 50)
    @Indexed(unique = true, background = true)
    private String nome; 

    @NotNull
	@Indexed(unique = true, background = true)
	@Schema(description= "CPF", nullable = false) 
    private Long cpf; 
    
    @NotNull
	@Schema(description= "Telefone", nullable = false) 
    private Long tel; 

    @NotNull
	@Size(min = 1, max = 50)
	@Indexed(unique = true, background = true)
	@Schema(description="Email", minLength = 1, maxLength=50, nullable = false)
	@Pattern(regexp = ".+@.+\\..+", message = "Email inválido")
    private String email; 

	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description= "Endereço", minLength = 1, maxLength=50, nullable = false)
    private String end; 

    @NotNull
	@Schema(description="Número residencial", nullable = false) 
    private Integer numero; 

    @NotNull
	@Size(min = 1, max = 50)
	@Schema(description= "Cidade", minLength = 1, maxLength=50, nullable = false)
    private String cidade; 

	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description= "Estado", minLength = 1, maxLength=50, nullable = false)
    private String estado; 
}
