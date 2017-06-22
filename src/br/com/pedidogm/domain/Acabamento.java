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
public class Acabamento {

    private Long id;
    private String descricao;
    private LocalDateTime criacao;
    private LocalDateTime alteracao;

    @Override
    public String toString() {
        return this.getDescricao();
    }

}
