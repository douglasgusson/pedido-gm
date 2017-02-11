package br.com.pedidogm.view;

import br.com.pedidogm.dao.DAOFactory;
import br.com.pedidogm.dao.model.AcabamentoDAO;
import br.com.pedidogm.domain.Acabamento;
import java.awt.Color;
import java.awt.Window;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 *
 * @author douglas
 */
public class FrmRegistroAcabamento extends javax.swing.JDialog {

    private static final int OPCAO_INSERIR = 0;
    private static final int OPCAO_ALTERAR = 1;

    private int opcao;
    private List<Acabamento> listaAcabamentos;
    private int indexRegistro;
    private Acabamento acabamento;

    Border borderRed = BorderFactory.createLineBorder(Color.red);
    Border borderDefault = BorderFactory.createLineBorder(Color.gray);


    public FrmRegistroAcabamento(Window parent) {
        super(parent, DEFAULT_MODALITY_TYPE);
        initComponents();
        initialize();
    }

    public FrmRegistroAcabamento(Window parent, List<Acabamento> lista) {
        this(parent);
        this.listaAcabamentos = lista;
        this.indexRegistro = -1;
        this.opcao = OPCAO_INSERIR;
    }

    public FrmRegistroAcabamento(Window parent, List<Acabamento> lista, int indice) {
        this(parent, lista);
        if (indice >= 0 && indice < lista.size()) {
            this.indexRegistro = indice;
            this.opcao = OPCAO_ALTERAR;
            this.acabamento = lista.get(indexRegistro);
            tfDescricao.setText(acabamento.getDescricao());
        }
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
        tfDescricao = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btGravar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de Acabamento");

        jLabel1.setText("Descrição:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/sair_16x16.png"))); // NOI18N
        jButton1.setMnemonic('S');
        jButton1.setText("Sair");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/gravar_16x16.png"))); // NOI18N
        btGravar.setMnemonic('G');
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tfDescricao)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 164, Short.MAX_VALUE)
                        .addComponent(btGravar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btGravar))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGravarActionPerformed

        if ((this.tfDescricao.getText().trim()).equals("")) {
            this.tfDescricao.setBorder(borderRed);
            this.tfDescricao.requestFocus();
        } else {

            AcabamentoDAO acabamentoDAO = DAOFactory.getDefaultDAOFactory().getAcabamentoDAO();
            Acabamento a = new Acabamento();

            switch (opcao) {

                case OPCAO_INSERIR:

                    a.setDescricao(this.tfDescricao.getText().trim());
                    a.setCriacao(LocalDateTime.now());
                    a.setAlteracao(LocalDateTime.now());

                    acabamentoDAO.inserir(a);

                    break;

                case OPCAO_ALTERAR:

                    a.setId(this.acabamento.getId());
                    a.setDescricao(this.tfDescricao.getText().trim());
                    a.setCriacao(this.acabamento.getCriacao());
                    a.setAlteracao(LocalDateTime.now());

                    acabamentoDAO.alterar(a);

                    break;
            }

            FrmAcabamentos frmAcabamentos = FrmAcabamentos.getInstancia();
            frmAcabamentos.atualizarTabela();

            if (OPCAO_ALTERAR == opcao) {
                this.dispose();
            } else {
                initialize();
            }

        }
    }//GEN-LAST:event_btGravarActionPerformed

    private void initialize() {
        this.tfDescricao.setText("");
        this.tfDescricao.requestFocus();
        this.tfDescricao.setBorder(borderDefault);
        getRootPane().setDefaultButton(btGravar);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btGravar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField tfDescricao;
    // End of variables declaration//GEN-END:variables
}
