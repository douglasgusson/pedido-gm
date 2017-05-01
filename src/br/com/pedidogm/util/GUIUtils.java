package br.com.pedidogm.util;

import java.awt.Window;
import javax.swing.JOptionPane;

/**
 *
 * @author douglas
 */
public class GUIUtils {

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

    public static int confirmarExclusao(Object o) {
        int i = JOptionPane.showConfirmDialog(null,
                "Deseja realmente excluir este registro?\n\n"
                        + o.toString() + "\n",
                "Confirmação de exclusão",
                JOptionPane.YES_NO_OPTION);
        return i;
    }
}
