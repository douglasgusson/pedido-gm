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
    private BigDecimal comprimentoBr;
    private BigDecimal alturaBr;
    private BigDecimal larguraBr;
    private BigDecimal comprimentoLiq;
    private BigDecimal alturaLiq;
    private BigDecimal larguraLiq;
    private String acabamento;
    private BigDecimal metragem;
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

    public BigDecimal getComprimentoBr() {
        return comprimentoBr;
    }

    public void setComprimentoBr(BigDecimal comprimentoBr) {
        this.comprimentoBr = comprimentoBr;
    }

    public BigDecimal getAlturaBr() {
        return alturaBr;
    }

    public void setAlturaBr(BigDecimal alturaBr) {
        this.alturaBr = alturaBr;
    }

    public BigDecimal getLarguraBr() {
        return larguraBr;
    }

    public void setLarguraBr(BigDecimal larguraBr) {
        this.larguraBr = larguraBr;
    }

    public BigDecimal getComprimentoLiq() {
        return comprimentoLiq;
    }

    public void setComprimentoLiq(BigDecimal comprimentoLiq) {
        this.comprimentoLiq = comprimentoLiq;
    }

    public BigDecimal getAlturaLiq() {
        return alturaLiq;
    }

    public void setAlturaLiq(BigDecimal alturaLiq) {
        this.alturaLiq = alturaLiq;
    }

    public BigDecimal getLarguraLiq() {
        return larguraLiq;
    }

    public void setLarguraLiq(BigDecimal larguraLiq) {
        this.larguraLiq = larguraLiq;
    }

    public String getAcabamento() {
        return acabamento;
    }

    public void setAcabamento(String acabamento) {
        this.acabamento = acabamento;
    }

    public BigDecimal getMetragem() {
        return metragem;
    }

    public void setMetragem(BigDecimal metragem) {
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
