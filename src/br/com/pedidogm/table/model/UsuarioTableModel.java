package br.com.pedidogm.table.model;

import br.com.pedidogm.dao.DAOFactory;
import br.com.pedidogm.dao.model.UsuarioDAO;
import br.com.pedidogm.domain.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Douglas Gusson
 */
public class UsuarioTableModel extends AbstractTableModel {

    private static final int COL_CODIGO = 0;
    private static final int COL_NOME = 1;
    private static final int COL_NOME_COMPLETO = 2;

    private List<Usuario> dados;

    public UsuarioTableModel() {
        dados = new ArrayList<>();
        carregarDoBD();
    }

    public List<Usuario> getColecao() {
        return dados;
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario obj = dados.get(rowIndex);
        if (columnIndex == COL_CODIGO) {
            return String.format("%05d", obj.getId());
        } else if (columnIndex == COL_NOME) {
            return obj.getNomeUsuario();
        } else if (columnIndex == COL_NOME_COMPLETO) {
            return obj.getNomeCompleto();
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
                coluna = "Nome de Usuário";
                break;
            case COL_NOME_COMPLETO:
                coluna = "Nome Completo";
                break;
            default:
                throw new IllegalArgumentException("Coluna inválida!");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == COL_CODIGO) {
            return Long.class;
        } else if (columnIndex == COL_NOME) {
            return String.class;
        } else if (columnIndex == COL_NOME_COMPLETO) {
            return String.class;
        }
        return null;
    }

    public Usuario get(int row) {
        return dados.get(row);
    }

    public void atualizarDoBD() {
        dados.clear();
        carregarDoBD();
    }

    private void carregarDoBD() {
        UsuarioDAO usuarioDAO = DAOFactory.getDefaultDAOFactory().getUsuarioDAO();
        dados = usuarioDAO.listarTodos();
    }

}
