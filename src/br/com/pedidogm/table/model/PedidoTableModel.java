package br.com.pedidogm.table.model;

import br.com.pedidogm.dao.DAOFactory;
import br.com.pedidogm.dao.model.PedidoDAO;
import br.com.pedidogm.domain.Pedido;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author douglas
 */
public class PedidoTableModel extends AbstractTableModel {

    private static final int COL_CODIGO = 0;
    private static final int COL_DATA = 1;
    private static final int COL_CLIENTE = 2;
    private static final int COL_USUARIO = 3;
    private static final int COL_VALOR = 4;

    private static final int COLUMN_COUNT = 5;

    private List<Pedido> dados;

    public PedidoTableModel(List<Pedido> dados) {
        this.dados = dados;
    }

    public PedidoTableModel() {
        dados = new ArrayList<>();
        carregarDoBD();
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

        if (columnIndex == COL_CODIGO) {
            return String.format("%05d", obj.getId());
        } else if (columnIndex == COL_DATA) {
            return obj.getDataCarregamento().format(
                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else if (columnIndex == COL_CLIENTE) {
            return obj.getCliente().getNome();
        } else if (columnIndex == COL_USUARIO) {
            return obj.getUsuario().getNomeCompleto();
        } else if (columnIndex == COL_VALOR) {
            return obj.getValor().toString();
        }
        return null;
    }

    @Override
    public String getColumnName(int colunm) {
        String columnName = "";
        switch (colunm) {
            case COL_CODIGO:
                columnName = "Código";
                break;
            case COL_DATA:
                columnName = "Data";
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

    public void atualizarDoBD() {
        dados.clear();
        carregarDoBD();
    }

    private void carregarDoBD() {
        PedidoDAO cd = DAOFactory.getDefaultDAOFactory().getPedidoDAO();
        dados = cd.listarTodos();
    }

    public List<Pedido> getColecao() {
        return dados;
    }

}
