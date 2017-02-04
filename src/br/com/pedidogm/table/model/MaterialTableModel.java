package br.com.pedidogm.table.model;

import br.com.pedidogm.dao.DAOFactory;
import br.com.pedidogm.dao.model.MaterialDAO;
import br.com.pedidogm.domain.Material;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Douglas Gusson
 */
public class MaterialTableModel extends AbstractTableModel {

    private static final int COL_CODIGO = 0;
    private static final int COL_NOME = 1;

    private static final int COLUMN_COUNT = 2;

    private List<Material> dados;

    public MaterialTableModel() {
        dados = new ArrayList<>();
        carregarDoBD();
    }

    public List<Material> getColecao() {
        return dados;
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return MaterialTableModel.COLUMN_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Material obj = dados.get(rowIndex);
        if (columnIndex == COL_CODIGO) {
            return String.format("%05d", obj.getId());
        } else if (columnIndex == COL_NOME) {
            return obj.getNome();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_CODIGO:
                coluna = "Código";
                break;
            case COL_NOME:
                coluna = "Nome";
                break;
            default:
                throw new IllegalArgumentException("Coluna inválida!");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == COL_CODIGO) {
            return int.class;
        } else if (columnIndex == COL_NOME) {
            return String.class;
        }
        return null;
    }

    public Material get(int row) {
        return dados.get(row);
    }

    public void atualizarDoBD() {
        dados.clear();
        carregarDoBD();
    }

    private void carregarDoBD() {
        MaterialDAO md = DAOFactory.getDefaultDAOFactory().getMaterialDAO();
        dados = md.listarTodos();
    }

}
