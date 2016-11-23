
package br.com.pedidogm.domain;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import java.text.SimpleDateFormat;
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
                + "";
        System.out.println(str);
        return str;
    }
    
}
