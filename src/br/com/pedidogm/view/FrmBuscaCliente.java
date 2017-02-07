package br.com.pedidogm.view;

import br.com.pedidogm.domain.Cliente;
import br.com.pedidogm.table.model.ClienteTableModel;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author douglas
 */
public class FrmBuscaCliente extends javax.swing.JDialog {

    private final List<Cliente> listaClientes;

    
    public FrmBuscaCliente(Window parent, List<Cliente> clientes) {
        super(parent, DEFAULT_MODALITY_TYPE);
        initComponents();
        this.listaClientes = clientes;
        initialize();
    }

    public void atualizarTabela() {
        tbClientes.setModel(new ClienteTableModel(this.listaClientes));
//        tbClientes.setDefaultRenderer(Object.class, new ClienteCellRenderer());
    }

    private void initialize() {

        atualizarTabela();
        eventoFechar();

        // Se teclarmos ESC nesta janela, ela irá se fechar:  
        this.getRootPane().registerKeyboardAction((ActionEvent e) -> {
            FrmBuscaCliente.this.retornarCliente();
            FrmBuscaCliente.this.dispose();
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);

    }

    private void eventoFechar() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                retornarCliente();
            }
        });
    }

    private Boolean retornarCliente() {

        int row = this.tbClientes.getSelectedRow();

        if (row != (-1)) {

            ClienteTableModel clienteTableModel = new ClienteTableModel(this.listaClientes);        
            Cliente c = clienteTableModel.get(row);

            if (this.getParent() instanceof FrmRegistroPedido) {
                FrmRegistroPedido frmRegistroPedido = FrmRegistroPedido.getInstancia();
                frmRegistroPedido.setCliente(c);
                this.dispose();
            }

            return true;
        }

        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btOk = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbClientes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Busca de Clientes");

        btOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/entrar_16x16.png"))); // NOI18N
        btOk.setMnemonic('O');
        btOk.setText("OK");
        btOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOkActionPerformed(evt);
            }
        });

        tbClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbClientes.setToolTipText("");
        tbClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbClientes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btOk)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btOk)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOkActionPerformed
        if (!retornarCliente()) {
            this.dispose();
        }
    }//GEN-LAST:event_btOkActionPerformed

    private void tbClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbClientesMouseClicked
        if (evt.getClickCount() == 2) {
            retornarCliente();
        }
    }//GEN-LAST:event_tbClientesMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btOk;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbClientes;
    // End of variables declaration//GEN-END:variables
}
