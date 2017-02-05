package br.com.pedidogm.table.model;

import br.com.pedidogm.domain.ItemPedido;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author douglas
 */
public class ItemPedidoTableModel extends AbstractTableModel {

    private static final int COL_QUANT = 0;
    private static final int COL_MATERIAL = 1;
    private static final int COL_METRAGEM = 2;
    private static final int COL_VALOR_UNIT = 3;
    private static final int COL_VALOR_TOTAL = 4;

    private static final int COLUMN_COUNT = 5;

    private List<ItemPedido> dados;

    public ItemPedidoTableModel(List<ItemPedido> dados) {
        this.dados = dados;
    }
    
    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return ItemPedidoTableModel.COLUMN_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        ItemPedido obj = dados.get(rowIndex);

        if (columnIndex == COL_QUANT) {
            return obj.getQuantidade();
        } else if (columnIndex == COL_MATERIAL) {
            return obj.getMaterial().getNome();
        } else if (columnIndex == COL_METRAGEM) {
            return obj.getMetragem();
        } else if (columnIndex == COL_VALOR_UNIT) {
            return obj.getValorUnitario();
        } else if (columnIndex == COL_VALOR_TOTAL) {
            return obj.getValorTotal();
        }
        return null;
    }

    @Override
    public String getColumnName(int colunm) {
        String columnName = "";
        switch (colunm) {
            case COL_QUANT:
                columnName = "Quant.";
                break;
            case COL_MATERIAL:
                columnName = "Material";
                break;
            case COL_METRAGEM:
                columnName = "Metragem";
                break;
            case COL_VALOR_UNIT:
                columnName = "Valor Unit.";
                break;
            case COL_VALOR_TOTAL: 
                columnName = "Valor Total";
                break;
            default:
                throw new IllegalArgumentException("Coluna inválida!");
        }

        return columnName;
    }

    public ItemPedido get(int row) {
        return dados.get(row);
    }

    public List<ItemPedido> getColecao() {
        return dados;
    }

}
