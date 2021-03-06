package br.com.pedidogm.view;

import br.com.pedidogm.util.Sessao;
import br.com.pedidogm.util.Info;
import br.com.pedidogm.util.GUIUtils;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

        jPanel1 = new javax.swing.JPanel();
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
        itemClientes = new javax.swing.JMenuItem();
        itemMateriais = new javax.swing.JMenuItem();
        itemTransportadores = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        itemTipos = new javax.swing.JMenuItem();
        itemAcabamentos = new javax.swing.JMenuItem();
        menuSistema = new javax.swing.JMenu();
        itemConfiguracoes = new javax.swing.JMenuItem();
        itemMudarSenha = new javax.swing.JMenuItem();
        itemLogout = new javax.swing.JMenuItem();
        itemSair = new javax.swing.JMenuItem();
        menuAjuda = new javax.swing.JMenu();
        itemManualUso = new javax.swing.JMenuItem();
        itemSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PedidoGM");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(java.awt.Color.white);

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
        btLogout.setContentAreaFilled(false);
        btLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogoutActionPerformed(evt);
            }
        });

        btSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/sair_16x16.png"))); // NOI18N
        btSair.setText("Sair");
        btSair.setContentAreaFilled(false);
        btSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(864, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(433, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        barraMenu.setOpaque(false);

        menuCadastros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/cadastros.png"))); // NOI18N
        menuCadastros.setMnemonic('C');
        menuCadastros.setText("Cadastros");
        menuCadastros.setToolTipText("T");

        itemPedidos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
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

        itemClientes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        itemClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/cliente_16x16.png"))); // NOI18N
        itemClientes.setMnemonic('C');
        itemClientes.setText("Clientes");
        itemClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemClientesActionPerformed(evt);
            }
        });
        menuCadastros.add(itemClientes);

        itemMateriais.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, 0));
        itemMateriais.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/produto_16x16.png"))); // NOI18N
        itemMateriais.setMnemonic('M');
        itemMateriais.setText("Materiais");
        itemMateriais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMateriaisActionPerformed(evt);
            }
        });
        menuCadastros.add(itemMateriais);

        itemTransportadores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, 0));
        itemTransportadores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/transportador_16x16.png"))); // NOI18N
        itemTransportadores.setMnemonic('T');
        itemTransportadores.setText("Transportadores");
        itemTransportadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemTransportadoresActionPerformed(evt);
            }
        });
        menuCadastros.add(itemTransportadores);
        menuCadastros.add(jSeparator1);

        itemTipos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/tabela.png"))); // NOI18N
        itemTipos.setMnemonic('I');
        itemTipos.setText("Tipos de Item");
        itemTipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemTiposActionPerformed(evt);
            }
        });
        menuCadastros.add(itemTipos);

        itemAcabamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/tabela.png"))); // NOI18N
        itemAcabamentos.setMnemonic('A');
        itemAcabamentos.setText("Acabamentos");
        itemAcabamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAcabamentosActionPerformed(evt);
            }
        });
        menuCadastros.add(itemAcabamentos);

        barraMenu.add(menuCadastros);

        menuSistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/sistema.png"))); // NOI18N
        menuSistema.setMnemonic('S');
        menuSistema.setText("Sistema");

        itemConfiguracoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/engrenagem_16x16.png"))); // NOI18N
        itemConfiguracoes.setMnemonic('C');
        itemConfiguracoes.setText("Configurações");
        itemConfiguracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConfiguracoesActionPerformed(evt);
            }
        });
        menuSistema.add(itemConfiguracoes);

        itemMudarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/usuario_16x16.png"))); // NOI18N
        itemMudarSenha.setMnemonic('M');
        itemMudarSenha.setText("Mudar minha senha");
        itemMudarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMudarSenhaActionPerformed(evt);
            }
        });
        menuSistema.add(itemMudarSenha);

        itemLogout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        itemLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/logout_16x16.png"))); // NOI18N
        itemLogout.setMnemonic('L');
        itemLogout.setText("Logout");
        itemLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemLogoutActionPerformed(evt);
            }
        });
        menuSistema.add(itemLogout);

        itemSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        itemSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/sair_16x16.png"))); // NOI18N
        itemSair.setMnemonic('S');
        itemSair.setText("Sair");
        itemSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSairActionPerformed(evt);
            }
        });
        menuSistema.add(itemSair);

        barraMenu.add(menuSistema);

        menuAjuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/ajuda.png"))); // NOI18N
        menuAjuda.setMnemonic('A');
        menuAjuda.setText("Ajuda");

        itemManualUso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/manual_16x16.png"))); // NOI18N
        itemManualUso.setMnemonic('M');
        itemManualUso.setText("Manual de Uso");
        itemManualUso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemManualUsoActionPerformed(evt);
            }
        });
        menuAjuda.add(itemManualUso);

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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        FrmPedidos pedidos = new FrmPedidos(this);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        pedidos.setVisible(true);
    }//GEN-LAST:event_itemPedidosActionPerformed

    private void itemClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemClientesActionPerformed
        FrmClientes clientes = new FrmClientes(this);
        clientes.setVisible(true);
    }//GEN-LAST:event_itemClientesActionPerformed

    private void itemSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSobreActionPerformed
        FrmSobre sobre = new FrmSobre(this);
        sobre.setVisible(true);
    }//GEN-LAST:event_itemSobreActionPerformed

    private void itemSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSairActionPerformed
        btSairActionPerformed(evt);
    }//GEN-LAST:event_itemSairActionPerformed

    private void itemLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemLogoutActionPerformed
        btLogoutActionPerformed(evt);
    }//GEN-LAST:event_itemLogoutActionPerformed

    private void itemAcabamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAcabamentosActionPerformed
        FrmAcabamentos acabamentos = new FrmAcabamentos(this);
        acabamentos.setVisible(true);
    }//GEN-LAST:event_itemAcabamentosActionPerformed

    private void itemTiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemTiposActionPerformed
        FrmTiposItem tiposItem = new FrmTiposItem(this);
        tiposItem.setVisible(true);
    }//GEN-LAST:event_itemTiposActionPerformed

    private void itemManualUsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemManualUsoActionPerformed
        try {
            URI uri = new URI("https://douglasgusson.github.io/pedido-gm/manual");
            Desktop dt = Desktop.getDesktop();
            dt.browse(uri);
        } catch (URISyntaxException | IOException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao tentar acessar o manual.\nDETALHES: " + ex);
        }
    }//GEN-LAST:event_itemManualUsoActionPerformed

    private void itemTransportadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemTransportadoresActionPerformed
        FrmTransportadores frmTransportadores = new FrmTransportadores(this);
        frmTransportadores.setVisible(true);
    }//GEN-LAST:event_itemTransportadoresActionPerformed

    private void initialize() {
        setPermissoes();

        this.setTitle(Info.getNameMoreVersion());

        lbUsuario.setText(Sessao.getUsuario().getNomeUsuario());
        lbAcesso.setText(Sessao.acessoToString());
        lbNome.setText(Sessao.getUsuario().getNomeCompleto());

        setDefaultCloseOperation(FrmPrincipal.DO_NOTHING_ON_CLOSE);
        setIcon(this);

        if (Sessao.getUsuario().getNovaSenha()) {
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
        if (!Sessao.getUsuario().getAdmin()) {
            itemConfiguracoes.setEnabled(false);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JButton btLogout;
    private javax.swing.JButton btSair;
    private javax.swing.JMenuItem itemAcabamentos;
    private javax.swing.JMenuItem itemClientes;
    private javax.swing.JMenuItem itemConfiguracoes;
    private javax.swing.JMenuItem itemLogout;
    private javax.swing.JMenuItem itemManualUso;
    private javax.swing.JMenuItem itemMateriais;
    private javax.swing.JMenuItem itemMudarSenha;
    private javax.swing.JMenuItem itemPedidos;
    private javax.swing.JMenuItem itemSair;
    private javax.swing.JMenuItem itemSobre;
    private javax.swing.JMenuItem itemTipos;
    private javax.swing.JMenuItem itemTransportadores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lbAcesso;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbUsuario;
    private javax.swing.JMenu menuAjuda;
    private javax.swing.JMenu menuCadastros;
    private javax.swing.JMenu menuSistema;
    // End of variables declaration//GEN-END:variables
}
