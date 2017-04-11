package br.com.pedidogm.dao.postgresql;

import br.com.pedidogm.dao.DAOException;
import br.com.pedidogm.dao.DAOFactory;
import br.com.pedidogm.dao.model.ClienteDAO;
import br.com.pedidogm.dao.model.ItemPedidoDAO;
import br.com.pedidogm.dao.model.PedidoDAO;
import br.com.pedidogm.dao.model.UsuarioDAO;
import br.com.pedidogm.domain.Cliente;
import br.com.pedidogm.domain.ItemPedido;
import br.com.pedidogm.domain.Pedido;
import br.com.pedidogm.domain.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author douglas
 */
public class PgPedidoDAO implements PedidoDAO {

    @Override
    public List<Pedido> listarTodos() {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        ClienteDAO clienteDAO = DAOFactory.getDefaultDAOFactory().getClienteDAO();
        UsuarioDAO usuarioDAO = DAOFactory.getDefaultDAOFactory().getUsuarioDAO();
        ItemPedidoDAO itemPedidoDAO = DAOFactory.getDefaultDAOFactory().getItemPedidoDAO();

        List<Pedido> pedidos = new ArrayList<>();

        try {
            String query
                    = "SELECT id_pedido, id_cliente, valor_desconto, valor_ipi, valor_seguro, \n"
                    + "       valor_frete, outros_valores, valor_pedido, placa_veiculo, nome_motorista, \n"
                    + "       observacoes, data_carregamento, data_criacao, data_atualizacao, \n"
                    + "       id_usuario\n"
                    + "  FROM pedido ORDER BY id_pedido DESC;";

            PreparedStatement ps = con.prepareStatement(
                    query, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Pedido p = new Pedido();

                p.setId(rs.getLong(1));

                Cliente c = clienteDAO.buscar(rs.getLong(2));
                p.setCliente(c);

                p.setDesconto(rs.getBigDecimal(3));
                p.setIpi(rs.getBigDecimal(4));
                p.setSeguro(rs.getBigDecimal(5));
                p.setFrete(rs.getBigDecimal(6));
                p.setOutros(rs.getBigDecimal(7));
                p.setValor(rs.getBigDecimal(8));
                p.setPlaca(rs.getString(9));
                p.setMotorista(rs.getString(10));
                p.setObservacoes(rs.getString(11));
                p.setDataCarregamento(rs.getDate(12).toLocalDate());
                p.setCriacao(rs.getTimestamp(13).toLocalDateTime());
                p.setAlteracao(rs.getTimestamp(14).toLocalDateTime());

                Usuario u = usuarioDAO.buscar(rs.getLong(15));
                p.setUsuario(u);

                List<ItemPedido> itens = itemPedidoDAO.buscarPorPedido(p);

                p.setItensPedido(itens);

                pedidos.add(p);
            }

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Falha ao listar pedidos em PgPedidoDAO", ex);
        }

        return pedidos;

    }

