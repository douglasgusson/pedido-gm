package br.com.pedidogm.dao.postgresql;

import br.com.pedidogm.dao.DAOException;
import br.com.pedidogm.dao.DAOFactory;
import br.com.pedidogm.dao.model.AcabamentoDAO;
import br.com.pedidogm.dao.model.ItemPedidoDAO;
import br.com.pedidogm.dao.model.MaterialDAO;
import br.com.pedidogm.dao.model.TipoItemDAO;
import br.com.pedidogm.domain.Acabamento;
import br.com.pedidogm.domain.ItemPedido;
import br.com.pedidogm.domain.Material;
import br.com.pedidogm.domain.Pedido;
import br.com.pedidogm.domain.TipoItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author douglas
 */
public class PgItemPedidoDAO implements ItemPedidoDAO {

    @Override
    public void inserir(ItemPedido itemPedido) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {
            String SQL
                    = "INSERT INTO item_pedido(\n"
                    + "            id_material, id_pedido, id_tipo_item, quantidade, \n"
                    + "            comprimento_br, altura_br, largura_br, comprimento_liq, altura_liq, \n"
                    + "            largura_liq, id_acabamento, metragem, valor_unitario, desconto, \n"
                    + "            valor_total)\n"
                    + "    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setLong(1, itemPedido.getMaterial().getId());
                ps.setLong(2, itemPedido.getPedido().getId());
                ps.setLong(3, itemPedido.getTipoItem().getId());
                ps.setLong(4, itemPedido.getQuantidade());
                ps.setBigDecimal(5, itemPedido.getComprimentoBr());
                ps.setBigDecimal(6, itemPedido.getAlturaBr());
                ps.setBigDecimal(7, itemPedido.getLarguraBr());
                ps.setBigDecimal(8, itemPedido.getComprimentoLiq());
                ps.setBigDecimal(9, itemPedido.getAlturaLiq());
                ps.setBigDecimal(10, itemPedido.getLarguraLiq());
                ps.setLong(11, itemPedido.getAcabamento().getId());
                ps.setBigDecimal(12, itemPedido.getMetragem());
                ps.setBigDecimal(13, itemPedido.getValorUnitario());
                ps.setBigDecimal(14, itemPedido.getDesconto());
                ps.setBigDecimal(15, itemPedido.getValorTotal());

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao inserir item_pedido em PgItemPedidoDAO", ex);
        }
    }

    @Override
    public void alterar(ItemPedido itemPedido) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {
            String SQL
                    = "UPDATE item_pedido\n"
                    + "   SET id_material=?, id_pedido=?, id_tipo_item=?, \n"
                    + "       quantidade=?, comprimento_br=?, altura_br=?, largura_br=?, comprimento_liq=?, \n"
                    + "       altura_liq=?, largura_liq=?, id_acabamento=?, metragem=?, valor_unitario=?, \n"
                    + "       desconto=?, valor_total=?\n"
                    + " WHERE id_item_pedido=? AND id_material=? AND id_pedido=?;";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setLong(1, itemPedido.getMaterial().getId());
                ps.setLong(2, itemPedido.getPedido().getId());
                ps.setLong(3, itemPedido.getTipoItem().getId());

                ps.setLong(4, itemPedido.getQuantidade());
                ps.setBigDecimal(5, itemPedido.getComprimentoBr());
                ps.setBigDecimal(6, itemPedido.getAlturaBr());
                ps.setBigDecimal(7, itemPedido.getLarguraBr());
                ps.setBigDecimal(8, itemPedido.getComprimentoLiq());
                ps.setBigDecimal(9, itemPedido.getAlturaLiq());
                ps.setBigDecimal(10, itemPedido.getLarguraLiq());
                ps.setLong(11, itemPedido.getAcabamento().getId());
                ps.setBigDecimal(12, itemPedido.getMetragem());
                ps.setBigDecimal(13, itemPedido.getValorUnitario());
                ps.setBigDecimal(14, itemPedido.getDesconto());
                ps.setBigDecimal(15, itemPedido.getValorTotal());

                ps.setLong(16, itemPedido.getId());
                ps.setLong(17, itemPedido.getMaterial().getId());
                ps.setLong(18, itemPedido.getPedido().getId());

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao alterar item_pedido em PgItemPedidoDAO", ex);
        }
    }

    @Override
    public void excluir(ItemPedido itemPedido) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {

            String SQL = "DELETE FROM item_pedido\n"
                    + "  WHERE id_item_pedido=? AND id_material=? AND id_pedido=?;";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setLong(1, itemPedido.getId());
                ps.setLong(2, itemPedido.getMaterial().getId());
                ps.setLong(3, itemPedido.getPedido().getId());

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao excluir item_pedido em PgItemPedidoDAO", ex);
        }
    }

    @Override
    public List<ItemPedido> buscarPorPedido(Pedido pedido) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();
        List<ItemPedido> itensPedido = new ArrayList<>();

        try {
            String query
                    = "SELECT id_item_pedido, id_material, id_pedido, id_tipo_item, quantidade, \n"
                    + "       comprimento_br, altura_br, largura_br, comprimento_liq, altura_liq, \n"
                    + "       largura_liq, id_acabamento, metragem, valor_unitario, desconto, \n"
                    + "       valor_total\n"
                    + "  FROM item_pedido \n"
                    + "  WHERE id_pedido = ?;";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, pedido.getId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ItemPedido itemPedido = new ItemPedido();

                itemPedido.setId(rs.getLong(1));

                MaterialDAO materialDAO = DAOFactory.getDefaultDAOFactory().getMaterialDAO();
                Material m = materialDAO.buscar(rs.getLong(2));
                itemPedido.setMaterial(m);

                itemPedido.setPedido(pedido);

                TipoItemDAO tipoItemDAO = DAOFactory.getDefaultDAOFactory().getTipoItemDAO();
                TipoItem tipoItem = tipoItemDAO.buscar(rs.getLong(4));
                itemPedido.setTipoItem(tipoItem);

                itemPedido.setQuantidade(rs.getLong(5));
                itemPedido.setComprimentoBr(rs.getBigDecimal(6));
                itemPedido.setAlturaBr(rs.getBigDecimal(7));
                itemPedido.setLarguraBr(rs.getBigDecimal(8));
                itemPedido.setComprimentoLiq(rs.getBigDecimal(9));
                itemPedido.setAlturaLiq(rs.getBigDecimal(10));
                itemPedido.setLarguraLiq(rs.getBigDecimal(11));
                
                AcabamentoDAO acabamentoDAO = DAOFactory.getDefaultDAOFactory().getAcabamentoDAO();
                Acabamento acabamento = acabamentoDAO.buscar(rs.getLong(12));                
                itemPedido.setAcabamento(acabamento);                
                
                itemPedido.setMetragem(rs.getBigDecimal(13));
                itemPedido.setValorUnitario(rs.getBigDecimal(14));
                itemPedido.setDesconto(rs.getBigDecimal(15));
                itemPedido.setValorTotal(rs.getBigDecimal(16));

                itensPedido.add(itemPedido);

            }

            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar item_pedido em PgItemPedidoDAO", ex);
        }

        return itensPedido;
    }

}
