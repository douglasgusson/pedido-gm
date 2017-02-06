package br.com.pedidogm.view;

import br.com.pedidogm.dao.DAOFactory;
import br.com.pedidogm.dao.model.ClienteDAO;
import br.com.pedidogm.dao.model.ItemPedidoDAO;
import br.com.pedidogm.dao.model.MaterialDAO;
import br.com.pedidogm.dao.model.PedidoDAO;
import br.com.pedidogm.dao.model.TipoItemDAO;
import br.com.pedidogm.domain.Cliente;
import br.com.pedidogm.domain.ItemPedido;
import br.com.pedidogm.domain.Material;
import br.com.pedidogm.domain.Pedido;
import br.com.pedidogm.domain.Sessao;
import br.com.pedidogm.domain.TipoItem;
import br.com.pedidogm.table.model.ItemPedidoTableModel;
import br.com.pedidogm.util.MascaraNumerica;
import java.awt.Color;
import java.awt.Window;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Douglas Gusson
 */
public class FrmRegistroPedido extends javax.swing.JDialog {

    private static FrmRegistroPedido INSTANCIA;

    private Cliente cliente = new Cliente();
    private Material material = new Material();
    private Pedido pedido = new Pedido();
    private List<Pedido> listaPedidos = new ArrayList<>();
    private List<ItemPedido> itensPedido = new ArrayList<>();
    private BigDecimal totalPedido = new BigDecimal("0");

    private Vector<TipoItem> tipos;
    private DefaultComboBoxModel<TipoItem> modeloComboBoxTipoItem;

    private static final int OPCAO_INSERIR = 0;
    private static final int OPCAO_ALTERAR = 1;

    private int indexRegistro;
    private int opcao;

    private final int METRO_QUADRADO = 0;
    private final int METRO_CUBICO = 1;
    private final int METRO_LINEAR = 2;

    private final String acabamentos[] = {"BRUTO", "POLIDO", "BIPOLIDO", "LEVIGADO"};
    private final String espessuras[] = {"1,5 cm", "2,0 cm", "3,0 cm", "4,0 cm"};

    Border borderRed = BorderFactory.createLineBorder(Color.red);
    Border borderDefault = BorderFactory.createLineBorder(Color.gray);

    public static FrmRegistroPedido getInstancia() {
        return INSTANCIA;
    }

    public FrmRegistroPedido(Window parent) {
        super(parent, DEFAULT_MODALITY_TYPE);
        initComponents();
        INSTANCIA = this;
        initialize();
    }

    public FrmRegistroPedido(Window parent, List<Pedido> lista) {
        this(parent);
        this.listaPedidos = lista;
        this.indexRegistro = -1;
        this.opcao = OPCAO_INSERIR;
    }

    public FrmRegistroPedido(Window parent, List<Pedido> lista, int index) {
        this(parent, lista);
        if (index >= 0 && index < listaPedidos.size()) {
            this.indexRegistro = index;
            this.opcao = OPCAO_ALTERAR;
            this.pedido = lista.get(indexRegistro);
            System.out.println(indexRegistro + ", " + listaPedidos.size());
            preencherCampos();
        }
    }

    public void atualizarTabela() {
        tbItensPedido.setModel(new ItemPedidoTableModel(this.itensPedido));
        ((AbstractTableModel) tbItensPedido.getModel()).fireTableDataChanged();
    }

    private void preencherCampos() {
        this.tfNomeCliente.setText(pedido.getCliente().getNome());
        this.taObservacoes.setText(pedido.getObservacoes());
    }

