package br.com.pedidogm.dao.postgresql;

import br.com.pedidogm.dao.DAOException;
import br.com.pedidogm.dao.DAOFactory;
import br.com.pedidogm.dao.model.ItemPedidoDAO;
import br.com.pedidogm.dao.model.MaterialDAO;
import br.com.pedidogm.domain.ItemPedido;
import br.com.pedidogm.domain.Material;
import br.com.pedidogm.domain.Pedido;
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
                    + "            id_material, id_pedido, quantidade, tipo, comprimento_br, altura_br, \n"
                    + "            largura_br, comprimento_liq, altura_liq, largura_liq, acabamento, \n"
                    + "            metragem, valor_unitario, desconto, valor_total)\n"
                    + "    VALUES (?, ?, ?, ?, ?, ?, \n"
                    + "            ?, ?, ?, ?, ?, \n"
                    + "            ?, ?, ?, ?);";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setLong(1, itemPedido.getMaterial().getId());
                ps.setLong(2, itemPedido.getPedido().getId());
                ps.setLong(3, itemPedido.getQuantidade());
                ps.setString(4, itemPedido.getTipo());
                ps.setFloat(5, itemPedido.getComprimentoBr());
                ps.setFloat(6, itemPedido.getAlturaBr());
                ps.setFloat(7, itemPedido.getLarguraBr());
                ps.setFloat(8, itemPedido.getComprimentoLiq());
                ps.setFloat(9, itemPedido.getAlturaLiq());
                ps.setFloat(10, itemPedido.getLarguraLiq());
                ps.setString(11, itemPedido.getAcabamento());
                ps.setFloat(12, itemPedido.getMetragem());
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
                    + "   SET quantidade=?, tipo=?, comprimento_br=?, \n"
                    + "       altura_br=?, largura_br=?, comprimento_liq=?, altura_liq=?, largura_liq=?, \n"
                    + "       acabamento=?, metragem=?, valor_unitario=?, desconto=?, valor_total=?\n"
                    + " WHERE id_item_pedido=? AND id_material=?, id_pedido=?;";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setLong(1, itemPedido.getQuantidade());
                ps.setString(2, itemPedido.getTipo());
                ps.setFloat(3, itemPedido.getComprimentoBr());
                ps.setFloat(4, itemPedido.getAlturaBr());
                ps.setFloat(5, itemPedido.getLarguraBr());
                ps.setFloat(6, itemPedido.getComprimentoLiq());
                ps.setFloat(7, itemPedido.getAlturaLiq());
                ps.setFloat(8, itemPedido.getLarguraLiq());
                ps.setString(9, itemPedido.getAcabamento());
                ps.setFloat(10, itemPedido.getMetragem());
                ps.setBigDecimal(11, itemPedido.getValorUnitario());
                ps.setBigDecimal(12, itemPedido.getDesconto());
                ps.setBigDecimal(13, itemPedido.getValorTotal());
                
                ps.setLong(14, itemPedido.getId());
                ps.setLong(15, itemPedido.getMaterial().getId());
                ps.setLong(16, itemPedido.getPedido().getId());

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
                    = "SELECT id_item_pedido, id_material, id_pedido, quantidade, tipo, comprimento_br, altura_br, \n"
                    + "       largura_br, comprimento_liq, altura_liq, largura_liq, acabamento, \n"
                    + "       metragem, valor_unitario, desconto, valor_total\n"
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
                
                itemPedido.setQuantidade(rs.getLong(4));
                itemPedido.setTipo(rs.getString(5));
                itemPedido.setComprimentoBr(rs.getFloat(6));
                itemPedido.setAlturaBr(rs.getFloat(7));
                itemPedido.setLarguraBr(rs.getFloat(8));
                itemPedido.setComprimentoLiq(rs.getFloat(9));
                itemPedido.setAlturaLiq(rs.getFloat(10));
                itemPedido.setLarguraLiq(rs.getFloat(11));
                itemPedido.setAcabamento(rs.getString(12));
                itemPedido.setMetragem(rs.getFloat(13));
                itemPedido.setValorUnitario(rs.getBigDecimal(14));
                itemPedido.setDesconto(rs.getBigDecimal(15));
                itemPedido.setValorTotal(rs.getBigDecimal(16));
                
                itensPedido.add(itemPedido);

            }

            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar pedido em PgPedidoDAO", ex);
        }

        return itensPedido;
    }

}
