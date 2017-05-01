package br.com.pedidogm.dao.postgresql;

import br.com.pedidogm.dao.DAOException;
import br.com.pedidogm.dao.DAOFactory;
import br.com.pedidogm.dao.model.TransportadorDAO;
import br.com.pedidogm.domain.Transportador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author douglas
 */
public class PgTransportadorDAO implements TransportadorDAO {

    @Override
    public List<Transportador> listarTodos() {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();
        List<Transportador> transportadores = new ArrayList<>();

        try {
            String query
                    = "SELECT id_transportador, \n"
                    + "       nome_transportador, \n"
                    + "       apelido_transportador, \n"
                    + "       placa_veiculo, \n"
                    + "       telefone, \n"
                    + "       celular, \n"
                    + "       observacoes, \n"
                    + "       data_criacao, \n"
                    + "       data_atualizacao\n"
                    + "  FROM transportador\n"
                    + "ORDER BY nome_transportador;";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Transportador t = new Transportador();
                t.setId(rs.getLong(1));
                t.setNome(rs.getString(2));
                t.setApelido(rs.getString(3));
                t.setPlaca(rs.getString(4));
                t.setTelefone(rs.getString(5));
                t.setCelular(rs.getString(6));
                t.setObservacoes(rs.getString(7));
                t.setCriacao(rs.getTimestamp(8).toLocalDateTime());
                t.setAlteracao(rs.getTimestamp(9).toLocalDateTime());
                transportadores.add(t);
            }

            con.close();

            return transportadores;

        } catch (SQLException ex) {
            throw new DAOException("Falha ao listar transportadores em PgTransportadorDAO", ex);
        }
    }

    @Override
    public void inserir(Transportador transportador) {
        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {
            String sql
                    = "INSERT INTO transportador(\n"
                    + "            nome_transportador, apelido_transportador, placa_veiculo, \n"
                    + "            telefone, celular, observacoes, data_criacao, \n"
                    + "            data_atualizacao)\n"
                    + "    VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, transportador.getNome());
                ps.setString(2, transportador.getApelido());
                ps.setString(3, transportador.getPlaca());
                ps.setString(4, transportador.getTelefone());
                ps.setString(5, transportador.getCelular());
                ps.setString(6, transportador.getObservacoes());
                ps.setTimestamp(7, Timestamp.valueOf(transportador.getCriacao()));
                ps.setTimestamp(8, Timestamp.valueOf(transportador.getAlteracao()));

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao inserir transportador em PgTransportadorDAO", ex);
        }
    }

    @Override
    public void alterar(Transportador transportador) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {
            String sql
                    = "UPDATE transportador\n"
                    + "   SET nome_transportador=?, apelido_transportador=?, \n"
                    + "       placa_veiculo=?, telefone=?, celular=?, observacoes=?, data_criacao=?, \n"
                    + "       data_atualizacao=?\n"
                    + " WHERE id_transportador=?;";

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, transportador.getNome());
                ps.setString(2, transportador.getApelido());
                ps.setString(3, transportador.getPlaca());
                ps.setString(4, transportador.getTelefone());
                ps.setString(5, transportador.getCelular());
                ps.setString(6, transportador.getObservacoes());
                ps.setTimestamp(7, Timestamp.valueOf(transportador.getCriacao()));
                ps.setTimestamp(8, Timestamp.valueOf(transportador.getAlteracao()));
                ps.setLong(9, transportador.getId());

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao alterar transportador em PgTransportadorDAO", ex);
        }
    }

    @Override
    public void excluir(Transportador transportador) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {

            String sql = "DELETE FROM transportador WHERE id_transportador = ?;";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setLong(1, transportador.getId());
            ps.executeUpdate();

            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao excluir transportador em PgTransportadorDAO", ex);
        }
    }

    @Override
    public Transportador buscar(Long id) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();
        Transportador t = new Transportador();

        try {
            String query
                    = "SELECT id_transportador, \n"
                    + "       nome_transportador, \n"
                    + "       apelido_transportador, \n"
                    + "       placa_veiculo, \n"
                    + "       telefone, \n"
                    + "       celular, \n"
                    + "       observacoes, \n"
                    + "       data_criacao, \n"
                    + "       data_atualizacao\n"
                    + "  FROM transportador \n"
                    + "ORDER BY nome_transportador;";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                t.setId(rs.getLong(1));
                t.setNome(rs.getString(2));
                t.setApelido(rs.getString(3));
                t.setPlaca(rs.getString(4));
                t.setTelefone(rs.getString(5));
                t.setCelular(rs.getString(6));
                t.setObservacoes(rs.getString(7));
                t.setCriacao(rs.getTimestamp(8).toLocalDateTime());
                t.setAlteracao(rs.getTimestamp(9).toLocalDateTime());
            }

            con.close();

            return t;

        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar transportador em PgTransportadorDAO", ex);
        }
    }

    @Override
    public List<Transportador> bucarPorNome(String str) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();
        List<Transportador> transportadores = new ArrayList<>();

        try {
            String query
                    = "SELECT id_transportador, \n"
                    + "       nome_transportador, \n"
                    + "       apelido_transportador, \n"
                    + "       placa_veiculo, \n"
                    + "       telefone, \n"
                    + "       celular, \n"
                    + "       observacoes, \n"
                    + "       data_criacao, \n"
                    + "       data_atualizacao\n"
                    + "  FROM transportador \n"
                    + "       WHERE LOWER(nome_transportador) LIKE '%" + str.toLowerCase() + "%'\n"
                    + "     OR LOWER(apelido_transportador) LIKE '%" + str.toLowerCase() + "%' \n"
                    + "ORDER BY nome_transportador;";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Transportador t = new Transportador();
                t.setId(rs.getLong(1));
                t.setNome(rs.getString(2));
                t.setApelido(rs.getString(3));
                t.setPlaca(rs.getString(4));
                t.setTelefone(rs.getString(5));
                t.setCelular(rs.getString(6));
                t.setObservacoes(rs.getString(7));
                t.setCriacao(rs.getTimestamp(8).toLocalDateTime());
                t.setAlteracao(rs.getTimestamp(9).toLocalDateTime());
                transportadores.add(t);
            }

            con.close();

            return transportadores;

        } catch (SQLException ex) {
            throw new DAOException(
                    "Falha ao buscar transportadores por nome em PgTransportadorDAO", ex);
        }
    }

}
