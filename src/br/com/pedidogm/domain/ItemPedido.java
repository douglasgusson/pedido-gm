package br.com.pedidogm.domain;

import java.math.BigDecimal;
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
public class ItemPedido {

    private Long id;
    private Material material;
    private Pedido pedido;
    private TipoItem tipoItem;
    private Long quantidade;
    private BigDecimal comprimentoBr;
    private BigDecimal alturaBr;
    private BigDecimal larguraBr;
    private BigDecimal comprimentoLiq;
    private BigDecimal alturaLiq;
    private BigDecimal larguraLiq;
    private Acabamento acabamento;
    private BigDecimal metragem;
    private BigDecimal valorUnitario;
    private BigDecimal desconto;
    private BigDecimal valorTotal;

    @Override
    public String toString() {
        return this.getId() + " - " + this.getMaterial().getNome();
    }

}
