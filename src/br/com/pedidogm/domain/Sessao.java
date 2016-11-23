
package br.com.pedidogm.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author douglas
 */
public class Sessao {

    private static Usuario usuario;
    private static Calendar acesso;

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario aUsuario) {
        usuario = aUsuario;
    }

    public static Calendar getAcesso() {
        return acesso;
    }

    public static void setAcesso(Calendar aAcesso) {
        acesso = aAcesso;
    }

    public static String acessoToString() {
        Date date = getAcesso().getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String data = format1.format(date);
        return data;
    }

}
