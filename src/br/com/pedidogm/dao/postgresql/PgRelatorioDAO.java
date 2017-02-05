package br.com.pedidogm.dao.postgresql;

import br.com.pedidogm.dao.DAOException;
import br.com.pedidogm.dao.DAOFactory;
import br.com.pedidogm.dao.model.RelatorioDAO;
import br.com.pedidogm.domain.Pedido;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author douglas
 */
public class PgRelatorioDAO implements RelatorioDAO {

    @Override
    public JasperViewer impressaoPedido(Pedido pedido) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {

            String query
                    = "SELECT\n"
                    + "     pedido.\"id_pedido\" AS pedido_id_pedido,\n"
                    + "     pedido.\"id_cliente\" AS pedido_id_cliente,\n"
                    + "     pedido.\"valor_pedido\" AS pedido_valor_pedido,\n"
                    + "     pedido.\"placa_veiculo\" AS pedido_placa_veiculo,\n"
                    + "     pedido.\"nome_motorista\" AS pedido_nome_motorista,\n"
                    + "     pedido.\"observacoes\" AS pedido_observacoes,\n"
                    + "     pedido.\"data_carregamento\" AS pedido_data_carregamento,\n"
                    + "     pedido.\"data_criacao\" AS pedido_data_criacao,\n"
                    + "     pedido.\"data_atualizacao\" AS pedido_data_atualizacao,\n"
                    + "     pedido.\"id_usuario\" AS pedido_id_usuario,\n"
                    + "     usuario.\"nome_usuario\" AS usuario_nome_usuario,\n"
                    + "     usuario.\"nome_completo\" AS usuario_nome_completo,\n"
                    + "     cliente.\"id_cliente\" AS cliente_id_cliente,\n"
                    + "     cliente.\"nome_cliente\" AS cliente_nome_cliente,\n"
                    + "     cliente.\"apelido\" AS cliente_apelido,\n"
                    + "     cliente.\"telefone\" AS cliente_telefone,\n"
                    + "     cliente.\"celular\" AS cliente_celular,\n"
                    + "     cliente.\"email\" AS cliente_email,\n"
                    + "     cliente.\"contato\" AS cliente_contato,\n"
                    + "     cliente.\"observacoes\" AS cliente_observacoes,\n"
                    + "     cliente.\"data_criacao\" AS cliente_data_criacao,\n"
                    + "     cliente.\"data_atualizacao\" AS cliente_data_atualizacao,\n"
                    + "     item_pedido.\"id_item_pedido\" AS item_pedido_id_item_pedido,\n"
                    + "     item_pedido.\"id_material\" AS item_pedido_id_material,\n"
                    + "     item_pedido.\"id_pedido\" AS item_pedido_id_pedido,\n"
                    + "     item_pedido.\"quantidade\" AS item_pedido_quantidade,\n"
                    + "     item_pedido.\"tipo\" AS item_pedido_tipo,\n"
                    + "     item_pedido.\"comprimento_br\" AS item_pedido_comprimento_br,\n"
                    + "     item_pedido.\"altura_br\" AS item_pedido_altura_br,\n"
                    + "     item_pedido.\"largura_br\" AS item_pedido_largura_br,\n"
                    + "     item_pedido.\"comprimento_liq\" AS item_pedido_comprimento_liq,\n"
                    + "     item_pedido.\"altura_liq\" AS item_pedido_altura_liq,\n"
                    + "     item_pedido.\"largura_liq\" AS item_pedido_largura_liq,\n"
                    + "     item_pedido.\"acabamento\" AS item_pedido_acabamento,\n"
                    + "     item_pedido.\"metragem\" AS item_pedido_metragem,\n"
                    + "     item_pedido.\"valor_unitario\" AS item_pedido_valor_unitario,\n"
                    + "     item_pedido.\"desconto\" AS item_pedido_desconto,\n"
                    + "     item_pedido.\"valor_total\" AS item_pedido_valor_total,\n"
                    + "     material.\"nome_material\" AS material_nome_material\n"
                    + "FROM\n"
                    + "     \"public\".\"usuario\" usuario INNER JOIN \"public\".\"pedido\" pedido ON usuario.\"id_usuario\" = pedido.\"id_usuario\"\n"
                    + "     INNER JOIN \"public\".\"cliente\" cliente ON pedido.\"id_cliente\" = cliente.\"id_cliente\"\n"
                    + "     INNER JOIN \"public\".\"item_pedido\" item_pedido ON pedido.\"id_pedido\" = item_pedido.\"id_pedido\"\n"
                    + "     INNER JOIN \"public\".\"material\" material ON item_pedido.\"id_material\" = material.\"id_material\"\n"
                    + "WHERE\n"
                    + "     pedido.\"id_pedido\" = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, pedido.getId());

            ResultSet rs = ps.executeQuery();

            JRResultSetDataSource jrResultSetDataSource = new JRResultSetDataSource(rs);

            InputStream fonte = PgRelatorioDAO.class.getResourceAsStream(
                    "/br/com/pedidogm/report/ImpressaoPedido.jrxml");

            JasperReport jasperReport = JasperCompileManager.compileReport(fonte);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, jrResultSetDataSource);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);

            return jasperViewer;

        } catch (JRException | SQLException ex) {
            throw new DAOException("Falha ao gerar impressao do pedido em PgRelatorioDAO", ex);
        }

    }

}
