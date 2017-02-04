package br.com.pedidogm.dao.model;

import br.com.pedidogm.domain.Pedido;
import java.util.List;

/**
 *
 * @author douglas
 */
public interface PedidoDAO {

    public List<Pedido> listarTodos();
    public void inserir(Pedido pedido);
    public void alterar(Pedido pedido);    
    public void excluir(Pedido pedido);
    public Pedido buscar(Long id);

}
