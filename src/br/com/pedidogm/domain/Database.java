package br.com.pedidogm.domain;

import br.com.pedidogm.view.FrmConfiguraBanco;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import javax.swing.JOptionPane;

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

    /**
     * @return O objeto Database.
     */
    public static Database getDatabase() {
        try {
            StringBuilder xml = new StringBuilder();
            Scanner scanner = new Scanner(new FileReader(".db_conf_pedidogm"));

            while (scanner.hasNext()) {
                xml.append(scanner.next());
            }

            XStream xStream = new XStream(new DomDriver());
            Database banco = (Database) xStream.fromXML(xml.toString());

            return banco;

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,
                    "Não foi possível estabelecer conexão com o banco.\n\n"
                    + "ERRO: Arquivo \".db_conf_pedidogm\" não encontrado.",
                    "Erro de conexão",
                    JOptionPane.ERROR_MESSAGE);
            FrmConfiguraBanco configuraBanco = new FrmConfiguraBanco(null);
            configuraBanco.setVisible(true);
        }
        return null;
    }

    /**
     * @param pathArq
     * @return O objeto Database.
     */
    public static Database getDatabase(String pathArq) {
        try {
            StringBuilder xml = new StringBuilder();
            Scanner scanner = new Scanner(new FileReader(pathArq));
            while (scanner.hasNext()) {
                xml.append(scanner.next());
            }
            XStream xStream = new XStream(new DomDriver());
            Database database = (Database) xStream.fromXML(xml.toString());

            return database;
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (ClassCastException ex) {
            JOptionPane.showMessageDialog(null, "O arquivo não pôde ser importado. \nERRO: " + ex);
        }
        return null;
    }

    @Override
    public String toString() {
        return new StringBuilder(getHost())
                .append(":").append(getPorta()).append("/")
                .append(getNomeBanco()).toString();
    }

}
