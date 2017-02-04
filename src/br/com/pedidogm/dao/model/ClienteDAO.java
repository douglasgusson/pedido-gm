package br.com.pedidogm.dao.model;

import br.com.pedidogm.domain.Cliente;
import java.util.List;

/**
 *
 * @author douglas
 */
public interface ClienteDAO {

    public List<Cliente> listarTodos();
    public void inserir(Cliente cliente);
    public void alterar(Cliente cliente);
    public void excluir(Cliente cliente);
    public Cliente buscar(Long id);
    public List<Cliente> bucarPorNome(String str);

}
