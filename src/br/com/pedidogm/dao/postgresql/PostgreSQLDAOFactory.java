package br.com.pedidogm.dao.postgresql;

import br.com.pedidogm.dao.DAOException;
import br.com.pedidogm.dao.DAOFactory;
import br.com.pedidogm.dao.model.AcabamentoDAO;
import br.com.pedidogm.dao.model.ClienteDAO;
import br.com.pedidogm.dao.model.ItemPedidoDAO;
import br.com.pedidogm.dao.model.MaterialDAO;
import br.com.pedidogm.dao.model.PedidoDAO;
import br.com.pedidogm.dao.model.RelatorioDAO;
import br.com.pedidogm.dao.model.TipoItemDAO;
import br.com.pedidogm.dao.model.TransportadorDAO;
import br.com.pedidogm.dao.model.UsuarioDAO;
import br.com.pedidogm.domain.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author douglas
 */
public class PostgreSQLDAOFactory extends DAOFactory {

    private static final Database DATABASE = Database.getDatabase();
    private static final String URL_BANCO = "jdbc:postgresql://" + DATABASE.toString();
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String USUARIO = DATABASE.getUsuario();
    private static final String SENHA = DATABASE.getSenha();

    private static Connection connection;

    @Override
    public Connection getConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL_BANCO, USUARIO, SENHA);
            return connection;
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,
                    "Conector para PostgreSQL não foi encontrado.",
                    "Erro de conexão",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            throw new DAOException("Não foi possível estabelecer conexão com o banco de dados.", ex);
        }
        return null;
    }

    @Override
    public UsuarioDAO getUsuarioDAO() {
        return new PgUsuarioDAO();
    }

    @Override
    public MaterialDAO getMaterialDAO() {
        return new PgMaterialDAO();
    }

    @Override
    public ClienteDAO getClienteDAO() {
        return new PgClienteDAO();
    }

    @Override
    public PedidoDAO getPedidoDAO() {
        return new PgPedidoDAO();
    }

    @Override
    public ItemPedidoDAO getItemPedidoDAO() {
        return new PgItemPedidoDAO();
    }

    @Override
    public RelatorioDAO getRelatorioDAO() {
        return new PgRelatorioDAO();
    }

    @Override
    public TipoItemDAO getTipoItemDAO() {
        return new PgTipoItemDAO();
    }

    @Override
    public AcabamentoDAO getAcabamentoDAO() {
        return new PgAcabamentoDAO();
    }

    @Override
    public TransportadorDAO getTransportadorDAO() {
        return new PgTransportadorDAO();
    }

}