    private void initialize() {

        atualizarTabela();
        tfNomeCliente.setText("");
        limparCamposItem();

        lbData.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEE, dd MMM yyyy, HH:mm")));

        KeyAdapter somenteNumeros = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                String caracteres = "0123456789,";
                if (!caracteres.contains(evt.getKeyChar() + "")) {
                    evt.consume();
                }
            }
        };

        tfQuantidade.addKeyListener(somenteNumeros);
        tfMetragem.addKeyListener(somenteNumeros);

        tfComprimentoBr.addKeyListener(new MascaraNumerica(tfComprimentoBr, 3, 2));
        tfComprimentoLiq.addKeyListener(new MascaraNumerica(tfComprimentoLiq, 3, 2));

        tfAlturaBr.addKeyListener(new MascaraNumerica(tfAlturaBr, 3, 2));
        tfAlturaLiq.addKeyListener(new MascaraNumerica(tfAlturaLiq, 3, 2));

        tfLarguraBr.addKeyListener(new MascaraNumerica(tfLarguraBr, 3, 2));
        tfLarguraLiq.addKeyListener(new MascaraNumerica(tfLarguraLiq, 3, 2));

        for (String acabamento : acabamentos) {
            this.cbAcabamento.addItem(acabamento);
        }

        for (String espessura : espessuras) {
            this.cbEspessura.addItem(espessura);
        }

        mudarEstadoFrm(METRO_QUADRADO);

        this.cbTipo.setSelectedItem("CHAPA");
        this.cbAcabamento.setSelectedItem("POLIDO");
        this.cbEspessura.setSelectedItem("2,0 cm");

        TipoItemDAO tipoItemDAO = DAOFactory.getDefaultDAOFactory().getTipoItemDAO();
        tipos = new Vector<>(tipoItemDAO.listarTodos());

        FocusAdapter selectAllText = new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent evt) {
                JTextField src = (JTextField) evt.getSource();
                src.selectAll();
            }
        };

        tfDesconto.addFocusListener(selectAllText);

        modeloComboBoxTipoItem = new DefaultComboBoxModel<>(tipos);
        cbTipo.setModel(modeloComboBoxTipoItem);

    }

    private void limparCamposItem() {
        tfMaterial.setText("");
        tfQuantidade.setText("");
        tfMetragem.setText("");
        tfComprimentoBr.setText("");
        tfComprimentoLiq.setText("");
        tfAlturaBr.setText("");
        tfAlturaLiq.setText("");
        tfLarguraBr.setText("");
        tfLarguraLiq.setText("");
        tfLarguraLiq.setText("");
        tfMetragem.setText("");
        tfValorUnitario.setText("");
        tfDesconto.setText("0,00");
        tfValorDesconto.setText("0,00");
        tfTotalItem.setText("0,00");
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.tfNomeCliente.setText(cliente.getNome());
    }

    public void setMaterial(Material material) {
        this.material = material;
        this.tfMaterial.setText(material.getNome());
    }

    private void mudarEstadoFrm(int index) {
        switch (index) {
            case METRO_CUBICO:
                this.tfLarguraBr.setEnabled(true);
                this.tfLarguraLiq.setEnabled(true);
                this.cbAcabamento.setEnabled(false);
                this.cbEspessura.setEnabled(false);
                this.cbAcabamento.setSelectedItem("BRUTO");
                break;
            default:
                this.tfLarguraBr.setEnabled(false);
                this.tfLarguraLiq.setEnabled(false);
                this.cbAcabamento.setEnabled(true);
                this.cbEspessura.setEnabled(true);
                break;

        }
    }

    private void calcularValores() {

        int tipo = 0;

        String quant = "0";

        BigDecimal compLiq = new BigDecimal("0.00");
        BigDecimal altLiq = new BigDecimal("0.00");
        BigDecimal largLiq = new BigDecimal("0.00");

        BigDecimal valorUnit = new BigDecimal("0.00");
        BigDecimal desconto = new BigDecimal("0.00");
        BigDecimal metragem = new BigDecimal("0.00");

        try {
            quant = this.tfQuantidade.getText();
            compLiq = new BigDecimal(this.tfComprimentoLiq.getText().replace(",", "."));
            altLiq = new BigDecimal(this.tfAlturaLiq.getText().replace(",", "."));
            valorUnit = new BigDecimal(this.tfValorUnitario.getText().replace(",", "."));
            desconto = new BigDecimal(this.tfDesconto.getText().replace(",", "."));
        } catch (NumberFormatException e) {
        }

        switch (tipo) {
            case METRO_QUADRADO:
                metragem = compLiq.multiply(altLiq).multiply(new BigDecimal(quant)).setScale(3, RoundingMode.HALF_EVEN);
                break;
            case METRO_CUBICO:
                metragem = compLiq.multiply(altLiq).multiply(largLiq).multiply(new BigDecimal(quant)).setScale(3, RoundingMode.HALF_EVEN);
                break;

        }

        BigDecimal totalItem = metragem.multiply(valorUnit).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal valorDesconto = totalItem.multiply((desconto.divide(new BigDecimal("100.0")))).setScale(2, RoundingMode.HALF_EVEN);
        totalItem = totalItem.subtract(valorDesconto).setScale(2, RoundingMode.HALF_EVEN);

        this.tfMetragem.setText(metragem.toString().replace(".", ","));
        this.tfValorDesconto.setText(valorDesconto.toString().replace(".", ","));
        this.tfTotalItem.setText(totalItem.toString().replace(".", ","));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btGravar = new javax.swing.JButton();
        brSair = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tfNomeCliente = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbItensPedido = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        tfPlaca = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfMotorista = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        tfMaterial = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfQuantidade = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfAlturaBr = new javax.swing.JTextField();
        tfComprimentoBr = new javax.swing.JTextField();
        tfLarguraBr = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tfComprimentoLiq = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tfAlturaLiq = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tfLarguraLiq = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cbAcabamento = new javax.swing.JComboBox<String>();
        jLabel14 = new javax.swing.JLabel();
        tfMetragem = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        tfValorUnitario = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        tfDesconto = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tfTotalItem = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cbEspessura = new javax.swing.JComboBox();
        tfValorDesconto = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        cbTipo = new javax.swing.JComboBox();
        lbData = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taObservacoes = new javax.swing.JTextArea();
        tfTotalPedido = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de Pedido");

        btGravar.setText("Gravar");
        btGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGravarActionPerformed(evt);
            }
        });

        brSair.setText("Sair");
        brSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brSairActionPerformed(evt);
            }
        });

        jLabel1.setText("Cliente:");

        tfNomeCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfNomeClienteFocusLost(evt);
            }
        });

        tbItensPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbItensPedido);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Transporte"));

        jLabel2.setText("Placa:");

        jLabel3.setText("Motorista:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(tfPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 191, Short.MAX_VALUE))
                    .addComponent(tfMotorista))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Item"));

        tfMaterial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfMaterialFocusLost(evt);
            }
        });

        jLabel4.setText("Material:");

        tfQuantidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfQuantidadeFocusLost(evt);
            }
        });

        jLabel5.setText("Quant.:");

        jLabel6.setText("Tipo:");

        tfAlturaBr.setText("0,00");
        tfAlturaBr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfAlturaBrFocusLost(evt);
            }
        });

        tfComprimentoBr.setText("0,00");
        tfComprimentoBr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfComprimentoBrFocusLost(evt);
            }
        });

        tfLarguraBr.setText("0,00");
        tfLarguraBr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfLarguraBrFocusLost(evt);
            }
        });

        jLabel7.setText("X");

        jLabel8.setText("X");

        jLabel9.setBackground(new java.awt.Color(52, 152, 219));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Medidas Brutas");
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setBackground(new java.awt.Color(26, 188, 156));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Medidas Líquidas");
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tfComprimentoLiq.setText("0,00");
        tfComprimentoLiq.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfComprimentoLiqFocusLost(evt);
            }
        });

        jLabel11.setText("X");

        tfAlturaLiq.setText("0,00");
        tfAlturaLiq.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfAlturaLiqFocusLost(evt);
            }
        });

        jLabel12.setText("X");

        tfLarguraLiq.setText("0,00");
        tfLarguraLiq.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfLarguraLiqFocusLost(evt);
            }
        });

        jLabel13.setText("Acabamento:");

        jLabel14.setText("Metragem:");

        tfMetragem.setText("0,00");
        tfMetragem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfMetragemFocusLost(evt);
            }
        });

        jLabel15.setText("Valor Unit.:");

        tfValorUnitario.setText("0,00");
        tfValorUnitario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfValorUnitarioFocusLost(evt);
            }
        });

        jLabel16.setText("Desc. (%):");

        tfDesconto.setText("0,00");
        tfDesconto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfDescontoFocusLost(evt);
            }
        });

        jLabel17.setText("Total:");

        tfTotalItem.setText("0,00");
        tfTotalItem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfTotalItemFocusLost(evt);
            }
        });

        jLabel18.setText("Espessura:");

        cbEspessura.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbEspessuraFocusLost(evt);
            }
        });

        tfValorDesconto.setText("0,00");

        jLabel19.setText("Desc. (R$):");

        jButton1.setText("Adicionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTipoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tfMetragem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfComprimentoBr, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfQuantidade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(tfMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(cbEspessura, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(tfAlturaBr, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(5, 5, 5)
                                                .addComponent(jLabel8))
                                            .addComponent(jLabel15))
                                        .addGap(5, 5, 5)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfLarguraBr, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel16))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(tfComprimentoLiq, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(4, 4, 4)
                                                        .addComponent(jLabel11))
                                                    .addComponent(jLabel19))
                                                .addGap(6, 6, 6)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel17)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(tfAlturaLiq, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(5, 5, 5)
                                                        .addComponent(jLabel12)
                                                        .addGap(5, 5, 5)
                                                        .addComponent(tfLarguraLiq, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(tfValorDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(tfTotalItem, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(tfValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tfDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbAcabamento, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13)
                                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbEspessura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfAlturaBr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfComprimentoBr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfLarguraBr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel13))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfAlturaLiq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfComprimentoLiq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfLarguraLiq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(cbAcabamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfMetragem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfTotalItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfValorDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbData.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbData.setText("data...");

        jLabel20.setText("Observações:");

        taObservacoes.setColumns(20);
        taObservacoes.setRows(5);
        jScrollPane2.setViewportView(taObservacoes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfTotalPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btGravar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(brSair))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20))
                                .addGap(6, 6, 6)))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfTotalPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(brSair)
                    .addComponent(btGravar))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGravarActionPerformed

        PedidoDAO pedidoDAO = DAOFactory.getDefaultDAOFactory().getPedidoDAO();
        Pedido p = new Pedido();

        switch (opcao) {

            case OPCAO_INSERIR:
                p.setCliente(cliente);
                p.setValor(new BigDecimal(this.tfTotalPedido.getText().replace(",", ".")));
                p.setPlaca(this.tfPlaca.getText());
                p.setMotorista(this.tfMotorista.getText());
                p.setObservacoes(this.taObservacoes.getText());
                p.setDataCarregamento(LocalDate.now());
                p.setCriacao(LocalDateTime.now());
                p.setAlteracao(LocalDateTime.now());

                p.setUsuario(Sessao.getUsuario());

                pedidoDAO.inserir(p);

                ItemPedidoDAO itemPedidoDAO = DAOFactory.getDefaultDAOFactory().getItemPedidoDAO();

                Pedido ultimoPedido = pedidoDAO.buscarUltimoPedido();

                for (int i = 0; i < itensPedido.size(); i++) {
                    itensPedido.get(i).setPedido(ultimoPedido);
                    itemPedidoDAO.inserir(itensPedido.get(i));
                }

                break;

            case OPCAO_ALTERAR:
                break;
        }

        FrmPedidos frmPedidos = FrmPedidos.getInstancia();
        frmPedidos.atualizarTabela();

        if (OPCAO_ALTERAR == opcao) {
            this.dispose();
        } else {
            initialize();
        }

    }//GEN-LAST:event_btGravarActionPerformed

    private void brSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_brSairActionPerformed

    private void tfMaterialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfMaterialFocusLost

        String query = this.tfMaterial.getText().trim();

        if (!query.equals("")) {

            MaterialDAO materialDAO = DAOFactory.getDefaultDAOFactory().getMaterialDAO();

            List<Material> materias = materialDAO.bucarPorNome(query);

            if (materias.size() == 1) {
                setMaterial(materias.get(0));
            } else {
                this.tfMaterial.setText("");
                FrmBuscaMaterial buscaMaterial = new FrmBuscaMaterial(this, materias);
                buscaMaterial.setVisible(true);
            }
        }
    }//GEN-LAST:event_tfMaterialFocusLost

    private void tfNomeClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNomeClienteFocusLost

        String query = this.tfNomeCliente.getText().trim();

        if (!query.equals("")) {

            ClienteDAO clienteDAO = DAOFactory.getDefaultDAOFactory().getClienteDAO();

            List<Cliente> clientes = clienteDAO.bucarPorNome(query);

            if (clientes.isEmpty()) {
                this.tfNomeCliente.setText("");
            } else if (clientes.size() == 1) {
                setCliente(clientes.get(0));
            } else {
                this.tfNomeCliente.setText("");
                FrmBuscaCliente buscaCliente = new FrmBuscaCliente(this, clientes);
                buscaCliente.setVisible(true);
            }
        }
    }//GEN-LAST:event_tfNomeClienteFocusLost

    private void cbEspessuraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbEspessuraFocusLost
        if (this.cbEspessura.getSelectedIndex() == 0) {
            this.tfLarguraBr.setText("0,015");
            this.tfLarguraLiq.setText("0,015");
        } else if (this.cbEspessura.getSelectedIndex() == 1) {
            this.tfLarguraBr.setText("0,02");
            this.tfLarguraLiq.setText("0,02");
        } else if (this.cbEspessura.getSelectedIndex() == 2) {
            this.tfLarguraBr.setText("0,03");
            this.tfLarguraLiq.setText("0,03");
        } else {
            this.tfLarguraBr.setText("0,04");
            this.tfLarguraLiq.setText("0,04");
        }
    }//GEN-LAST:event_cbEspessuraFocusLost

    private void tfTotalItemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfTotalItemFocusLost


    }//GEN-LAST:event_tfTotalItemFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {

            ItemPedido itemPedido = new ItemPedido();

            itemPedido.setMaterial(this.material);
            TipoItem ti = (TipoItem) this.cbTipo.getItemAt(this.cbTipo.getSelectedIndex());
            itemPedido.setTipoItem(ti);

            itemPedido.setQuantidade(Long.parseLong(this.tfQuantidade.getText()));
            itemPedido.setComprimentoBr(new BigDecimal(this.tfComprimentoBr.getText().replace(",", ".")));
            itemPedido.setAlturaBr(new BigDecimal(this.tfAlturaBr.getText().replace(",", ".")));
            itemPedido.setLarguraBr(new BigDecimal(this.tfLarguraBr.getText().replace(",", ".")));
            itemPedido.setComprimentoLiq(new BigDecimal(this.tfComprimentoLiq.getText().replace(",", ".")));
            itemPedido.setAlturaLiq(new BigDecimal(this.tfAlturaLiq.getText().replace(",", ".")));
            itemPedido.setLarguraLiq(new BigDecimal(this.tfLarguraLiq.getText().replace(",", ".")));
            itemPedido.setAcabamento(this.cbAcabamento.getSelectedItem().toString());
            itemPedido.setMetragem(new BigDecimal(this.tfMetragem.getText().replace(",", ".")));
            itemPedido.setValorUnitario(new BigDecimal(this.tfValorUnitario.getText().replace(",", ".")));
            itemPedido.setDesconto(new BigDecimal(this.tfDesconto.getText().replace(",", ".")));
            itemPedido.setValorTotal(new BigDecimal(this.tfTotalItem.getText().replace(",", ".")));

            this.totalPedido = this.totalPedido.add(itemPedido.getValorTotal());
            this.tfTotalPedido.setText(totalPedido.toString().replace(".", ","));

            itensPedido.add(itemPedido);
            atualizarTabela();
            limparCamposItem();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Formato inválido para número. \nERRO:" + e);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void tfComprimentoBrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfComprimentoBrFocusLost

        BigDecimal compBr = new BigDecimal("0.00");

        try {
            compBr = new BigDecimal(this.tfComprimentoBr.getText().replace(",", "."));
        } catch (NumberFormatException e) {
        }

        BigDecimal compLiq = compBr.subtract(new BigDecimal("0.05"));
        this.tfComprimentoLiq.setText(compLiq.toString().replace(".", ","));

        calcularValores();

    }//GEN-LAST:event_tfComprimentoBrFocusLost

    private void tfAlturaBrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfAlturaBrFocusLost

        BigDecimal altBr = new BigDecimal("0.00");

        try {
            altBr = new BigDecimal(this.tfAlturaBr.getText().replace(",", "."));
        } catch (NumberFormatException e) {
        }

        BigDecimal altLiq = altBr.subtract(new BigDecimal("0.05"));
        this.tfAlturaLiq.setText(altLiq.toString().replace(".", ","));

        calcularValores();

    }//GEN-LAST:event_tfAlturaBrFocusLost

    private void tfLarguraBrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfLarguraBrFocusLost
        calcularValores();
    }//GEN-LAST:event_tfLarguraBrFocusLost

    private void tfComprimentoLiqFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfComprimentoLiqFocusLost
        calcularValores();
    }//GEN-LAST:event_tfComprimentoLiqFocusLost

    private void tfAlturaLiqFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfAlturaLiqFocusLost
        calcularValores();
    }//GEN-LAST:event_tfAlturaLiqFocusLost

    private void tfLarguraLiqFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfLarguraLiqFocusLost
        calcularValores();
    }//GEN-LAST:event_tfLarguraLiqFocusLost

    private void tfMetragemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfMetragemFocusLost
        calcularValores();
    }//GEN-LAST:event_tfMetragemFocusLost

    private void tfValorUnitarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfValorUnitarioFocusLost
        calcularValores();
    }//GEN-LAST:event_tfValorUnitarioFocusLost

    private void tfDescontoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfDescontoFocusLost
        calcularValores();
    }//GEN-LAST:event_tfDescontoFocusLost

    private void tfQuantidadeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfQuantidadeFocusLost
        calcularValores();
    }//GEN-LAST:event_tfQuantidadeFocusLost

    private void cbTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTipoItemStateChanged
        TipoItem ti = (TipoItem) this.cbTipo.getItemAt(this.cbTipo.getSelectedIndex());
        mudarEstadoFrm(ti.getReferenciaCalculo());
    }//GEN-LAST:event_cbTipoItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton brSair;
    private javax.swing.JButton btGravar;
    private javax.swing.JComboBox<String> cbAcabamento;
    private javax.swing.JComboBox cbEspessura;
    private javax.swing.JComboBox cbTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbData;
    private javax.swing.JTextArea taObservacoes;
    private javax.swing.JTable tbItensPedido;
    private javax.swing.JTextField tfAlturaBr;
    private javax.swing.JTextField tfAlturaLiq;
    private javax.swing.JTextField tfComprimentoBr;
    private javax.swing.JTextField tfComprimentoLiq;
    private javax.swing.JTextField tfDesconto;
    private javax.swing.JTextField tfLarguraBr;
    private javax.swing.JTextField tfLarguraLiq;
    private javax.swing.JTextField tfMaterial;
    private javax.swing.JTextField tfMetragem;
    private javax.swing.JTextField tfMotorista;
    private javax.swing.JTextField tfNomeCliente;
    private javax.swing.JTextField tfPlaca;
    private javax.swing.JTextField tfQuantidade;
    private javax.swing.JTextField tfTotalItem;
    private javax.swing.JTextField tfTotalPedido;
    private javax.swing.JTextField tfValorDesconto;
    private javax.swing.JTextField tfValorUnitario;
    // End of variables declaration//GEN-END:variables
}
