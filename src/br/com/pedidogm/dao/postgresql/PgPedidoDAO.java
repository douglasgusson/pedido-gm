package br.com.pedidogm.dao.postgresql;

import br.com.pedidogm.dao.DAOException;
import br.com.pedidogm.dao.DAOFactory;
import br.com.pedidogm.dao.model.ClienteDAO;
import br.com.pedidogm.dao.model.PedidoDAO;
import br.com.pedidogm.domain.Cliente;
import br.com.pedidogm.domain.Pedido;
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
        List<Pedido> pedidos = new ArrayList<>();

        try {
            String query
                    = "SELECT \n"
                    + "    id_pedido, \n"
                    + "    id_cliente, \n"
                    + "    valor_pedido, \n"
                    + "    placa_veiculo, \n"
                    + "    nome_motorista, \n"
                    + "    observacoes, \n"
                    + "    data_carregamento, \n"
                    + "    data_criacao, \n"
                    + "    data_atualizacao\n"
                    + "  FROM pedido;";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pedido p = new Pedido();
                p.setId(rs.getLong(1));
                ClienteDAO clienteDAO = DAOFactory.getDefaultDAOFactory().getClienteDAO();
                Cliente c = clienteDAO.buscar(rs.getLong(2));
                p.setCliente(c);
                p.setValor(rs.getBigDecimal(3));
                p.setPlaca(rs.getString(4));
                p.setMotorista(rs.getString(5));
                p.setObservacoes(rs.getString(6));
                p.setDataCarregamento(rs.getDate(7).toLocalDate());
                p.setCriacao(rs.getTimestamp(8).toLocalDateTime());
                p.setAlteracao(rs.getTimestamp(9).toLocalDateTime());
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
                    + "            id_cliente, valor_pedido, placa_veiculo, nome_motorista, \n"
                    + "            observacoes, data_carregamento, data_criacao, data_atualizacao)\n"
                    + "    VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setLong(1, pedido.getCliente().getId());
                ps.setBigDecimal(2, pedido.getValor());
                ps.setString(3, pedido.getPlaca());
                ps.setString(4, pedido.getMotorista());
                ps.setString(5, pedido.getObservacoes());
                ps.setDate(6, Date.valueOf(pedido.getDataCarregamento()));
                ps.setTimestamp(7, Timestamp.valueOf(pedido.getCriacao()));
                ps.setTimestamp(8, Timestamp.valueOf(pedido.getAlteracao()));

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
                    + "   SET id_cliente=?, valor_pedido=?, placa_veiculo=?, nome_motorista=?, \n"
                    + "       observacoes=?, data_carregamento=?, data_criacao=?, data_atualizacao=?\n"
                    + " WHERE id_pedido = ?;";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setLong(1, pedido.getCliente().getId());
                ps.setBigDecimal(2, pedido.getValor());
                ps.setString(3, pedido.getPlaca());
                ps.setString(4, pedido.getMotorista());
                ps.setString(5, pedido.getObservacoes());
                ps.setDate(6, Date.valueOf(pedido.getDataCarregamento()));
                ps.setTimestamp(7, Timestamp.valueOf(pedido.getCriacao()));
                ps.setTimestamp(8, Timestamp.valueOf(pedido.getAlteracao()));
                ps.setLong(9, pedido.getId());

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
        Pedido p = new Pedido();

        try {
            String query
                    = "SELECT \n"
                    + "    id_pedido, \n"
                    + "    id_cliente, \n"
                    + "    valor_pedido, \n"
                    + "    placa_veiculo, \n"
                    + "    nome_motorista, \n"
                    + "    observacoes, \n"
                    + "    data_carregamento, \n"
                    + "    data_criacao, \n"
                    + "    data_atualizacao\n"
                    + "  FROM pedido WHERE id_pedido = ?;";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                p.setId(rs.getLong(1));
                ClienteDAO clienteDAO = DAOFactory.getDefaultDAOFactory().getClienteDAO();
                Cliente c = clienteDAO.buscar(rs.getLong(2));
                p.setCliente(c);
                p.setValor(rs.getBigDecimal(3));
                p.setPlaca(rs.getString(4));
                p.setMotorista(rs.getString(5));
                p.setObservacoes(rs.getString(6));
                p.setDataCarregamento(rs.getDate(7).toLocalDate());
                p.setCriacao(rs.getTimestamp(8).toLocalDateTime());
                p.setAlteracao(rs.getTimestamp(9).toLocalDateTime());
            }

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Falha ao buscar pedido em PgPedidoDAO", ex);
        }

        return p;
    }

}
