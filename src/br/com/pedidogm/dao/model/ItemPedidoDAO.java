
package br.com.pedidogm.dao.model;

import br.com.pedidogm.domain.ItemPedido;
import java.util.List;

/**
 *
 * @author douglas
 */
public interface ItemPedidoDAO {
    
    public void inserir(ItemPedido itemPedido);
    public void alterar(ItemPedido itemPedido);
    public void excluir(ItemPedido itemPedido);
    public List<ItemPedido> buscar(Long idPedido);
    
}
