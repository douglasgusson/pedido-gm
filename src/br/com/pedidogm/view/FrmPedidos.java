package br.com.pedidogm.view;

import br.com.pedidogm.dao.DAOFactory;
import br.com.pedidogm.dao.model.PedidoDAO;
import br.com.pedidogm.dao.model.RelatorioDAO;
import br.com.pedidogm.domain.Pedido;
import br.com.pedidogm.util.Sessao;
import br.com.pedidogm.table.cellrenderer.PedidoCellRenderer;
import br.com.pedidogm.table.model.PedidoTableModel;
import br.com.pedidogm.util.GUIUtils;
import java.awt.Cursor;
import java.awt.Window;
import java.util.List;
import javax.swing.JOptionPane;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Douglas Gusson
 */
public class FrmPedidos extends javax.swing.JDialog {

    private static FrmPedidos INSTANCIA;
    private int limitPedidos = 5;

    public static FrmPedidos getInstancia() {
        return INSTANCIA;
    }

    public FrmPedidos(Window parent) {
        super(parent, DEFAULT_MODALITY_TYPE);
        initComponents();
        atualizarTabela();
        INSTANCIA = this;
    }

    public void atualizarTabela() {

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        limitPedidos = (int) spNumPedidos.getValue();

        PedidoDAO pdao = DAOFactory.getDefaultDAOFactory().getPedidoDAO();
        List<Pedido> pedidos = pdao.listar(limitPedidos);

        this.tbPedidos.setModel(new PedidoTableModel(pedidos));
        this.tbPedidos.setDefaultRenderer(Object.class, new PedidoCellRenderer());

        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

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
        btAtualizar = new javax.swing.JButton();
        btSair = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPedidos = new javax.swing.JTable();
        spNumPedidos = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pedidos");

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

        btAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/atualizar_16x16.png"))); // NOI18N
        btAtualizar.setMnemonic('t');
        btAtualizar.setText("Atualizar");
        btAtualizar.setToolTipText("");
        btAtualizar.setFocusable(false);
        btAtualizar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btAtualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizarActionPerformed(evt);
            }
        });
        jToolBar1.add(btAtualizar);

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

        spNumPedidos.setModel(new javax.swing.SpinnerNumberModel(5, 1, null, 1));
        spNumPedidos.setToolTipText("Número de pedidos a serem exibidos");
        spNumPedidos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spNumPedidosStateChanged(evt);
            }
        });

        jLabel1.setText("Número de pedidos a serem exibidos:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spNumPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spNumPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        FrmRegistroPedido registroPedido = new FrmRegistroPedido(this);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        registroPedido.setVisible(true);
    }//GEN-LAST:event_btNovoActionPerformed

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        int indice = tbPedidos.getSelectedRow();
        if (indice == -1) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            JOptionPane.showMessageDialog(null, "Nenhum registro selecionado");
        } else {

            Pedido p = ((PedidoTableModel) tbPedidos.getModel()).getColecao().get(indice);

            if (Sessao.getUsuario().getNomeUsuario().equals(p.getUsuario().getNomeUsuario())) {
                FrmRegistroPedido registroPedido
                        = new FrmRegistroPedido(this,
                                ((PedidoTableModel) tbPedidos.getModel()).getColecao(), indice);
                this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                registroPedido.setVisible(true);
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                JOptionPane.showMessageDialog(null, "Desculpe. Você pode apenas alterar pedidos emitidos por você mesmo.");
            }
        }

    }//GEN-LAST:event_btAlterarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        int indice = tbPedidos.getSelectedRow();
        if (indice == -1) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            JOptionPane.showMessageDialog(null, "Nenhum registro selecionado");
        } else {
            Pedido p = ((PedidoTableModel) tbPedidos.getModel()).getColecao().get(indice);

            if (Sessao.getUsuario().getNomeUsuario().equals(p.getUsuario().getNomeUsuario())) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                if (GUIUtils.confirmarExclusao(p) == JOptionPane.YES_OPTION) {
                    PedidoDAO pedidoDAO = DAOFactory.getDefaultDAOFactory().getPedidoDAO();
                    pedidoDAO.excluir(p);
                    atualizarTabela();
                }
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                JOptionPane.showMessageDialog(null, "Desculpe. Você pode apenas excluir pedidos emitidos por você mesmo.");
            }

        }
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btSairActionPerformed

    private void btImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImprimirActionPerformed

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        int indice = tbPedidos.getSelectedRow();

        if (indice == -1) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            JOptionPane.showMessageDialog(null, "Nenhum registro selecionado");
        } else {

            RelatorioDAO relatorioDAO = DAOFactory.getDefaultDAOFactory().getRelatorioDAO();
            Pedido pedido = ((PedidoTableModel) tbPedidos.getModel()).getColecao().get(indice);

            JasperViewer jasperViewer = relatorioDAO.impressaoPedido(pedido);

            FrmReport frmReport = new FrmReport(this, pedido.toString(), jasperViewer);
            this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            frmReport.setVisible(true);
        }

    }//GEN-LAST:event_btImprimirActionPerformed

    private void btAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizarActionPerformed
        atualizarTabela();
    }//GEN-LAST:event_btAtualizarActionPerformed

    private void spNumPedidosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spNumPedidosStateChanged
        atualizarTabela();
    }//GEN-LAST:event_spNumPedidosStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btAtualizar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btImprimir;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JSpinner spNumPedidos;
    private javax.swing.JTable tbPedidos;
    // End of variables declaration//GEN-END:variables
}
