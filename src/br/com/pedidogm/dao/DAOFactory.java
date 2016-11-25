package br.com.pedidogm.dao;

import br.com.pedidogm.dao.model.MaterialDAO;
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
