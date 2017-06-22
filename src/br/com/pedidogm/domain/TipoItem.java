package br.com.pedidogm.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author douglas
 */
@Data @AllArgsConstructor @NoArgsConstructor
public class TipoItem {

    private Long id;
    private String descricao;
    private int referenciaCalculo;
    private LocalDateTime criacao;
    private LocalDateTime alteracao;

    @Override
    public String toString() {
        return this.getDescricao();
    }

}
