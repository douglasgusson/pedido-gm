package br.com.pedidogm.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Douglas Gusson
 */
public class FrmReport extends JDialog {

    private final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private final Dimension screenSize = toolkit.getScreenSize();
    private JPanel jContentPane;
    private final JasperViewer jasperViewer;
    private final String title;

    public FrmReport(Window parent, String title, JasperViewer jasperViewer) {
        super(parent, title, DEFAULT_MODALITY_TYPE);
        this.jasperViewer = jasperViewer;
        this.title = title;
        initialize();
    }


    /**
     * This method initializes this
     *
     * @return void
     */
    private void initialize() {
        this.setBounds(0, 0, screenSize.width, screenSize.height - 40);
        this.setResizable(false);
        this.setContentPane(getJContentPane());

        // Se teclarmos ESC nesta janela, ela irá se fechar:  
        this.getRootPane().registerKeyboardAction((ActionEvent e) -> {
            FrmReport.this.dispose();
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
        this.setTitle(title);
    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {

        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(new GridLayout());
            jContentPane.add(jasperViewer.getContentPane());
        }
        return jContentPane;

    }

}
