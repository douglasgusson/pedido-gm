package br.com.pedidogm.util;

import java.awt.Window;
import javax.swing.JOptionPane;

/**
 *
 * @author douglas
 */
public class GUIUtils {

    private static final String NAME_SYSTEM = "PedidoGM";
    private static final String VERSION = "v0.8.4";

    public static String getVersion() {
        return VERSION;
    }

    public static String getNameSystem() {
        return NAME_SYSTEM;
    }

    public static String getNameMoreVersion() {
        return NAME_SYSTEM + " " + VERSION;
    }

    public static void confirmarSaida(Window w) {
        int i = JOptionPane.showConfirmDialog(null,
                "Deseja realmente sair deste sistema?\n",
                "Confirmação de saída",
                JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.NO_OPTION) {
            w.repaint();
        } else {
            System.exit(0);
        }
    }
}
