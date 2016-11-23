
package br.com.pedidogm.domain;

import java.text.SimpleDateFormat;

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


    public static String acessoToString() {
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String data = format1.format(usuario.getUltimoAcesso());
        return data;
    }

}
