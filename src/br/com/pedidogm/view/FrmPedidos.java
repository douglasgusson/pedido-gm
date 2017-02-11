package br.com.pedidogm.view;

import br.com.pedidogm.dao.DAOFactory;
import br.com.pedidogm.dao.model.PedidoDAO;
import br.com.pedidogm.dao.model.RelatorioDAO;
import br.com.pedidogm.domain.Pedido;
import br.com.pedidogm.domain.Sessao;
import br.com.pedidogm.table.cellrenderer.PedidoCellRenderer;
import br.com.pedidogm.table.model.PedidoTableModel;
import java.awt.Window;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Douglas Gusson
 */
public class FrmPedidos extends javax.swing.JDialog {

    private static FrmPedidos INSTANCIA;

    public static FrmPedidos getInstancia() {
        return INSTANCIA;
    }

    public FrmPedidos(Window parent) {
        super(parent, DEFAULT_MODALITY_TYPE);
        initComponents();
        INSTANCIA = this;
    }

    public void atualizarTabela() {
        tbPedidos.setModel(new PedidoTableModel());
        tbPedidos.setDefaultRenderer(Object.class, new PedidoCellRenderer());
        ((PedidoTableModel) tbPedidos.getModel()).atualizarDoBD();
        ((AbstractTableModel) tbPedidos.getModel()).fireTableDataChanged();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        btNovo = new javax.swing.JButton();
        btAlterar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btImprimir = new javax.swing.JButton();
        btSair = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPedidos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pedidos");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        btNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/novo_16x16.png"))); // NOI18N
        btNovo.setMnemonic('N');
        btNovo.setText("Novo");
        btNovo.setFocusable(false);
        btNovo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btNovo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
            }
        });
        jToolBar1.add(btNovo);

        btAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/alterar_16x16.png"))); // NOI18N
        btAlterar.setMnemonic('A');
        btAlterar.setText("Alterar");
        btAlterar.setFocusable(false);
        btAlterar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btAlterar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarActionPerformed(evt);
            }
        });
        jToolBar1.add(btAlterar);

        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/excluir_16x16.png"))); // NOI18N
        btExcluir.setMnemonic('E');
        btExcluir.setText("Excluir");
        btExcluir.setFocusable(false);
        btExcluir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btExcluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });
        jToolBar1.add(btExcluir);

        btImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/impressao_16x16.png"))); // NOI18N
        btImprimir.setMnemonic('I');
        btImprimir.setText("Imprimir");
        btImprimir.setFocusable(false);
        btImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImprimirActionPerformed(evt);
            }
        });
        jToolBar1.add(btImprimir);

        btSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/sair_16x16.png"))); // NOI18N
        btSair.setMnemonic('S');
        btSair.setText("Sair");
        btSair.setFocusable(false);
        btSair.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btSair.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });
        jToolBar1.add(btSair);

        tbPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbPedidos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
        FrmRegistroPedido registroPedido = new FrmRegistroPedido(this);
        registroPedido.setVisible(true);
    }//GEN-LAST:event_btNovoActionPerformed

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed
        int indice = tbPedidos.getSelectedRow();
        if (indice == -1) {
            JOptionPane.showMessageDialog(null, "Nenhum registro selecionado");
        } else {

            Pedido p = ((PedidoTableModel) tbPedidos.getModel()).getColecao().get(indice);

            if (Sessao.getUsuario().getNomeUsuario().equals(p.getUsuario().getNomeUsuario())) {
                FrmRegistroPedido registroPedido
                        = new FrmRegistroPedido(this,
                                ((PedidoTableModel) tbPedidos.getModel()).getColecao(), indice);
                registroPedido.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Desculpe. Você pode apenas alterar pedidos emitidos por você mesmo.");
            }
        }
    }//GEN-LAST:event_btAlterarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        int indice = tbPedidos.getSelectedRow();
        if (indice == -1) {
            JOptionPane.showMessageDialog(null, "Nenhum registro selecionado");
        } else {
            Pedido p = ((PedidoTableModel) tbPedidos.getModel()).getColecao().get(indice);

            if (Sessao.getUsuario().getNomeUsuario().equals(p.getUsuario().getNomeUsuario())) {
                int i = JOptionPane.showConfirmDialog(null,
                        "Deseja realmente excluir este pedido?\n"
                        + p.getId() + " - " + p.getCliente().getNome(),
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION);
                if (i == JOptionPane.YES_OPTION) {
                    PedidoDAO pedidoDAO = DAOFactory.getDefaultDAOFactory().getPedidoDAO();
                    pedidoDAO.excluir(p);
                    atualizarTabela();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Desculpe. Você pode apenas excluir pedidos emitidos por você mesmo.");
            }

        }
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btSairActionPerformed

    private void btImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImprimirActionPerformed

        int indice = tbPedidos.getSelectedRow();

        if (indice == -1) {
            JOptionPane.showMessageDialog(null, "Nenhum registro selecionado");
        } else {

            RelatorioDAO relatorioDAO = DAOFactory.getDefaultDAOFactory().getRelatorioDAO();
            Pedido pedido = ((PedidoTableModel) tbPedidos.getModel()).getColecao().get(indice);

            JasperViewer jasperViewer = relatorioDAO.impressaoPedido(pedido);

            FrmReport frmReport = new FrmReport(this, pedido.toString(), jasperViewer);
            frmReport.setVisible(true);
        }

    }//GEN-LAST:event_btImprimirActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        atualizarTabela();
    }//GEN-LAST:event_formWindowGainedFocus

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btImprimir;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btSair;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tbPedidos;
    // End of variables declaration//GEN-END:variables
}
