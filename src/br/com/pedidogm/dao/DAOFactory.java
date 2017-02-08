package br.com.pedidogm.dao;

import br.com.pedidogm.dao.model.AcabamentoDAO;
import br.com.pedidogm.dao.model.ClienteDAO;
import br.com.pedidogm.dao.model.ItemPedidoDAO;
import br.com.pedidogm.dao.model.MaterialDAO;
import br.com.pedidogm.dao.model.PedidoDAO;
import br.com.pedidogm.dao.model.RelatorioDAO;
import br.com.pedidogm.dao.model.TipoItemDAO;
import br.com.pedidogm.dao.model.UsuarioDAO;
import br.com.pedidogm.dao.postgresql.PostgreSQLDAOFactory;
import java.sql.Connection;

/**
 *
 * @author douglas
 */
public abstract class DAOFactory {

    public static final int POSTGRESQL = 0;
    public static final int MYSQL = 1;
    public static final int OUTRO = 2;

    public abstract UsuarioDAO getUsuarioDAO();
    public abstract MaterialDAO getMaterialDAO();
    public abstract ClienteDAO getClienteDAO();
    public abstract PedidoDAO getPedidoDAO();
    public abstract ItemPedidoDAO getItemPedidoDAO();
    public abstract TipoItemDAO getTipoItemDAO();
    public abstract AcabamentoDAO getAcabamentoDAO();
    public abstract RelatorioDAO getRelatorioDAO();
    public abstract Connection getConnection();

    public static DAOFactory getDAOFactory(int whichfactory) {
        switch (whichfactory) {
            case POSTGRESQL:
                return new PostgreSQLDAOFactory();
            case MYSQL:
                return null;
            case OUTRO:
                return null;
            default:
                return null;
        }
    }

    public static DAOFactory getDefaultDAOFactory() {
        return getDAOFactory(POSTGRESQL);
    }

}
