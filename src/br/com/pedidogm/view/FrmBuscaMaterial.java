package br.com.pedidogm.view;

import br.com.pedidogm.dao.DAOFactory;
import br.com.pedidogm.dao.model.MaterialDAO;
import br.com.pedidogm.domain.Material;
import br.com.pedidogm.table.cellrenderer.MaterialCellRenderer;
import br.com.pedidogm.table.model.MaterialTableModel;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author douglas
 */
public class FrmBuscaMaterial extends javax.swing.JDialog {

    private List<Material> listaMateriais;

    public FrmBuscaMaterial(Window parent, List<Material> materials) {
        super(parent, DEFAULT_MODALITY_TYPE);
        initComponents();
        this.listaMateriais = materials;
        initialize();
    }

    public void atualizarTabela() {
        tbMaterias.setModel(new MaterialTableModel(this.listaMateriais));
        tbMaterias.setDefaultRenderer(Object.class, new MaterialCellRenderer());
        ((AbstractTableModel) tbMaterias.getModel()).fireTableDataChanged();
    }

    private void initialize() {

        atualizarTabela();
        eventoFechar();

        // Se teclarmos ESC nesta janela, ela irá se fechar:  
        this.getRootPane().registerKeyboardAction((ActionEvent e) -> {
            FrmBuscaMaterial.this.retornarMaterial();
            FrmBuscaMaterial.this.dispose();
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);

    }

    private void eventoFechar() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                retornarMaterial();
            }
        });
    }

    private Boolean retornarMaterial() {

        int row = this.tbMaterias.getSelectedRow();

        if (row != (-1)) {

            MaterialTableModel materialTableModel = new MaterialTableModel(this.listaMateriais);
            Material m = materialTableModel.get(row);

            if (this.getParent() instanceof FrmRegistroPedido) {
                FrmRegistroPedido frmRegistroPedido = FrmRegistroPedido.getInstancia();
                frmRegistroPedido.setMaterial(m);
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
        tbMaterias = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tfPesquisa = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Busca de Materiais");

        btOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/entrar_16x16.png"))); // NOI18N
        btOk.setMnemonic('O');
        btOk.setText("OK");
        btOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOkActionPerformed(evt);
            }
        });

        tbMaterias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbMaterias.setToolTipText("");
        tbMaterias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMateriasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbMaterias);

        jLabel1.setText("Pesquisar:");

        tfPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPesquisaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btOk))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPesquisa)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(7, 7, 7)
                .addComponent(btOk)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOkActionPerformed
        if (!retornarMaterial()) {
            this.dispose();
        }
    }//GEN-LAST:event_btOkActionPerformed

    private void tbMateriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMateriasMouseClicked
        if (evt.getClickCount() == 2) {
            retornarMaterial();
        }
    }//GEN-LAST:event_tbMateriasMouseClicked

    private void tfPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPesquisaKeyReleased
        MaterialDAO mdao = DAOFactory.getDefaultDAOFactory().getMaterialDAO();
        listaMateriais = mdao.bucarPorNome(tfPesquisa.getText());
        atualizarTabela();
    }//GEN-LAST:event_tfPesquisaKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btOk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbMaterias;
    private javax.swing.JTextField tfPesquisa;
    // End of variables declaration//GEN-END:variables
}
