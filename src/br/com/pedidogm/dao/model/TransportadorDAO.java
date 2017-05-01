package br.com.pedidogm.dao.model;

import br.com.pedidogm.domain.Transportador;
import java.util.List;

/**
 *
 * @author douglas
 */
public interface TransportadorDAO {

    public List<Transportador> listarTodos();
    public void inserir(Transportador transportador);
    public void alterar(Transportador transportador);
    public void excluir(Transportador transportador);
    public Transportador buscar(Long id);
    public List<Transportador> bucarPorNome(String str);

}
