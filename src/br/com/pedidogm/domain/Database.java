package br.com.pedidogm.domain;

import br.com.pedidogm.util.Seguranca;

/**
 *
 * @author douglas
 */
public class Database {

    private final String host;
    private final String porta;
    private final String nomeBanco;
    private final String usuario;
    private final String senha;

    public Database(String host, String porta, String nomeBanco, String usuario, String senha) {
        this.host = host;
        this.porta = porta;
        this.nomeBanco = nomeBanco;
        this.usuario = usuario;
        this.senha = senha;
    }
    
    public String getHost() {
        return Seguranca.encryptDecrypt(host);
    }

    public String getPorta() {
        return Seguranca.encryptDecrypt(porta);
    }

    public String getNomeBanco() {
        return Seguranca.encryptDecrypt(nomeBanco);
    }

    public String getUsuario() {
        return Seguranca.encryptDecrypt(usuario);
    }

    public String getSenha() {
        return Seguranca.encryptDecrypt(senha);
    }

    @Override
    public String toString() {
        return getHost() + ":" + getPorta() + "/" + getNomeBanco();
    }

}
