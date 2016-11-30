package br.com.pedidogm.view;

import br.com.pedidogm.dao.DAOFactory;
import br.com.pedidogm.dao.model.UsuarioDAO;
import br.com.pedidogm.domain.Usuario;
import br.com.pedidogm.util.Seguranca;
import java.awt.Window;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author douglas
 */
public class FrmRegistroUsuario extends javax.swing.JDialog {

    private static final int OPCAO_INSERIR = 0;
    private static final int OPCAO_ALTERAR = 1;

    private int opcao;
    private List<Usuario> listaUsuarios;
    private int indexRegistro;
    private Usuario usuario;

    public FrmRegistroUsuario(Window parent) {
        super(parent, DEFAULT_MODALITY_TYPE);
        initComponents();
    }

    public FrmRegistroUsuario(Window parent, List<Usuario> lista) {
        this(parent);
        this.listaUsuarios = lista;
        this.indexRegistro = -1;
        this.opcao = OPCAO_INSERIR;
    }

    public FrmRegistroUsuario(Window parent, List<Usuario> lista, int indice) {
        this(parent, lista);
        if (indice >= 0 && indice < lista.size()) {
            this.indexRegistro = indice;
            this.opcao = OPCAO_ALTERAR;
            this.usuario = lista.get(indexRegistro);
            preencherCampos();
            this.tfSenha.setEnabled(false);
            this.tfConfirmacao.setEnabled(false);
        }
    }

    private void preencherCampos() {
        this.tfNomeUsuario.setText(this.usuario.getNomeUsuario());
        this.tfNomeCompleto.setText(this.usuario.getNomeCompleto());
        this.tfEmail.setText(this.usuario.getEmail());
        this.ckNovaSenha.setSelected(this.usuario.isNovaSenha());
        this.ckAtivo.setSelected(this.usuario.isAtivo());
        this.ckAdministrador.setSelected(this.usuario.isAdmin());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfNomeUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfNomeCompleto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfConfirmacao = new javax.swing.JPasswordField();
        tfSenha = new javax.swing.JPasswordField();
        ckNovaSenha = new javax.swing.JCheckBox();
        ckAtivo = new javax.swing.JCheckBox();
        ckAdministrador = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        btGravar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nome de Usuário:");

        jLabel2.setText("Nome Completo:");

        jLabel3.setText("Email:");

        jLabel4.setText("Senha:");

        jLabel5.setText("Confirmação:");

        ckNovaSenha.setText("Novo senha");

        ckAtivo.setText("Ativo");

        ckAdministrador.setText("Administrador");

        jButton1.setText("Sair");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btGravar.setText("Gravar");
        btGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGravarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckAtivo)
                    .addComponent(ckNovaSenha)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tfSenha)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1)
                                .addComponent(tfNomeUsuario)
                                .addComponent(jLabel2)
                                .addComponent(tfNomeCompleto, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
                            .addComponent(jLabel3))
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tfConfirmacao, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tfEmail, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(ckAdministrador))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(233, Short.MAX_VALUE)
                .addComponent(btGravar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNomeCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfConfirmacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ckNovaSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckAdministrador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckAtivo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btGravar))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGravarActionPerformed

        if ((this.tfNomeUsuario.getText().trim()).equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Favor informar o nome do usuario!",
                    "Campo obrigatório", JOptionPane.INFORMATION_MESSAGE);
            this.tfNomeUsuario.requestFocus();
        } else if ((this.tfNomeCompleto.getText().trim()).equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Favor informar o nome completo do usuario!",
                    "Campo obrigatório", JOptionPane.INFORMATION_MESSAGE);
            this.tfNomeCompleto.requestFocus();
        } else {

            UsuarioDAO usuarioDAO = DAOFactory.getDefaultDAOFactory().getUsuarioDAO();
            Usuario u = new Usuario();

            switch (opcao) {

                case OPCAO_INSERIR:

                    u.setNomeUsuario(this.tfNomeUsuario.getText().trim());
                    u.setNomeCompleto(this.tfNomeCompleto.getText().trim());
                    u.setEmail(this.tfEmail.getText().trim());
                    u.setSenha(Seguranca.criptografarSHA256(this.tfSenha.getText()));
                    u.setNovaSenha(this.ckNovaSenha.isSelected());
                    u.setAtivo(this.ckAtivo.isSelected());
                    u.setAdmin(this.ckAdministrador.isSelected());
                    u.setUltimoAcesso(LocalDateTime.now());

                    usuarioDAO.inserir(u);

                    break;

                case OPCAO_ALTERAR:

                    u.setId(this.usuario.getId());
                    u.setNomeUsuario(this.tfNomeUsuario.getText().trim());
                    u.setNomeCompleto(this.tfNomeCompleto.getText().trim());
                    u.setEmail(this.tfEmail.getText().trim());
                    u.setSenha(this.usuario.getSenha());
                    u.setNovaSenha(this.ckNovaSenha.isSelected());
                    u.setAtivo(this.ckAtivo.isSelected());
                    u.setAdmin(this.ckAdministrador.isSelected());
                    u.setUltimoAcesso(this.usuario.getUltimoAcesso());

                    usuarioDAO.alterar(u);

                    break;
            }

            FrmUsuarios frmUsuarios = FrmUsuarios.getInstancia();
            frmUsuarios.atualizarTabela();
            //initialize();

        }

    }//GEN-LAST:event_btGravarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btGravar;
    private javax.swing.JCheckBox ckAdministrador;
    private javax.swing.JCheckBox ckAtivo;
    private javax.swing.JCheckBox ckNovaSenha;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField tfConfirmacao;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfNomeCompleto;
    private javax.swing.JTextField tfNomeUsuario;
    private javax.swing.JPasswordField tfSenha;
    // End of variables declaration//GEN-END:variables
}
