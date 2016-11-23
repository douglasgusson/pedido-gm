package br.com.pedidogm.domain;

/**
 *
 * @author douglas
 */
public class Database {

    private String host;
    private String porta;
    private String nomeBanco;
    private String usuario;
    private String senha;

    public Database(String host, String porta, String nomeBanco, String usuario, String senha) {
        this.host = host;
        this.porta = porta;
        this.nomeBanco = nomeBanco;
        this.usuario = usuario;
        this.senha = senha;
    }
    
    public String getHost() {
        return host;
    }

    public String getPorta() {
        return porta;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return getHost() + ":" + getPorta() + "/" + getNomeBanco();
    }

}
