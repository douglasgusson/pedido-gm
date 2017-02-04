package br.com.pedidogm.dao.postgresql;

import br.com.pedidogm.dao.DAOException;
import br.com.pedidogm.dao.DAOFactory;
import br.com.pedidogm.dao.model.ClienteDAO;
import br.com.pedidogm.domain.Cliente;
import java.sql.Connection;
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
public class PgClienteDAO implements ClienteDAO {

    @Override
    public List<Cliente> listarTodos() {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();
        List<Cliente> clientes = new ArrayList<>();

        try {
            String query
                    = "SELECT\n"
                    + "    id_cliente,\n"
                    + "    nome_cliente,\n"
                    + "    apelido,\n"
                    + "    telefone,\n"
                    + "    celular,\n"
                    + "    email,\n"
                    + "    contato,\n"
                    + "    observacoes,\n"
                    + "    data_criacao,\n"
                    + "    data_atualizacao\n"
                    + "  FROM cliente;";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getLong(1));
                c.setNome(rs.getString(2));
                c.setApelido(rs.getString(3));
                c.setTelefone(rs.getString(4));
                c.setCelular(rs.getString(5));
                c.setEmail(rs.getString(6));
                c.setContato(rs.getString(7));
                c.setObservacoes(rs.getString(8));
                c.setCriacao(rs.getTimestamp(9).toLocalDateTime());
                c.setAlteracao(rs.getTimestamp(10).toLocalDateTime());
                clientes.add(c);
            }

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Falha ao listar cliente em PgClienteDAO", ex);
        }

        return clientes;
    }

    @Override
    public void inserir(Cliente cliente) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {
            String SQL
                    = "INSERT INTO cliente ("
                    + "nome_cliente, apelido, telefone, celular, "
                    + "email, contato, observacoes, data_criacao, "
                    + "data_atualizacao)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setString(1, cliente.getNome());
                ps.setString(2, cliente.getApelido());
                ps.setString(3, cliente.getTelefone());
                ps.setString(4, cliente.getCelular());
                ps.setString(5, cliente.getEmail());
                ps.setString(6, cliente.getContato());
                ps.setString(7, cliente.getObservacoes());
                ps.setTimestamp(8, Timestamp.valueOf(cliente.getCriacao()));
                ps.setTimestamp(9, Timestamp.valueOf(cliente.getAlteracao()));

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao inserir cliente em PgClienteDAO", ex);
        }
    }

    @Override
    public void alterar(Cliente cliente) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {
            String SQL
                    = "UPDATE cliente\n"
                    + "  SET\n"
                    + "    nome_cliente = ?,\n"
                    + "    apelido = ?,\n"
                    + "    telefone = ?,\n"
                    + "    celular = ?,\n"
                    + "    email = ?,\n"
                    + "    contato = ?,\n"
                    + "    observacoes = ?,\n"
                    + "    data_criacao = ?,\n"
                    + "    data_atualizacao = ?\n"
                    + "  WHERE id_cliente = ?;";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setString(1, cliente.getNome());
                ps.setString(2, cliente.getApelido());
                ps.setString(3, cliente.getTelefone());
                ps.setString(4, cliente.getCelular());
                ps.setString(5, cliente.getEmail());
                ps.setString(6, cliente.getContato());
                ps.setString(7, cliente.getObservacoes());
                ps.setTimestamp(8, Timestamp.valueOf(cliente.getCriacao()));
                ps.setTimestamp(9, Timestamp.valueOf(cliente.getAlteracao()));
                ps.setLong(10, cliente.getId());

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao alterar cliente em PgClienteDAO", ex);
        }
    }

    @Override
    public void excluir(Cliente cliente) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {

            String SQL = "DELETE FROM cliente WHERE id_cliente = ?;";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setLong(1, cliente.getId());

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao excluir cliente em PgClienteDAO", ex);
        }
    }

    @Override
    public Cliente buscar(Long id) {
        
        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();
        Cliente cliente = null;

        try {

            String query
                    = "SELECT\n"
                    + "    id_cliente,\n"
                    + "    nome_cliente,\n"
                    + "    apelido,\n"
                    + "    telefone,\n"
                    + "    celular,\n"
                    + "    email,\n"
                    + "    contato,\n"
                    + "    observacoes,\n"
                    + "    data_criacao,\n"
                    + "    data_atualizacao\n"
                    + "  FROM cliente WHERE id_cliente = ?;";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getLong(1));
                cliente.setNome(rs.getString(2));
                cliente.setApelido(rs.getString(3));
                cliente.setTelefone(rs.getString(4));
                cliente.setCelular(rs.getString(5));
                cliente.setEmail(rs.getString(6));
                cliente.setContato(rs.getString(7));
                cliente.setObservacoes(rs.getString(8));
                cliente.setCriacao(rs.getTimestamp(9).toLocalDateTime());
                cliente.setAlteracao(rs.getTimestamp(10).toLocalDateTime());

            }

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Falha ao buscar cliente por id em PgClienteDAO", ex);
        }

        return cliente;
        
        
    }

    @Override
    public List<Cliente> bucarPorNome(String str) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();
        List<Cliente> clientes = new ArrayList<>();

        try {

            String query
                    = "SELECT\n"
                    + "    id_cliente,\n"
                    + "    nome_cliente,\n"
                    + "    apelido,\n"
                    + "    telefone,\n"
                    + "    celular,\n"
                    + "    email,\n"
                    + "    contato,\n"
                    + "    observacoes,\n"
                    + "    data_criacao,\n"
                    + "    data_atualizacao\n"
                    + "  FROM cliente WHERE LOWER(nome_cliente) "
                    + "    LIKE '%" + str.toLowerCase() + "%'"
                    + "      OR LOWER(apelido) LIKE '%" + str.toLowerCase() + "%';";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getLong(1));
                c.setNome(rs.getString(2));
                c.setApelido(rs.getString(3));
                c.setTelefone(rs.getString(4));
                c.setCelular(rs.getString(5));
                c.setEmail(rs.getString(6));
                c.setContato(rs.getString(7));
                c.setObservacoes(rs.getString(8));
                c.setCriacao(rs.getTimestamp(9).toLocalDateTime());
                c.setAlteracao(rs.getTimestamp(10).toLocalDateTime());
                clientes.add(c);
            }

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Falha ao buscar clientes por nome em PgClienteDAO", ex);
        }

        return clientes;
    }

}
