package br.com.pedidogm.domain;

import java.time.LocalDateTime;

/**
 *
 * @author douglas
 */
public class Material {

    private Long id;
    private String nome;
    private LocalDateTime criacao;
    private LocalDateTime alteracao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        return new StringBuilder(getId().toString())
                .append(" - ").append(getNome()).toString();
    }

}
