package br.com.pedidogm.dao.postgresql;

import br.com.pedidogm.dao.DAOException;
import br.com.pedidogm.dao.DAOFactory;
import br.com.pedidogm.dao.model.MaterialDAO;
import br.com.pedidogm.domain.Material;
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
public class PgMaterialDAO implements MaterialDAO {

    @Override
    public List<Material> listarTodos() {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();
        List<Material> materiais = new ArrayList<>();

        try {
            String query
                    = "SELECT \n"
                    + "    id_material, \n"
                    + "    nome_material, \n"
                    + "    data_criacao, \n"
                    + "    data_atualizacao \n"
                    + "  FROM material;";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Material m = new Material();
                m.setId(rs.getLong(1));
                m.setNome(rs.getString(2));
                m.setCriacao(rs.getTimestamp(3).toLocalDateTime());
                m.setAlteracao(rs.getTimestamp(4).toLocalDateTime());
                materiais.add(m);
            }

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Falha ao listar materiais em PgMaterialDAO", ex);
        }

        return materiais;
    }

    @Override
    public void inserir(Material material) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {
            String SQL
                    = "INSERT INTO material (nome_material, data_criacao, data_atualizacao)\n"
                    + "  VALUES (?, ?, ?);";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setString(1, material.getNome());
                ps.setTimestamp(2, Timestamp.valueOf(material.getCriacao()));
                ps.setTimestamp(3, Timestamp.valueOf(material.getAlteracao()));

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao inserir material em PgMaterialDAO", ex);
        }
    }

    @Override
    public void alterar(Material material) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {
            String SQL
                    = "UPDATE material\n"
                    + "  SET nome_material = ?,\n"
                    + "      data_criacao = ?,\n"
                    + "      data_atualizacao = ?\n"
                    + "  WHERE id_material = ?;";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setString(1, material.getNome());
                ps.setTimestamp(2, Timestamp.valueOf(material.getCriacao()));
                ps.setTimestamp(3, Timestamp.valueOf(material.getAlteracao()));
                ps.setLong(4, material.getId());

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao alterar material em PgMaterialDAO", ex);
        }
    }

    @Override
    public void excluir(Material material) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {

            String SQL = "DELETE FROM material WHERE id_material = ?;";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setLong(1, material.getId());

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao excluir material em PgMaterialDAO", ex);
        }
    }

    @Override
    public Material buscar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Material> bucarPorNome(String str) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();
        List<Material> materiais = new ArrayList<>();

        try {
            String query
                    = "SELECT \n"
                    + "    id_material, \n"
                    + "    nome_material, \n"
                    + "    data_criacao, \n"
                    + "    data_atualizacao \n"
                    + "  FROM material WHERE LOWER(nome_material) "
                    + "LIKE '%" + str.toLowerCase() + "%';";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Material m = new Material();
                m.setId(rs.getLong(1));
                m.setNome(rs.getString(2));
                m.setCriacao(rs.getTimestamp(3).toLocalDateTime());
                m.setAlteracao(rs.getTimestamp(4).toLocalDateTime());
                materiais.add(m);
            }

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Falha ao buscar materiais por nome em PgMaterialDAO", ex);
        }

        return materiais;
    }

}
