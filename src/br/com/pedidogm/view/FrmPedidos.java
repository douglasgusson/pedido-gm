package br.com.pedidogm.view;

import java.awt.Window;

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
        initialize();
    }

    private void initialize() {
        //atualizarTabela();
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
        btSair = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        btNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/novo_16x16.png"))); // NOI18N
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

        btSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/sair_16x16.png"))); // NOI18N
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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    }//GEN-LAST:event_btAlterarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btSairActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btSair;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}