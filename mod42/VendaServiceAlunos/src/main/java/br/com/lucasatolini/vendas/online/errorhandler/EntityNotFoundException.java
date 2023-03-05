package br.com.lucasatolini.vendas.online.errorhandler;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EntityNotFoundException extends RuntimeException {
    private String message;
    private String error;

    public EntityNotFoundException(String key, String value) {
        this.message = "Error fetching entity.";
        this.error = "Not found entity with " + key + " equal " + value;
    }
}
