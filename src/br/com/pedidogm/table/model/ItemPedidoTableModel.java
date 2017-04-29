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
    private static final int COL_DIMESSOES = 2;
    private static final int COL_METRAGEM = 3;
    private static final int COL_VALOR_UNIT = 4;
    private static final int COL_VALOR_TOTAL = 5;

    private static final int COLUMN_COUNT = 6;

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

        switch (columnIndex) {
            case COL_QUANT:
                return obj.getQuantidade();
            case COL_MATERIAL:
                return obj.getTipoItem().getDescricao() 
                        + " - " + obj.getMaterial().getNome()
                        + " (" + obj.getAcabamento() + ")";
            case COL_DIMESSOES:
                return obj.getComprimentoLiq().setScale(3)
                        + " x " + obj.getAlturaLiq().setScale(3)
                        + " x " + obj.getLarguraLiq().setScale(3);
            case COL_METRAGEM:
                return obj.getMetragem().setScale(3);
            case COL_VALOR_UNIT:
                return obj.getValorUnitario().setScale(2);
            case COL_VALOR_TOTAL:
                return obj.getValorTotal().setScale(2);
            default:
                break;
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
            case COL_DIMESSOES:
                columnName = "Med. Líquidas";
                break;
            case COL_MATERIAL:
                columnName = "Descrição";
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
