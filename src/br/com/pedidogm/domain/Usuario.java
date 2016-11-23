package br.com.pedidogm.domain;

import java.time.LocalDateTime;

/**
 *
 * @author douglas
 */
public class Usuario {

    private Long id;
    private String nomeUsuario;
    private String senha;
    private String nomeCompleto;
    private String email;
    private LocalDateTime ultimoAcesso;
    private Boolean novaSenha;
    private Boolean ativo;
    private Boolean admin;

    public Usuario(String nomeUsuario, String senha, String nomeCompleto,
            String email, LocalDateTime ultimoAcesso, Boolean novaSenha, Boolean ativo, Boolean admin) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.ultimoAcesso = ultimoAcesso;
        this.novaSenha = novaSenha;
        this.ativo = ativo;
        this.admin = admin;
    }

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(LocalDateTime ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    public Boolean isNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(Boolean novaSenha) {
        this.novaSenha = novaSenha;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
     public Boolean isAdmin() {
        return ativo;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

}
