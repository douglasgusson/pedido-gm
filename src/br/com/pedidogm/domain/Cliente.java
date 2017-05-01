
package br.com.pedidogm.domain;

/**
 *
 * @author douglas
 */
public class Cliente extends Pessoa {
    
    private String email;
    private String contato;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    @Override
    public String toString() {
        return super.toString(); 
    }
   
}
