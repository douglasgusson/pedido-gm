package br.com.pedidogm.table.model;

import br.com.pedidogm.domain.Cliente;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author douglas
 */
public class ClienteTableModel extends AbstractTableModel {

    private static final int COL_CODIGO = 0;
    private static final int COL_NOME = 1;
    private static final int COL_APELIDO = 2;
    private static final int COL_TELEFONE = 3;
    private static final int COL_CELULAR = 4;

    private static final int COLUMN_COUNT = 5;

    private List<Cliente> dados;

    public ClienteTableModel(List<Cliente> dados) {
        this.dados = dados;
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return ClienteTableModel.COLUMN_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Cliente obj = dados.get(rowIndex);

        switch (columnIndex) {
            case COL_CODIGO:
                return String.format("%05d", obj.getId());
            case COL_NOME:
                return obj.getNome();
            case COL_APELIDO:
                return obj.getApelido();
            case COL_TELEFONE:
                return obj.getTelefone();
            case COL_CELULAR:
                return obj.getCelular();
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
                columnName = "Código";
                break;
            case COL_NOME:
                columnName = "Nome";
                break;
            case COL_APELIDO:
                columnName = "Apelido/Nome Fant.";
                break;
            case COL_TELEFONE:
                columnName = "Telefone";
                break;
            case COL_CELULAR:
                columnName = "Celular";
                break;
            default:
                throw new IllegalArgumentException("Coluna inválida!");
        }

        return columnName;
    }

    public Cliente get(int row) {
        return dados.get(row);
    }

    public List<Cliente> getColecao() {
        return dados;
    }

}
