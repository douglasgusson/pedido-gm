package br.com.pedidogm.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author douglas
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

    private Long id;
    private String nome;
    private String apelido;
    private String telefone;
    private String celular;
    private String observacoes;
    private LocalDateTime criacao;
    private LocalDateTime alteracao;

    @Override
    public String toString() {
        return this.getNome();
    }

}
