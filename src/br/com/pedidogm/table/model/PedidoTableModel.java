package br.com.pedidogm.table.model;

import br.com.pedidogm.domain.Pedido;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author douglas
 */
public class PedidoTableModel extends AbstractTableModel {

    private static final int COL_CODIGO = 0;
    private static final int COL_DATA_EMISSAO = 1;
    private static final int COL_DATA_CARREGAMENTO = 2;
    private static final int COL_CLIENTE = 3;
    private static final int COL_USUARIO = 4;
    private static final int COL_VALOR = 5;

    private static final int COLUMN_COUNT = 6;

    private List<Pedido> dados;

    public PedidoTableModel(List<Pedido> dados) {
        this.dados = dados;
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return PedidoTableModel.COLUMN_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Pedido obj = dados.get(rowIndex);

        switch (columnIndex) {
            case COL_CODIGO:
                return String.format("%05d", obj.getId());
            case COL_DATA_EMISSAO:
                return obj.getCriacao().format(
                        DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm"));
            case COL_DATA_CARREGAMENTO:
                return obj.getDataCarregamento().format(
                        DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            case COL_CLIENTE:
                return obj.getCliente().getNome();
            case COL_USUARIO:
                return obj.getUsuario().getNomeCompleto();
            case COL_VALOR:
                return obj.getValor().toString();
            default:
                break;
        }
        return null;
    }

    @Override
    public String getColumnName(int colunm) {
        String columnName = "";
        switch (colunm) {
            case COL_CODIGO:
                columnName = "Número";
                break;
            case COL_DATA_EMISSAO:
                columnName = "Emissão";
                break;
            case COL_DATA_CARREGAMENTO:
                columnName = "Carregamento";
                break;
            case COL_CLIENTE:
                columnName = "Cliente";
                break;
            case COL_USUARIO:
                columnName = "Emitido por";
                break;
            case COL_VALOR:
                columnName = "Valor";
                break;
            default:
                throw new IllegalArgumentException("Coluna inválida!");
        }

        return columnName;
    }

    public Pedido get(int row) {
        return dados.get(row);
    }

    public List<Pedido> getColecao() {
        return dados;
    }

}
