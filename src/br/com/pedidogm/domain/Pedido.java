package br.com.pedidogm.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author douglas
 */
@Data @AllArgsConstructor @NoArgsConstructor
public class Pedido {

    private Long id;
    private Cliente cliente;
    private BigDecimal desconto;
    private BigDecimal ipi;
    private BigDecimal seguro;
    private BigDecimal frete;
    private BigDecimal outros;
    private BigDecimal valor;
    private String placa;
    private String motorista;
    private String observacoes;
    private LocalDate dataCarregamento;
    private LocalDateTime criacao;
    private LocalDateTime alteracao;
    private Usuario usuario;

    private List<ItemPedido> itensPedido;

    @Override
    public String toString() {
        return new StringBuilder(getId().toString()).append(" - ")
                .append(getCliente().getNome()).append(" (R$")
                .append(getValor()).append(")").toString();
    }

}
