
package br.com.pedidogm.domain;

import java.time.format.DateTimeFormatter;

/**
 *
 * @author douglas
 */
public class Sessao {

    private static Usuario usuario;

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario aUsuario) {
        usuario = aUsuario;
    }


    public static String sessaoToString() {
        String data = usuario.getUltimoAcesso().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        String str = 
                "Acesso: " + data + "\n"
                + "Usuário: " + usuario.getNomeUsuario()+ "\n"
                + "Nome: " + usuario.getNomeCompleto() + "\n"
                + "Admin: " + usuario.isAdmin();
        System.out.println(str);
        return str;
    }
    
}
