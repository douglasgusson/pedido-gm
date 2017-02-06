package br.com.pedidogm.dao.model;

import br.com.pedidogm.domain.TipoItem;
import java.util.List;

/**
 *
 * @author douglas
 */
public interface TipoItemDAO {

    public List<TipoItem> listarTodos();
    public void inserir(TipoItem tipoItem);
    public void alterar(TipoItem tipoItem);
    public void excluir(TipoItem tipoItem);
    public TipoItem buscar(Long id);

}
