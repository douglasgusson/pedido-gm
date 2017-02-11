package br.com.pedidogm.view;

import br.com.pedidogm.dao.DAOFactory;
import br.com.pedidogm.dao.model.AcabamentoDAO;
import br.com.pedidogm.dao.model.ClienteDAO;
import br.com.pedidogm.dao.model.ItemPedidoDAO;
import br.com.pedidogm.dao.model.MaterialDAO;
import br.com.pedidogm.dao.model.PedidoDAO;
import br.com.pedidogm.dao.model.TipoItemDAO;
import br.com.pedidogm.domain.Acabamento;
import br.com.pedidogm.domain.Cliente;
import br.com.pedidogm.domain.ItemPedido;
import br.com.pedidogm.domain.Material;
import br.com.pedidogm.domain.Pedido;
import br.com.pedidogm.domain.Sessao;
import br.com.pedidogm.domain.TipoItem;
import br.com.pedidogm.table.cellrenderer.ItemPedidoCellRenderer;
import br.com.pedidogm.table.model.ItemPedidoTableModel;
import br.com.pedidogm.util.MascaraNumerica;
import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
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
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
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
    private Pedido pedido;
    private List<Pedido> listaPedidos = new ArrayList<>();
    private List<ItemPedido> itensPedido = new ArrayList<>();
    private List<ItemPedido> itensPedidoExclusao = new ArrayList<>();
    private BigDecimal totalPedido = new BigDecimal("0");
    private BigDecimal metragem = new BigDecimal("0.00");
    private Long idItem;

    private Vector<TipoItem> tipos;
    private DefaultComboBoxModel<TipoItem> modeloComboBoxTipoItem;

    private Vector<Acabamento> acabamentos;
    private DefaultComboBoxModel<Acabamento> modeloComboBoxAcabamento;

    private static final int OPCAO_INSERIR = 0;
    private static final int OPCAO_ALTERAR = 1;

    private int indexRegistro;
    private int opcao;

    private final int METRO_QUADRADO = 0;
    private final int METRO_CUBICO = 1;
    private final int METRO_LINEAR = 2;

    private final String espessuras[] = {"1,5 cm", "2,0 cm", "3,0 cm", "4,0 cm"};

    Border borderRed = BorderFactory.createLineBorder(Color.red);
    Border borderDefault = BorderFactory.createLineBorder(Color.gray);

    public static FrmRegistroPedido getInstancia() {
        return INSTANCIA;
    }

    public FrmRegistroPedido(Window parent) {
        super(parent, DEFAULT_MODALITY_TYPE);
        this.idItem = (long) -1;
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
            this.pedido = listaPedidos.get(indexRegistro);
            preencherCampos();
        }
    }

    public void atualizarTabela() {
        tbItensPedido.setModel(new ItemPedidoTableModel(this.itensPedido));
        tbItensPedido.setDefaultRenderer(Object.class, new ItemPedidoCellRenderer());
        ((AbstractTableModel) tbItensPedido.getModel()).fireTableDataChanged();
    }

    private void preencherCampos() {

        this.totalPedido = pedido.getValor();

        this.tfNomeCliente.setText(pedido.getCliente().getNome());
        this.lbData.setText(pedido.getDataCarregamento().format(DateTimeFormatter.ofPattern("EEE, dd MMM yyyy")));
        this.tfTotalPedido.setText(pedido.getValor().toString().replace(".", ","));
        this.tfPlaca.setText(pedido.getPlaca());
        this.tfMotorista.setText(pedido.getMotorista());
        this.taObservacoes.setText(pedido.getObservacoes());
        this.itensPedido = pedido.getItensPedido();
        atualizarTabela();
    }

    private void initialize() {

        itensPedido.clear();
        atualizarTabela();
        menuContextoTabelaItens();

        tfNomeCliente.setText("");
        tfNomeCliente.requestFocus();
        tfPlaca.setText("");
        tfMotorista.setText("");
        taObservacoes.setText("");
        tfTotalPedido.setText("0,00");
        limparCamposItem();

        lbData.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, dd MMM yyyy")));

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

        for (String espessura : espessuras) {
            this.cbEspessura.addItem(espessura);
        }

        mudarEstadoFrm(METRO_QUADRADO);

        this.cbTipo.setSelectedItem("CHAPA");
        this.cbAcabamento.setSelectedItem("POLIDO");
        this.cbEspessura.setSelectedItem("2,0 cm");

        TipoItemDAO tipoItemDAO = DAOFactory.getDefaultDAOFactory().getTipoItemDAO();
        tipos = new Vector<>(tipoItemDAO.listarTodos());

        AcabamentoDAO acabamentoDAO = DAOFactory.getDefaultDAOFactory().getAcabamentoDAO();
        acabamentos = new Vector<>(acabamentoDAO.listarTodos());

        FocusAdapter calcularMetragem = new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent evt) {
                calcularMetragem();
            }
        };

        tfLarguraBr.addFocusListener(calcularMetragem);
        tfComprimentoLiq.addFocusListener(calcularMetragem);
        tfAlturaLiq.addFocusListener(calcularMetragem);
        tfLarguraLiq.addFocusListener(calcularMetragem);
        tfQuantidade.addFocusListener(calcularMetragem);

        FocusAdapter setBorderDefault = new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent evt) {
                JTextField field = (JTextField) evt.getSource();
                if (!field.getText().trim().equals("")) {
                    field.setBorder(borderDefault);
                }
            }
        };

        tfNomeCliente.addFocusListener(setBorderDefault);
        tfQuantidade.addFocusListener(setBorderDefault);
        tfMaterial.addFocusListener(setBorderDefault);
        tfComprimentoBr.addFocusListener(setBorderDefault);
        tfAlturaBr.addFocusListener(setBorderDefault);
        tfLarguraBr.addFocusListener(setBorderDefault);
        tfComprimentoLiq.addFocusListener(setBorderDefault);
        tfAlturaLiq.addFocusListener(setBorderDefault);
        tfLarguraLiq.addFocusListener(setBorderDefault);
        tfMetragem.addFocusListener(setBorderDefault);
        tfValorUnitario.addFocusListener(setBorderDefault);
        tfDesconto.addFocusListener(setBorderDefault);
        tfTotalItem.addFocusListener(setBorderDefault);

        modeloComboBoxTipoItem = new DefaultComboBoxModel<>(tipos);
        cbTipo.setModel(modeloComboBoxTipoItem);

        modeloComboBoxAcabamento = new DefaultComboBoxModel<>(acabamentos);
        cbAcabamento.setModel(modeloComboBoxAcabamento);

    }

    private void limparCamposItem() {
        this.idItem = (long) -1;
        tfMaterial.setText("");
        tfMaterial.setBorder(borderDefault);
        tfQuantidade.setText("");
        tfQuantidade.setBorder(borderDefault);
        tfComprimentoBr.setText("");
        tfComprimentoBr.setBorder(borderDefault);
        tfComprimentoLiq.setText("");
        tfComprimentoLiq.setBorder(borderDefault);
        tfAlturaBr.setText("");
        tfAlturaBr.setBorder(borderDefault);
        tfAlturaLiq.setText("");
        tfAlturaLiq.setBorder(borderDefault);
        tfLarguraBr.setText("");
        tfLarguraBr.setBorder(borderDefault);
        tfLarguraLiq.setText("");
        tfLarguraLiq.setBorder(borderDefault);
        tfMetragem.setText("");
        tfMetragem.setBorder(borderDefault);
        tfValorUnitario.setText("");
        tfValorUnitario.setBorder(borderDefault);
        tfDesconto.setText("0,00");
        tfDesconto.setBorder(borderDefault);
        tfValorDesconto.setText("0,00");
        tfTotalItem.setText("0,00");
        tfTotalItem.setBorder(borderDefault);
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
                this.cbEspessura.setEnabled(false);
                break;
            default:
                this.tfLarguraBr.setEnabled(false);
                this.tfLarguraLiq.setEnabled(false);
                this.cbEspessura.setEnabled(true);
                break;

        }
    }

    private void calcularMetragem() {

        String quant = "0";

        BigDecimal compLiq = new BigDecimal("0.00");
        BigDecimal altLiq = new BigDecimal("0.00");
        BigDecimal largLiq = new BigDecimal("0.00");

        TipoItem ti = (TipoItem) this.cbTipo.getItemAt(this.cbTipo.getSelectedIndex());
        int ref = ti.getReferenciaCalculo();

        try {
            quant = this.tfQuantidade.getText();
            compLiq = new BigDecimal(this.tfComprimentoLiq.getText().replace(",", "."));
            altLiq = new BigDecimal(this.tfAlturaLiq.getText().replace(",", "."));
            largLiq = new BigDecimal(this.tfLarguraLiq.getText().replace(",", "."));
        } catch (NumberFormatException e) {
        }

        switch (ref) {
            case METRO_QUADRADO:
                try {
                    metragem = compLiq.multiply(altLiq).multiply(new BigDecimal(quant)).setScale(3, RoundingMode.HALF_EVEN);
                } catch (NumberFormatException e) {
                }
                break;
            case METRO_CUBICO:
                try {
                    metragem = compLiq.multiply(altLiq).multiply(largLiq).multiply(new BigDecimal(quant)).setScale(3, RoundingMode.HALF_EVEN);
                } catch (NumberFormatException e) {
                }
                break;

        }

        this.tfMetragem.setText(metragem.toString().replace(".", ","));

        calcularValores();
    }

    private void calcularValores() {

        BigDecimal valorUnit = new BigDecimal("0.00");
        BigDecimal desconto = new BigDecimal("0.00");

        try {
            metragem = new BigDecimal(this.tfMetragem.getText().replace(",", "."));
            valorUnit = new BigDecimal(this.tfValorUnitario.getText().replace(",", "."));
            desconto = new BigDecimal(this.tfDesconto.getText().replace(",", "."));
        } catch (NumberFormatException e) {
        }

        BigDecimal totalItem = metragem.multiply(valorUnit).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal valorDesconto = totalItem.multiply((desconto.divide(new BigDecimal("100.0")))).setScale(2, RoundingMode.HALF_EVEN);
        totalItem = totalItem.subtract(valorDesconto).setScale(2, RoundingMode.HALF_EVEN);

        this.tfValorDesconto.setText(valorDesconto.toString().replace(".", ","));
        this.tfTotalItem.setText(totalItem.toString().replace(".", ","));

    }

    private void menuContextoTabelaItens() {

        JPopupMenu popUpMenu = new JPopupMenu();

        JMenuItem itemAlterar = new JMenuItem("Alterar");

        itemAlterar.addActionListener(
                (ActionEvent e) -> {
                    int index = tbItensPedido.getSelectedRow();
                    if (index != -1) {
                        ItemPedidoTableModel obj = new ItemPedidoTableModel(itensPedido);
                        ItemPedido item = obj.get(index);
                        this.totalPedido = this.totalPedido.subtract(item.getValorTotal());
                        this.tfTotalPedido.setText(totalPedido.toString().replace(".", ","));
                        idItem = item.getId();
                        preencherItem(item);
                        tfQuantidade.requestFocus();
                        itensPedido.remove(item);
                        tbItensPedido.setModel(new ItemPedidoTableModel(itensPedido));
                    }
                }
        );

        JMenuItem itemExcluir = new JMenuItem("Excluir");

        itemExcluir.addActionListener(
                (ActionEvent e) -> {
                    int index = tbItensPedido.getSelectedRow();
                    if (index != -1) {
                        ItemPedidoTableModel obj = new ItemPedidoTableModel(itensPedido);
                        ItemPedido item = obj.get(index);
                        this.totalPedido = this.totalPedido.subtract(item.getValorTotal());
                        this.tfTotalPedido.setText(totalPedido.toString().replace(".", ","));
                        this.itensPedidoExclusao.add(item);
                        itensPedido.remove(item);
                        tbItensPedido.setModel(new ItemPedidoTableModel(itensPedido));
                    }
                }
        );

        popUpMenu.add(itemAlterar);
        popUpMenu.addSeparator();
        popUpMenu.add(itemExcluir);

        tbItensPedido.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            popUpMenu.show(tbItensPedido, e.getX(), e.getY());
                        }
                    }
                }
        );

        tbItensPedido.addKeyListener(
                new java.awt.event.KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_CONTEXT_MENU) {
                            if (!itensPedido.isEmpty()) {
                                popUpMenu.show(tbItensPedido, 200, 100);
                            }
                        }
                    }
                });
    }

    private void preencherItem(ItemPedido ip) {

        tfQuantidade.setText(ip.getQuantidade().toString());
        setMaterial(ip.getMaterial());
        cbTipo.setSelectedItem(ip.getTipoItem());

        tfComprimentoBr.setText(ip.getComprimentoBr().toString().replace(".", ","));
        tfAlturaBr.setText(ip.getAlturaBr().toString().replace(".", ","));
        tfLarguraBr.setText(ip.getLarguraBr().toString().replace(".", ","));

        tfComprimentoLiq.setText(ip.getComprimentoLiq().toString().replace(".", ","));
        tfAlturaLiq.setText(ip.getAlturaLiq().toString().replace(".", ","));
        tfLarguraLiq.setText(ip.getLarguraLiq().toString().replace(".", ","));

        cbAcabamento.setSelectedItem(ip.getAcabamento());

        tfMetragem.setText(ip.getMetragem().toString().replace(".", ","));
        tfValorUnitario.setText(ip.getValorUnitario().toString().replace(".", ","));
        tfDesconto.setText(ip.getDesconto().toString().replace(".", ","));
        tfValorDesconto.setText("()()()()");
        tfTotalItem.setText(ip.getValorTotal().toString().replace(".", ","));

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
        btAdicionar = new javax.swing.JButton();
        cbTipo = new javax.swing.JComboBox();
        cbAcabamento = new javax.swing.JComboBox();
        lbData = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taObservacoes = new javax.swing.JTextArea();
        tfTotalPedido = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuCadastros = new javax.swing.JMenu();
        itemClientes = new javax.swing.JMenuItem();
        itemMateriais = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de Pedido");

        btGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/gravar_16x16.png"))); // NOI18N
        btGravar.setMnemonic('G');
        btGravar.setText("Gravar");
        btGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGravarActionPerformed(evt);
            }
        });

        brSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/sair_16x16.png"))); // NOI18N
        brSair.setMnemonic('S');
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

        jLabel18.setText("Espessura:");

        cbEspessura.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbEspessuraItemStateChanged(evt);
            }
        });
        cbEspessura.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbEspessuraFocusLost(evt);
            }
        });

        tfValorDesconto.setEditable(false);
        tfValorDesconto.setBackground(java.awt.Color.white);
        tfValorDesconto.setForeground(java.awt.Color.black);
        tfValorDesconto.setText("0,00");
        tfValorDesconto.setDisabledTextColor(java.awt.Color.black);
        tfValorDesconto.setEnabled(false);

        jLabel19.setText("Desc. (R$):");

        btAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/add_16x16.png"))); // NOI18N
        btAdicionar.setMnemonic('A');
        btAdicionar.setText("Adicionar");
        btAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionarActionPerformed(evt);
            }
        });

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTipoItemStateChanged(evt);
            }
        });

        cbAcabamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cbAcabamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
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
                    .addComponent(btAdicionar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbData.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbData.setText("data...");

        jLabel20.setText("Observações:");

        taObservacoes.setColumns(20);
        taObservacoes.setRows(5);
        jScrollPane2.setViewportView(taObservacoes);

        menuCadastros.setText("Cadastros");

        itemClientes.setText("Clientes");
        itemClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemClientesActionPerformed(evt);
            }
        });
        menuCadastros.add(itemClientes);

        itemMateriais.setText("Materiais");
        itemMateriais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMateriaisActionPerformed(evt);
            }
        });
        menuCadastros.add(itemMateriais);

        jMenuBar1.add(menuCadastros);

        setJMenuBar(jMenuBar1);

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
                                .addGap(6, 6, 6))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(26, 26, 26))
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

        if (tfNomeCliente.getText().trim().equals("")) {
            tfNomeCliente.setBorder(borderRed);
            tfNomeCliente.requestFocus();
        } else if (itensPedido.size() <= 0) {
            tfQuantidade.setBorder(borderRed);
            tfQuantidade.requestFocus();
        } else {

            PedidoDAO pedidoDAO = DAOFactory.getDefaultDAOFactory().getPedidoDAO();
            ItemPedidoDAO itemPedidoDAO = DAOFactory.getDefaultDAOFactory().getItemPedidoDAO();

            switch (opcao) {

                case OPCAO_INSERIR:

                    Pedido p = new Pedido();

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

                    Pedido ultimoPedido = pedidoDAO.buscarUltimoPedido();

                    for (int i = 0; i < itensPedido.size(); i++) {
                        itensPedido.get(i).setPedido(ultimoPedido);
                        itemPedidoDAO.inserir(itensPedido.get(i));
                    }

                    break;

                case OPCAO_ALTERAR:

                    pedido.setCliente(cliente);
                    pedido.setValor(new BigDecimal(this.tfTotalPedido.getText().replace(",", ".")));
                    pedido.setPlaca(this.tfPlaca.getText());
                    pedido.setMotorista(this.tfMotorista.getText());
                    pedido.setObservacoes(this.taObservacoes.getText());
                    pedido.setDataCarregamento(LocalDate.now());
                    pedido.setCriacao(LocalDateTime.now());
                    pedido.setAlteracao(LocalDateTime.now());

                    pedido.setUsuario(Sessao.getUsuario());

                    pedidoDAO.alterar(pedido);

                    for (int i = 0; i < itensPedido.size(); i++) {

                        itensPedido.get(i).setPedido(pedido);

                        if (itensPedido.get(i).getId().equals((long) -1)) {
                            itemPedidoDAO.inserir(itensPedido.get(i));
                        } else {
                            itemPedidoDAO.alterar(itensPedido.get(i));
                        }
                    }

                    for (int i = 0; i < itensPedidoExclusao.size(); i++) {
                        itensPedidoExclusao.get(i).setPedido(pedido);
                        itemPedidoDAO.excluir(itensPedidoExclusao.get(i));
                    }

                    break;
            }

            FrmPedidos frmPedidos = FrmPedidos.getInstancia();
            frmPedidos.atualizarTabela();

            if (OPCAO_ALTERAR == opcao) {
                this.dispose();
            } else {
                initialize();
            }
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

            if (materias.isEmpty()) {
                this.tfMaterial.setText("");
            } else if (materias.size() == 1) {
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

    private void btAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarActionPerformed

        if ((this.tfQuantidade.getText().trim()).equals("")) {
            this.tfQuantidade.setBorder(borderRed);
            this.tfQuantidade.requestFocus();
        } else if ((this.tfMaterial.getText().trim()).equals("")) {
            this.tfMaterial.setBorder(borderRed);
            this.tfMaterial.requestFocus();
        } else if ((this.tfComprimentoBr.getText().trim()).equals("")) {
            this.tfComprimentoBr.setBorder(borderRed);
            this.tfComprimentoBr.requestFocus();
        } else if ((this.tfAlturaBr.getText().trim()).equals("")) {
            this.tfAlturaBr.setBorder(borderRed);
            this.tfAlturaBr.requestFocus();
        } else if ((this.tfLarguraBr.getText().trim()).equals("")) {
            this.tfLarguraBr.setBorder(borderRed);
            this.tfLarguraBr.requestFocus();
        } else if ((this.tfComprimentoLiq.getText().trim()).equals("")) {
            this.tfComprimentoLiq.setBorder(borderRed);
            this.tfComprimentoLiq.requestFocus();
        } else if ((this.tfAlturaLiq.getText().trim()).equals("")) {
            this.tfAlturaLiq.setBorder(borderRed);
            this.tfAlturaLiq.requestFocus();
        } else if ((this.tfLarguraLiq.getText().trim()).equals("")) {
            this.tfLarguraLiq.setBorder(borderRed);
            this.tfLarguraLiq.requestFocus();
        } else if ((this.tfMetragem.getText().trim()).equals("")) {
            this.tfMetragem.setBorder(borderRed);
            this.tfMetragem.requestFocus();
        } else if ((this.tfValorUnitario.getText().trim()).equals("")) {
            this.tfValorUnitario.setBorder(borderRed);
            this.tfValorUnitario.requestFocus();
        } else if ((this.tfDesconto.getText().trim()).equals("")) {
            this.tfDesconto.setBorder(borderRed);
            this.tfDesconto.requestFocus();
        } else if ((this.tfTotalItem.getText().trim()).equals("")) {
            this.tfTotalItem.setBorder(borderRed);
            this.tfTotalItem.requestFocus();
        } else {

            try {

                ItemPedido itemPedido = new ItemPedido();

                itemPedido.setId(idItem);

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

                Acabamento acabamento = (Acabamento) this.cbAcabamento.getItemAt(this.cbAcabamento.getSelectedIndex());
                itemPedido.setAcabamento(acabamento);

                itemPedido.setMetragem(new BigDecimal(this.tfMetragem.getText().replace(",", ".")));
                itemPedido.setValorUnitario(new BigDecimal(this.tfValorUnitario.getText().replace(",", ".")));
                itemPedido.setDesconto(new BigDecimal(this.tfDesconto.getText().replace(",", ".")));
                itemPedido.setValorTotal(new BigDecimal(this.tfTotalItem.getText().replace(",", ".")));

                this.totalPedido = this.totalPedido.add(itemPedido.getValorTotal());
                this.tfTotalPedido.setText(totalPedido.toString().replace(".", ","));

                itensPedido.add(itemPedido);
                atualizarTabela();
                limparCamposItem();
                metragem = new BigDecimal("0.00");
                tfQuantidade.requestFocus();

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Formato inválido para número. \nERRO:" + e);
            }

        }

    }//GEN-LAST:event_btAdicionarActionPerformed

    private void tfComprimentoBrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfComprimentoBrFocusLost

        BigDecimal compBr = new BigDecimal("0.00");

        try {
            compBr = new BigDecimal(this.tfComprimentoBr.getText().replace(",", "."));
        } catch (NumberFormatException e) {
        }

        BigDecimal compLiq = compBr.subtract(new BigDecimal("0.05"));
        this.tfComprimentoLiq.setText(compLiq.toString().replace(".", ","));

        calcularMetragem();

    }//GEN-LAST:event_tfComprimentoBrFocusLost

    private void tfAlturaBrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfAlturaBrFocusLost

        BigDecimal altBr = new BigDecimal("0.00");

        try {
            altBr = new BigDecimal(this.tfAlturaBr.getText().replace(",", "."));
        } catch (NumberFormatException e) {
        }

        BigDecimal altLiq = altBr.subtract(new BigDecimal("0.05"));
        this.tfAlturaLiq.setText(altLiq.toString().replace(".", ","));

        calcularMetragem();

    }//GEN-LAST:event_tfAlturaBrFocusLost

    private void tfLarguraBrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfLarguraBrFocusLost
        BigDecimal largBr = new BigDecimal("0.00");

        try {
            largBr = new BigDecimal(this.tfLarguraBr.getText().replace(",", "."));
        } catch (NumberFormatException e) {
        }

        BigDecimal altLiq = largBr.subtract(new BigDecimal("0.05"));
        this.tfLarguraLiq.setText(altLiq.toString().replace(".", ","));

        calcularMetragem();
    }//GEN-LAST:event_tfLarguraBrFocusLost

    private void tfComprimentoLiqFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfComprimentoLiqFocusLost
        calcularMetragem();
    }//GEN-LAST:event_tfComprimentoLiqFocusLost

    private void tfAlturaLiqFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfAlturaLiqFocusLost
        calcularMetragem();
    }//GEN-LAST:event_tfAlturaLiqFocusLost

    private void tfLarguraLiqFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfLarguraLiqFocusLost
        calcularMetragem();
    }//GEN-LAST:event_tfLarguraLiqFocusLost

    private void tfMetragemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfMetragemFocusLost
        calcularValores();
    }//GEN-LAST:event_tfMetragemFocusLost

    private void tfValorUnitarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfValorUnitarioFocusLost
        calcularValores();
    }//GEN-LAST:event_tfValorUnitarioFocusLost

    private void tfDescontoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfDescontoFocusLost
        if ((tfDesconto.getText().trim()).equals("")) {
            tfDesconto.setText("0,00");
        } else if (Float.parseFloat(tfDesconto.getText().replace(",", ".")) < 0
                || Float.parseFloat(tfDesconto.getText().replace(",", ".")) > 100) {
            tfDesconto.setBorder(borderRed);
            JOptionPane.showMessageDialog(null,
                    "O desconto deve ser um valor entre 0,00 e 100,00%.");
            tfDesconto.requestFocus();
        }
        calcularValores();
    }//GEN-LAST:event_tfDescontoFocusLost

    private void tfQuantidadeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfQuantidadeFocusLost
        calcularMetragem();
    }//GEN-LAST:event_tfQuantidadeFocusLost

    private void cbTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTipoItemStateChanged
        TipoItem ti = (TipoItem) this.cbTipo.getItemAt(this.cbTipo.getSelectedIndex());
        mudarEstadoFrm(ti.getReferenciaCalculo());
    }//GEN-LAST:event_cbTipoItemStateChanged

    private void cbEspessuraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbEspessuraItemStateChanged
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
    }//GEN-LAST:event_cbEspessuraItemStateChanged

    private void itemClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemClientesActionPerformed
        FrmClientes clientes = new FrmClientes(this);
        clientes.setVisible(true);
    }//GEN-LAST:event_itemClientesActionPerformed

    private void itemMateriaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMateriaisActionPerformed
        FrmMateriais materiais = new FrmMateriais(this);
        materiais.setVisible(true);
    }//GEN-LAST:event_itemMateriaisActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton brSair;
    private javax.swing.JButton btAdicionar;
    private javax.swing.JButton btGravar;
    private javax.swing.JComboBox cbAcabamento;
    private javax.swing.JComboBox cbEspessura;
    private javax.swing.JComboBox cbTipo;
    private javax.swing.JMenuItem itemClientes;
    private javax.swing.JMenuItem itemMateriais;
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
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbData;
    private javax.swing.JMenu menuCadastros;
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
