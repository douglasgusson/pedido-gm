package br.com.pedidogm.dao.postgresql;

import br.com.pedidogm.dao.DAOException;
import br.com.pedidogm.dao.DAOFactory;
import br.com.pedidogm.dao.model.UsuarioDAO;
import br.com.pedidogm.domain.Sessao;
import br.com.pedidogm.domain.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author douglas
 */
public class PgUsuarioDAO implements UsuarioDAO {

    @Override
    public List<Usuario> listarTodos() {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();
        List<Usuario> usuarios = new ArrayList<>();

        try {
            String query
                    = "SELECT \n"
                    + "    id_usuario,\n"
                    + "    nome_usuario,\n"
                    + "    senha,\n"
                    + "    nome_completo,\n"
                    + "    email,\n"
                    + "    ultimo_acesso,\n"
                    + "    nova_senha,\n"
                    + "    ativo,\n"
                    + "    administrador\n"
                    + "  FROM usuario \n"
                    + "ORDER BY nome_usuario;";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getLong(1));
                u.setNomeUsuario(rs.getString(2));
                u.setSenha(rs.getString(3));
                u.setNomeCompleto(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setUltimoAcesso(rs.getTimestamp(6).toLocalDateTime());
                u.setNovaSenha(rs.getBoolean(7));
                u.setAtivo(rs.getBoolean(8));
                u.setAdmin(rs.getBoolean(9));

                usuarios.add(u);
            }

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Falha ao listar usuarios em PgUsuarioDAO", ex);
        }

        return usuarios;
    }

    @Override
    public void inserir(Usuario u) {
        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {
            String SQL
                    = "INSERT INTO usuario (nome_usuario, senha, nome_completo, email, ultimo_acesso, nova_senha, ativo, administrador)\n"
                    + "  VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setString(1, u.getNomeUsuario());
                ps.setString(2, u.getSenha());
                ps.setString(3, u.getNomeCompleto());
                ps.setString(4, u.getEmail());
                ps.setTimestamp(5, Timestamp.valueOf(u.getUltimoAcesso()));
                ps.setBoolean(6, u.isNovaSenha());
                ps.setBoolean(7, u.isAtivo());
                ps.setBoolean(8, u.isAdmin());

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao inserir usuario em PgUsuarioDAO", ex);
        }
    }

    @Override
    public void alterar(Usuario u) {

        Connection connection = DAOFactory.getDefaultDAOFactory().getConnection();

        try {
            String SQL
                    = "UPDATE usuario\n"
                    + "  SET nome_usuario = ?,\n"
                    + "      senha = ?,\n"
                    + "      nome_completo = ?,\n"
                    + "      email = ?,\n"
                    + "      ultimo_acesso = ?,\n"
                    + "      nova_senha = ?,\n"
                    + "      ativo = ?,\n"
                    + "      administrador = ?\n"
                    + "  WHERE id_usuario = ?;";

            try (PreparedStatement ps = connection.prepareStatement(SQL)) {

                ps.setString(1, u.getNomeUsuario());
                ps.setString(2, u.getSenha());
                ps.setString(3, u.getNomeCompleto());
                ps.setString(4, u.getEmail());
                ps.setTimestamp(5, Timestamp.valueOf(u.getUltimoAcesso()));
                ps.setBoolean(6, u.isNovaSenha());
                ps.setBoolean(7, u.isAtivo());
                ps.setBoolean(8, u.isAdmin());
                ps.setLong(9, u.getId());

                ps.executeUpdate();
            }
            connection.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao alterar usuário!", ex);
        }
    }

    @Override
    public boolean logar(Usuario usuario) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();
        Usuario u = new Usuario();

        try {
            String query
                    = "SELECT \n"
                    + "    id_usuario,\n"
                    + "    nome_usuario,\n"
                    + "    senha,\n"
                    + "    nome_completo,\n"
                    + "    email,\n"
                    + "    ultimo_acesso,\n"
                    + "    nova_senha,\n"
                    + "    ativo,\n"
                    + "    administrador\n"
                    + "  FROM usuario WHERE nome_usuario = ? AND senha = ?;";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, usuario.getNomeUsuario());
            ps.setString(2, usuario.getSenha());

            ResultSet rs = ps.executeQuery();

            if (rs.next() && rs.getBoolean(8)) {
                u.setId(rs.getLong(1));
                u.setNomeUsuario(rs.getString(2));
                u.setSenha(rs.getString(3));
                u.setNomeCompleto(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setUltimoAcesso(rs.getTimestamp(6).toLocalDateTime());
                u.setNovaSenha(rs.getBoolean(7));
                u.setAtivo(rs.getBoolean(8));
                u.setAdmin(rs.getBoolean(9));
                u.setUltimoAcesso(LocalDateTime.now());
                Sessao.setUsuario(u);
                alterar(u);
                return true;
            }

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Falha ao logar em PgUsuarioDAO", ex);
        }

        return false;
    }

    @Override
    public Usuario buscar(Long id) {

        Connection connection = DAOFactory.getDefaultDAOFactory().getConnection();

        Usuario usuario = new Usuario();

        try {
            String query
                    = "SELECT \n"
                    + "    id_usuario,\n"
                    + "    nome_usuario,\n"
                    + "    senha,\n"
                    + "    nome_completo,\n"
                    + "    email,\n"
                    + "    ultimo_acesso,\n"
                    + "    nova_senha,\n"
                    + "    ativo,\n"
                    + "    administrador\n"
                    + "  FROM usuario WHERE id_usuario = ?;";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    String nomeUsuario = rs.getString(2);
                    String senha = rs.getString(3);
                    String nomeCompleto = rs.getString(4);
                    String email = rs.getString(5);
                    LocalDateTime ultimoAcesso = rs.getTimestamp(6).toLocalDateTime();
                    Boolean novaSenha = rs.getBoolean(7);
                    Boolean ativo = rs.getBoolean(8);
                    Boolean admin = rs.getBoolean(9);

                    usuario = new Usuario(nomeUsuario, senha, nomeCompleto, email, ultimoAcesso, novaSenha, ativo, admin);
                }
            }
            connection.close();

            return usuario;

        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar usuario por id!", ex);
        }
    }

    @Override
    public void excluir(Usuario usuario) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {

            String SQL = "DELETE FROM usuario WHERE id_usuario = ?;";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setLong(1, usuario.getId());

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao excluir usuario em PgUsuarioDAO", ex);
        }
    }

}
