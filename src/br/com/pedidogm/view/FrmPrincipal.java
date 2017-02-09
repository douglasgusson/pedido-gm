package br.com.pedidogm.view;

import br.com.pedidogm.domain.Sessao;
import br.com.pedidogm.util.GUIUtils;
import java.awt.Window;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Douglas Gusson
 */
public class FrmPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal() {
        initComponents();
        initialize();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lbUsuario = new javax.swing.JLabel();
        lbAcesso = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbNome = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btLogout = new javax.swing.JButton();
        btSair = new javax.swing.JButton();
        barraMenu = new javax.swing.JMenuBar();
        menuCadastros = new javax.swing.JMenu();
        itemPedidos = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        itemMateriais = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        itemTipos = new javax.swing.JMenuItem();
        itemAcabamentos = new javax.swing.JMenuItem();
        menuUtiltarios = new javax.swing.JMenu();
        itemMudarSenha = new javax.swing.JMenuItem();
        itemConfiguracoes = new javax.swing.JMenuItem();
        menuSistema = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        menuAjuda = new javax.swing.JMenu();
        itemSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela Principal");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBackground(java.awt.Color.white);
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Sessão"));

        lbUsuario.setBackground(new java.awt.Color(204, 204, 204));
        lbUsuario.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lbUsuario.setForeground(java.awt.Color.darkGray);
        lbUsuario.setText("usuario autenticado...");

        lbAcesso.setBackground(new java.awt.Color(250, 250, 250));
        lbAcesso.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lbAcesso.setForeground(java.awt.Color.darkGray);
        lbAcesso.setText("inicio da sessão...");

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel1.setText("Usuário:");

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel2.setText("Início da sessão:");

        lbNome.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lbNome.setForeground(java.awt.Color.darkGray);
        lbNome.setText("nome...");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel3.setText("Nome:");

        btLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/logout_16x16.png"))); // NOI18N
        btLogout.setText("Logout");
        btLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogoutActionPerformed(evt);
            }
        });

        btSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/sair_16x16.png"))); // NOI18N
        btSair.setText("Sair");
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(lbAcesso, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lbNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btLogout)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btSair)
                                .addContainerGap())))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbUsuario)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNome)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbAcesso)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btLogout)
                    .addComponent(btSair))
                .addContainerGap())
        );

        menuCadastros.setMnemonic('C');
        menuCadastros.setText("Cadastros");

        itemPedidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/pedido_16x16.png"))); // NOI18N
        itemPedidos.setMnemonic('P');
        itemPedidos.setText("Pedidos");
        itemPedidos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        itemPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPedidosActionPerformed(evt);
            }
        });
        menuCadastros.add(itemPedidos);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/cliente_16x16.png"))); // NOI18N
        jMenuItem2.setMnemonic('C');
        jMenuItem2.setText("Clientes");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuCadastros.add(jMenuItem2);

        itemMateriais.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/produto_16x16.png"))); // NOI18N
        itemMateriais.setMnemonic('M');
        itemMateriais.setText("Materiais");
        itemMateriais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMateriaisActionPerformed(evt);
            }
        });
        menuCadastros.add(itemMateriais);
        menuCadastros.add(jSeparator1);

        itemTipos.setText("Tipos de Item");
        itemTipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemTiposActionPerformed(evt);
            }
        });
        menuCadastros.add(itemTipos);

        itemAcabamentos.setText("Acabamentos");
        itemAcabamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAcabamentosActionPerformed(evt);
            }
        });
        menuCadastros.add(itemAcabamentos);

        barraMenu.add(menuCadastros);

        menuUtiltarios.setMnemonic('U');
        menuUtiltarios.setText("Utilitários");

        itemMudarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/usuario_16x16.png"))); // NOI18N
        itemMudarSenha.setMnemonic('M');
        itemMudarSenha.setText("Mudar minha senha");
        itemMudarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMudarSenhaActionPerformed(evt);
            }
        });
        menuUtiltarios.add(itemMudarSenha);

        itemConfiguracoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/engrenagem_16x16.png"))); // NOI18N
        itemConfiguracoes.setMnemonic('C');
        itemConfiguracoes.setText("Configurações");
        itemConfiguracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConfiguracoesActionPerformed(evt);
            }
        });
        menuUtiltarios.add(itemConfiguracoes);

        barraMenu.add(menuUtiltarios);

        menuSistema.setMnemonic('S');
        menuSistema.setText("Sistema");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/logout_16x16.png"))); // NOI18N
        jMenuItem1.setMnemonic('L');
        jMenuItem1.setText("Logout");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuSistema.add(jMenuItem1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/sair_16x16.png"))); // NOI18N
        jMenuItem3.setMnemonic('S');
        jMenuItem3.setText("Sair");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menuSistema.add(jMenuItem3);

        barraMenu.add(menuSistema);

        menuAjuda.setMnemonic('A');
        menuAjuda.setText("Ajuda");

        itemSobre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/info_16x16.png"))); // NOI18N
        itemSobre.setMnemonic('S');
        itemSobre.setText("Sobre");
        itemSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSobreActionPerformed(evt);
            }
        });
        menuAjuda.add(itemSobre);

        barraMenu.add(menuAjuda);

        setJMenuBar(barraMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(873, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(439, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void itemConfiguracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConfiguracoesActionPerformed
        FrmConfiguracoes configuracoes = new FrmConfiguracoes(this);
        configuracoes.setVisible(true);
    }//GEN-LAST:event_itemConfiguracoesActionPerformed

    private void btLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogoutActionPerformed
        FrmLogin login = new FrmLogin(this);
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btLogoutActionPerformed

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        GUIUtils.confirmarSaida(this);
    }//GEN-LAST:event_btSairActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        GUIUtils.confirmarSaida(this);
    }//GEN-LAST:event_formWindowClosing

    private void itemMudarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMudarSenhaActionPerformed
        FrmMudaSenha mudaSenha = new FrmMudaSenha(this);
        mudaSenha.setVisible(true);
    }//GEN-LAST:event_itemMudarSenhaActionPerformed

    private void itemMateriaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMateriaisActionPerformed
        FrmMateriais materiais = new FrmMateriais(this);
        materiais.setVisible(true);
    }//GEN-LAST:event_itemMateriaisActionPerformed

    private void itemPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPedidosActionPerformed
        FrmPedidos pedidos = new FrmPedidos(this);
        pedidos.setVisible(true);
    }//GEN-LAST:event_itemPedidosActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        FrmClientes clientes = new FrmClientes(this);
        clientes.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void itemSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSobreActionPerformed
        FrmSobre sobre = new FrmSobre(this);
        sobre.setVisible(true);
    }//GEN-LAST:event_itemSobreActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        btSairActionPerformed(evt);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        btLogoutActionPerformed(evt);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void itemAcabamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAcabamentosActionPerformed
        FrmAcabamentos acabamentos = new FrmAcabamentos(this);
        acabamentos.setVisible(true);
    }//GEN-LAST:event_itemAcabamentosActionPerformed

    private void itemTiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemTiposActionPerformed
        FrmTiposItem tiposItem = new FrmTiposItem(this);
        tiposItem.setVisible(true);
    }//GEN-LAST:event_itemTiposActionPerformed

    private void initialize() {
        setPermissoes();
        lbUsuario.setText(Sessao.getUsuario().getNomeUsuario());
        lbAcesso.setText(Sessao.acessoToString());
        lbNome.setText(Sessao.getUsuario().getNomeCompleto());
        setDefaultCloseOperation(FrmPrincipal.DO_NOTHING_ON_CLOSE);
        setIcon(this);

        if (Sessao.getUsuario().isNovaSenha()) {
            JOptionPane.showMessageDialog(null, "Por motivos de segurança, será necessário mudar sua senha.");
            FrmMudaSenha mudaSenha = new FrmMudaSenha(this);
            mudaSenha.setVisible(true);
        }

    }

    private void setIcon(Window w) {
        w.setIconImage(new ImageIcon(
                getClass().getResource("/br/com/pedidogm/img/icon.png")).getImage());
    }

    private void setPermissoes() {
        if (!Sessao.getUsuario().isAdmin()) {
            itemConfiguracoes.setEnabled(false);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JButton btLogout;
    private javax.swing.JButton btSair;
    private javax.swing.JMenuItem itemAcabamentos;
    private javax.swing.JMenuItem itemConfiguracoes;
    private javax.swing.JMenuItem itemMateriais;
    private javax.swing.JMenuItem itemMudarSenha;
    private javax.swing.JMenuItem itemPedidos;
    private javax.swing.JMenuItem itemSobre;
    private javax.swing.JMenuItem itemTipos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lbAcesso;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbUsuario;
    private javax.swing.JMenu menuAjuda;
    private javax.swing.JMenu menuCadastros;
    private javax.swing.JMenu menuSistema;
    private javax.swing.JMenu menuUtiltarios;
    // End of variables declaration//GEN-END:variables
}
