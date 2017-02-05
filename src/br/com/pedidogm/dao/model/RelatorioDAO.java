
package br.com.pedidogm.dao.model;

import br.com.pedidogm.domain.Pedido;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author douglas
 */
public interface RelatorioDAO {
    
    public JasperViewer impressaoPedido(Pedido pedido);
    
}
