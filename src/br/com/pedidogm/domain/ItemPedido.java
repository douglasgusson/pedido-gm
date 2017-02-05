
package br.com.pedidogm.domain;

import java.math.BigDecimal;

/**
 *
 * @author douglas
 */
public class ItemPedido {
    
    private Long id;
    private Material material;
    private Pedido pedido;
    private Long quantidade;
    private String tipo;
    private Float comprimentoBr;
    private Float alturaBr;
    private Float larguraBr;
    private Float comprimentoLiq;
    private Float alturaLiq;
    private Float larguraLiq;
    private String acabamento;
    private Float metragem;
    private BigDecimal valorUnitario;
    private BigDecimal desconto;
    private BigDecimal valorTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Float getComprimentoBr() {
        return comprimentoBr;
    }

    public void setComprimentoBr(Float comprimentoBr) {
        this.comprimentoBr = comprimentoBr;
    }

    public Float getAlturaBr() {
        return alturaBr;
    }

    public void setAlturaBr(Float alturaBr) {
        this.alturaBr = alturaBr;
    }

    public Float getLarguraBr() {
        return larguraBr;
    }

    public void setLarguraBr(Float larguraBr) {
        this.larguraBr = larguraBr;
    }

    public Float getComprimentoLiq() {
        return comprimentoLiq;
    }

    public void setComprimentoLiq(Float comprimentoLiq) {
        this.comprimentoLiq = comprimentoLiq;
    }

    public Float getAlturaLiq() {
        return alturaLiq;
    }

    public void setAlturaLiq(Float alturaLiq) {
        this.alturaLiq = alturaLiq;
    }

    public Float getLarguraLiq() {
        return larguraLiq;
    }

    public void setLarguraLiq(Float larguraLiq) {
        this.larguraLiq = larguraLiq;
    }

    public String getAcabamento() {
        return acabamento;
    }

    public void setAcabamento(String acabamento) {
        this.acabamento = acabamento;
    }

    public Float getMetragem() {
        return metragem;
    }

    public void setMetragem(Float metragem) {
        this.metragem = metragem;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }


    
}
