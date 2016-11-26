package br.com.pedidogm.dao.postgresql;

import br.com.pedidogm.dao.DAOException;
import br.com.pedidogm.dao.DAOFactory;
import br.com.pedidogm.dao.model.MaterialDAO;
import br.com.pedidogm.domain.Material;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(Material material) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Material buscar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
