package br.com.pedidogm.dao.postgresql;

import br.com.pedidogm.dao.DAOException;
import br.com.pedidogm.dao.DAOFactory;
import br.com.pedidogm.dao.model.AcabamentoDAO;
import br.com.pedidogm.domain.Acabamento;
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
public class PgAcabamentoDAO implements AcabamentoDAO {

    @Override
    public List<Acabamento> listarTodos() {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();
        List<Acabamento> acabamentos = new ArrayList<>();

        try {
            String query
                    = "SELECT id_acabamento, descricao, data_criacao, data_atualizacao\n"
                    + "  FROM acabamento;";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Acabamento a = new Acabamento();
                a.setId(rs.getLong(1));
                a.setDescricao(rs.getString(2));
                a.setCriacao(rs.getTimestamp(3).toLocalDateTime());
                a.setAlteracao(rs.getTimestamp(4).toLocalDateTime());
                acabamentos.add(a);
            }

            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao listar acabamentos em PgAcabamentoDAO", ex);
        }

        return acabamentos;
    }

    @Override
    public void inserir(Acabamento acabamento) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {
            String SQL
                    = "INSERT INTO acabamento(\n"
                    + "            descricao, data_criacao, data_atualizacao)\n"
                    + "    VALUES (?, ?, ?);";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setString(1, acabamento.getDescricao());
                ps.setTimestamp(2, Timestamp.valueOf(acabamento.getCriacao()));
                ps.setTimestamp(3, Timestamp.valueOf(acabamento.getAlteracao()));

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao inserir acabamento em PgAcabamentoDAO", ex);
        }
    }

    @Override
    public void alterar(Acabamento acabamento) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {
            String SQL
                    = "UPDATE acabamento\n"
                    + "   SET descricao=?, data_criacao=?, data_atualizacao=?\n"
                    + " WHERE id_acabamento=?;";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setString(1, acabamento.getDescricao());
                ps.setTimestamp(2, Timestamp.valueOf(acabamento.getCriacao()));
                ps.setTimestamp(3, Timestamp.valueOf(acabamento.getAlteracao()));
                ps.setLong(4, acabamento.getId());

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao alterar acabamento em PgAcabamentoDAO", ex);
        }
    }

    @Override
    public void excluir(Acabamento acabamento) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {

            String SQL = "DELETE FROM acabamento\n"
                    + " WHERE id_acabamento = ?;";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setLong(1, acabamento.getId());

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao excluir acabamento em PgAcabamentoDAO", ex);
        }
    }

    @Override
    public Acabamento buscar(Long id) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();
        Acabamento a = new Acabamento();

        try {
            String query
                    = "SELECT id_acabamento, descricao, data_criacao, data_atualizacao\n"
                    + "  FROM acabamento WHERE id_acabamento = ?;";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                a.setId(rs.getLong(1));
                a.setDescricao(rs.getString(2));
                a.setCriacao(rs.getTimestamp(3).toLocalDateTime());
                a.setAlteracao(rs.getTimestamp(4).toLocalDateTime());
            }

            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar acabamento em PgAcabamentoDAO", ex);
        }

        return a;
    }

}