    @Override
    public void inserir(Pedido pedido) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {
            String SQL
                    = "INSERT INTO pedido(\n"
                    + "            id_cliente, valor_desconto, valor_ipi, valor_seguro, \n"
                    + "            valor_frete, outros_valores, valor_pedido, placa_veiculo, nome_motorista, \n"
                    + "            observacoes, data_carregamento, data_criacao, data_atualizacao, \n"
                    + "            id_usuario)\n"
                    + "    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setLong(1, pedido.getCliente().getId());
                ps.setBigDecimal(2, pedido.getDesconto());
                ps.setBigDecimal(3, pedido.getIpi());
                ps.setBigDecimal(4, pedido.getSeguro());
                ps.setBigDecimal(5, pedido.getFrete());
                ps.setBigDecimal(6, pedido.getOutros());
                ps.setBigDecimal(7, pedido.getValor());
                ps.setString(8, pedido.getPlaca());
                ps.setString(9, pedido.getMotorista());
                ps.setString(10, pedido.getObservacoes());
                ps.setDate(11, Date.valueOf(pedido.getDataCarregamento()));
                ps.setTimestamp(12, Timestamp.valueOf(pedido.getCriacao()));
                ps.setTimestamp(13, Timestamp.valueOf(pedido.getAlteracao()));
                ps.setLong(14, pedido.getUsuario().getId());

                ps.executeUpdate();

            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao inserir pedido em PgPedidoDAO", ex);
        }

    }

    @Override
    public void alterar(Pedido pedido) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {
            String SQL
                    = "UPDATE pedido\n"
                    + "   SET id_cliente=?, valor_desconto=?, valor_ipi=?, valor_seguro=?, \n"
                    + "       valor_frete=?, outros_valores=?, valor_pedido=?, placa_veiculo=?, \n"
                    + "       nome_motorista=?, observacoes=?, data_carregamento=?, data_criacao=?, \n"
                    + "       data_atualizacao=?, id_usuario=?\n"
                    + " WHERE id_pedido = ?;";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setLong(1, pedido.getCliente().getId());
                ps.setBigDecimal(2, pedido.getDesconto());
                ps.setBigDecimal(3, pedido.getIpi());
                ps.setBigDecimal(4, pedido.getSeguro());
                ps.setBigDecimal(5, pedido.getFrete());
                ps.setBigDecimal(6, pedido.getOutros());
                ps.setBigDecimal(7, pedido.getValor());
                ps.setString(8, pedido.getPlaca());
                ps.setString(9, pedido.getMotorista());
                ps.setString(10, pedido.getObservacoes());
                ps.setDate(11, Date.valueOf(pedido.getDataCarregamento()));
                ps.setTimestamp(12, Timestamp.valueOf(pedido.getCriacao()));
                ps.setTimestamp(13, Timestamp.valueOf(pedido.getAlteracao()));
                ps.setLong(14, pedido.getUsuario().getId());
                ps.setLong(15, pedido.getId());

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao alterar pedido em PgPedidoDAO", ex);
        }

    }

    @Override
    public void excluir(Pedido pedido) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {

            String SQL = "DELETE FROM pedido WHERE id_pedido = ?;";

            ItemPedidoDAO itemPedidoDAO = DAOFactory.getDefaultDAOFactory().getItemPedidoDAO();

            if (!pedido.getItensPedido().isEmpty()) {
                for (ItemPedido ip : pedido.getItensPedido()) {
                    itemPedidoDAO.excluir(ip);
                }
            }

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setLong(1, pedido.getId());

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao excluir pedido em PgPedidoDAO", ex);
        }
    }

    @Override
    public Pedido buscar(Long id) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        ClienteDAO clienteDAO = DAOFactory.getDefaultDAOFactory().getClienteDAO();
        UsuarioDAO usuarioDAO = DAOFactory.getDefaultDAOFactory().getUsuarioDAO();
        ItemPedidoDAO itemPedidoDAO = DAOFactory.getDefaultDAOFactory().getItemPedidoDAO();

        Pedido p = new Pedido();

        try {
            String query
                    = "SELECT id_pedido, id_cliente, valor_desconto, valor_ipi, valor_seguro, \n"
                    + "       valor_frete, outros_valores, valor_pedido, placa_veiculo, nome_motorista, \n"
                    + "       observacoes, data_carregamento, data_criacao, data_atualizacao, \n"
                    + "       id_usuario\n"
                    + "  FROM pedido WHERE id_pedido = ?;";

            PreparedStatement ps = con.prepareStatement(
                    query, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                p.setId(rs.getLong(1));

                Cliente c = clienteDAO.buscar(rs.getLong(2));
                p.setCliente(c);

                p.setDesconto(rs.getBigDecimal(3));
                p.setIpi(rs.getBigDecimal(4));
                p.setSeguro(rs.getBigDecimal(5));
                p.setFrete(rs.getBigDecimal(6));
                p.setOutros(rs.getBigDecimal(7));
                p.setValor(rs.getBigDecimal(8));
                p.setPlaca(rs.getString(9));
                p.setMotorista(rs.getString(10));
                p.setObservacoes(rs.getString(11));
                p.setDataCarregamento(rs.getDate(12).toLocalDate());
                p.setCriacao(rs.getTimestamp(13).toLocalDateTime());
                p.setAlteracao(rs.getTimestamp(14).toLocalDateTime());

                Usuario u = usuarioDAO.buscar(rs.getLong(15));
                p.setUsuario(u);

                List<ItemPedido> itens = itemPedidoDAO.buscarPorPedido(p);

                p.setItensPedido(itens);

            }

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Falha ao buscar pedido em PgPedidoDAO", ex);
        }

        return p;
    }

    @Override
    public Pedido buscarUltimoPedido() {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();
        Pedido p = new Pedido();

        try {

            String query = "SELECT MAX(id_pedido) FROM pedido;";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                p.setId(rs.getLong(1));
            }

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Falha ao buscar ultimo pedido em PgPedidoDAO", ex);
        }

        return p;
    }

}
