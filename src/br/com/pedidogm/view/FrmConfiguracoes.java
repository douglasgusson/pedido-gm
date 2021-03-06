package br.com.pedidogm.view;

import java.awt.Window;

/**
 *
 * @author Usuario
 */
public class FrmConfiguracoes extends javax.swing.JDialog {

    public FrmConfiguracoes(Window parent) {
        super(parent, DEFAULT_MODALITY_TYPE);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btUsuarios = new javax.swing.JButton();
        btBancoDados = new javax.swing.JButton();
        btSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configurações");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/users_80x80.png"))); // NOI18N
        btUsuarios.setMnemonic('U');
        btUsuarios.setText("Usuários");
        btUsuarios.setContentAreaFilled(false);
        btUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btUsuarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btUsuarios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUsuariosActionPerformed(evt);
            }
        });

        btBancoDados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/banco_de_dados_80x80.png"))); // NOI18N
        btBancoDados.setMnemonic('B');
        btBancoDados.setText("Banco de Dados");
        btBancoDados.setContentAreaFilled(false);
        btBancoDados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btBancoDados.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btBancoDados.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btBancoDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBancoDadosActionPerformed(evt);
            }
        });

        btSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/sair_16x16.png"))); // NOI18N
        btSair.setMnemonic('S');
        btSair.setText("Sair");
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btSair)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btBancoDados)))
                .addGap(50, 50, 50))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btBancoDados)
                    .addComponent(btUsuarios))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(btSair)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUsuariosActionPerformed
        FrmUsuarios usuarios = new FrmUsuarios(this);
        usuarios.setVisible(true);
    }//GEN-LAST:event_btUsuariosActionPerformed

    private void btBancoDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBancoDadosActionPerformed
        FrmConfiguraBanco configuraBanco = new FrmConfiguraBanco(this);
        configuraBanco.setVisible(true);
    }//GEN-LAST:event_btBancoDadosActionPerformed

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btSairActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBancoDados;
    private javax.swing.JButton btSair;
    private javax.swing.JButton btUsuarios;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
