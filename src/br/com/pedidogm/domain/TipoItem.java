package br.com.pedidogm.domain;

import java.time.LocalDateTime;

/**
 *
 * @author douglas
 */
public class TipoItem {

    private Long id;
    private String descricao;
    private int referenciaCalculo;
    private LocalDateTime criacao;
    private LocalDateTime alteracao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getReferenciaCalculo() {
        return referenciaCalculo;
    }

    public void setReferenciaCalculo(int referenciaCalculo) {
        this.referenciaCalculo = referenciaCalculo;
    }

    public LocalDateTime getCriacao() {
        return criacao;
    }

    public void setCriacao(LocalDateTime criacao) {
        this.criacao = criacao;
    }

    public LocalDateTime getAlteracao() {
        return alteracao;
    }

    public void setAlteracao(LocalDateTime alteracao) {
        this.alteracao = alteracao;
    }

    @Override
    public String toString() {
        return this.getDescricao();
    }

}
