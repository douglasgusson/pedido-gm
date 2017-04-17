package br.com.pedidogm.view;

import br.com.pedidogm.domain.Database;
import br.com.pedidogm.util.Seguranca;
import br.com.pedidogm.util.XMLFilter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.awt.Window;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Douglas Gusson
 */
public class FrmConfiguraBanco extends javax.swing.JDialog {

    private Database database;

    /**
     * Creates new form FrmConfiguraBanco
     *
     * @param parent
     */
    public FrmConfiguraBanco(Window parent) {
        super(parent, DEFAULT_MODALITY_TYPE);
        initComponents();
        inicializarFrame();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btSair = new javax.swing.JButton();
        tfSenha = new javax.swing.JPasswordField();
        tfUsuario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfPorta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfHost = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        btGravar = new javax.swing.JButton();
        btAlterar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        btImportar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configuração do Banco de Dados");
        setResizable(false);

        btSair.setMnemonic('S');
        btSair.setText("Sair");
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });

        jLabel1.setText("Senha:");

        jLabel2.setText("Usuário:");

        jLabel3.setText("Host:");

        jLabel4.setText("Porta:");

        jLabel5.setText("Nome do banco:");

        btGravar.setMnemonic('G');
        btGravar.setText("Gravar");
        btGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGravarActionPerformed(evt);
            }
        });

        btAlterar.setMnemonic('A');
        btAlterar.setText("Alterar");
        btAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarActionPerformed(evt);
            }
        });

        btCancelar.setMnemonic('C');
        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        btImportar.setMnemonic('I');
        btImportar.setText("Importar");
        btImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImportarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel6.setForeground(java.awt.Color.red);
        jLabel6.setText("Obs.: Quaisquer alterações, exigem que o sistema seja reiniciado.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfNome)
                            .addComponent(tfUsuario)
                            .addComponent(tfSenha)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btImportar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btGravar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btCancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btAlterar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btSair))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfHost)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(tfPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSair)
                    .addComponent(btGravar)
                    .addComponent(btAlterar)
                    .addComponent(btCancelar)
                    .addComponent(btImportar))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btSairActionPerformed

    private void btGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGravarActionPerformed
        try {
            String ip = Seguranca.encryptDecrypt(this.tfHost.getText());
            String porta = Seguranca.encryptDecrypt(this.tfPorta.getText());
            String nome = Seguranca.encryptDecrypt(this.tfNome.getText());
            String usuario = Seguranca.encryptDecrypt(this.tfUsuario.getText());
            String senha = Seguranca.encryptDecrypt(this.tfSenha.getText());

            database = new Database(
                    ip, porta, nome, usuario, senha);

            File xmlMap = new File(".db_conf_pedidogm.xml");
            OutputStream streamOut = null;

            if (!(xmlMap.exists())) {
                xmlMap.createNewFile();
            } else {
                xmlMap.delete();
                xmlMap.createNewFile();
            }

            streamOut = new FileOutputStream(xmlMap);
            XStream xstream = new XStream(new DomDriver());
            xstream.toXML(database, streamOut);

            JOptionPane.showMessageDialog(null,
                    "Para que as alterações surtam efeito, o sistema será fechado!\n\n"
                    + "*Após esse processo, abra o sistema e utilize normalmente.",
                    "Configurações registradas", JOptionPane.INFORMATION_MESSAGE);

            System.exit(0);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrmConfiguraBanco.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrmConfiguraBanco.class.getName()).log(Level.SEVERE, null, ex);
        } catch (StreamException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }//GEN-LAST:event_btGravarActionPerformed

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed
        btGravar.setEnabled(true);
        btCancelar.setEnabled(true);
        btAlterar.setEnabled(false);
        btSair.setEnabled(false);
        btImportar.setEnabled(false);
        tfHost.setEnabled(true);
        tfPorta.setEnabled(true);
        tfNome.setEnabled(true);
        tfUsuario.setEnabled(true);
        tfSenha.setEnabled(true);
        tfHost.requestFocus();
    }//GEN-LAST:event_btAlterarActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        inicializarFrame();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImportarActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new XMLFilter());
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            getDatabase(file.getAbsolutePath());
        }
        btGravar.setEnabled(true);
        btCancelar.setEnabled(true);
        btAlterar.setEnabled(false);
        btSair.setEnabled(false);
        btImportar.setEnabled(false);
        tfHost.setEnabled(true);
        tfPorta.setEnabled(true);
        tfNome.setEnabled(true);
        tfUsuario.setEnabled(true);
        tfSenha.setEnabled(true);
    }//GEN-LAST:event_btImportarActionPerformed

    private void inicializarFrame() {
        btCancelar.setEnabled(false);
        btGravar.setEnabled(false);
        btAlterar.setEnabled(true);
        btImportar.setEnabled(true);
        btSair.setEnabled(true);
        tfHost.setEnabled(false);
        tfPorta.setEnabled(false);
        tfNome.setEnabled(false);
        tfUsuario.setEnabled(false);
        tfSenha.setEnabled(false);
        getDatabase();
    }

    private void carregaDadosForm() {
        tfHost.setText(database.getHost());
        tfPorta.setText(database.getPorta());
        tfNome.setText(database.getNomeBanco());
        tfUsuario.setText(database.getUsuario());
        tfSenha.setText(database.getSenha());
    }

    private boolean getDatabase() {
        try {
            StringBuilder xml = new StringBuilder();
            Scanner scanner = new Scanner(new FileReader(".db_conf_pedidogm.xml"));
            while (scanner.hasNext()) {
                xml.append(scanner.next());
            }
            XStream xStream = new XStream(new DomDriver());
            database = (Database) xStream.fromXML(xml.toString());
            carregaDadosForm();

            return true;
        } catch (FileNotFoundException ex) {
        }
        return false;
    }

    private boolean getDatabase(String arq) {
        try {
            StringBuilder xml = new StringBuilder();
            Scanner scanner = new Scanner(new FileReader(arq));
            while (scanner.hasNext()) {
                xml.append(scanner.next());
            }
            XStream xStream = new XStream(new DomDriver());
            database = (Database) xStream.fromXML(xml.toString());
            carregaDadosForm();

            return true;
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (ClassCastException ex) {
            JOptionPane.showMessageDialog(null, "O arquivo não pôde ser importado. \nERRO: " + ex);
        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btGravar;
    private javax.swing.JButton btImportar;
    private javax.swing.JButton btSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField tfHost;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfPorta;
    private javax.swing.JPasswordField tfSenha;
    private javax.swing.JTextField tfUsuario;
    // End of variables declaration//GEN-END:variables
}
