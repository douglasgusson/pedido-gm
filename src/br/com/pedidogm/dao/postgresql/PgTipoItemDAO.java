package br.com.pedidogm.dao.postgresql;

import br.com.pedidogm.dao.DAOException;
import br.com.pedidogm.dao.DAOFactory;
import br.com.pedidogm.dao.model.TipoItemDAO;
import br.com.pedidogm.domain.TipoItem;
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
public class PgTipoItemDAO implements TipoItemDAO {

    @Override
    public List<TipoItem> listarTodos() {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();
        List<TipoItem> tiposItem = new ArrayList<>();

        try {
            String query
                    = "SELECT id_tipo_item, descricao, referencia_calculo, data_criacao, data_atualizacao\n"
                    + "  FROM tipo_item ORDER BY id_tipo_item;";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TipoItem ti = new TipoItem();
                ti.setId(rs.getLong(1));
                ti.setDescricao(rs.getString(2));
                ti.setReferenciaCalculo(rs.getInt(3));
                ti.setCriacao(rs.getTimestamp(4).toLocalDateTime());
                ti.setAlteracao(rs.getTimestamp(5).toLocalDateTime());
                tiposItem.add(ti);
            }

            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao listar tipos de item em PgTipoItemDAO", ex);
        }

        return tiposItem;
    }

    @Override
    public void inserir(TipoItem tipoItem) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {
            String SQL
                    = "INSERT INTO tipo_item(\n"
                    + "            descricao, referencia_calculo, data_criacao, data_atualizacao)\n"
                    + "    VALUES (?, ?, ?, ?);";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setString(1, tipoItem.getDescricao());
                ps.setInt(2, tipoItem.getReferenciaCalculo());
                ps.setTimestamp(3, Timestamp.valueOf(tipoItem.getCriacao()));
                ps.setTimestamp(4, Timestamp.valueOf(tipoItem.getAlteracao()));

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao inserir tipo de item em PgTipoItemDAO", ex);
        }
    }

    @Override
    public void alterar(TipoItem tipoItem) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {
            String SQL
                    = "UPDATE tipo_item\n"
                    + "   SET descricao=?, referencia_calculo=?, data_criacao=?, \n"
                    + "       data_atualizacao=?\n"
                    + " WHERE id_tipo_item = ?;";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setString(1, tipoItem.getDescricao());
                ps.setInt(2, tipoItem.getReferenciaCalculo());
                ps.setTimestamp(3, Timestamp.valueOf(tipoItem.getCriacao()));
                ps.setTimestamp(4, Timestamp.valueOf(tipoItem.getAlteracao()));
                ps.setLong(5, tipoItem.getId());

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao alterar tipo de item em PgTipoItemDAO", ex);
        }
    }

    @Override
    public void excluir(TipoItem tipoItem) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {

            String SQL = "DELETE FROM tipo_item\n"
                    + " WHERE  id_tipo_item = ?;";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setLong(1, tipoItem.getId());

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao excluir tipo de item em PgTipoItemDAO", ex);
        }

    }

    @Override
    public TipoItem buscar(Long id) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();
        TipoItem ti = new TipoItem();

        try {
            String query
                    = "SELECT id_tipo_item, descricao, referencia_calculo, data_criacao, data_atualizacao\n"
                    + "  FROM tipo_item WHERE id_tipo_item = ?;";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ti.setId(rs.getLong(1));
                ti.setDescricao(rs.getString(2));
                ti.setReferenciaCalculo(rs.getInt(3));
                ti.setCriacao(rs.getTimestamp(4).toLocalDateTime());
                ti.setAlteracao(rs.getTimestamp(5).toLocalDateTime());
            }

            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar tipo de item em PgTipoItemDAO", ex);
        }

        return ti;
    }

}
