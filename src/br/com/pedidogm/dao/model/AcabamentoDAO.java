package br.com.pedidogm.dao.model;

import br.com.pedidogm.domain.Acabamento;
import java.util.List;

/**
 *
 * @author douglas
 */
public interface AcabamentoDAO {

    public List<Acabamento> listarTodos();
    public void inserir(Acabamento acabamento);
    public void alterar(Acabamento acabamento);
    public void excluir(Acabamento acabamento);
    public Acabamento buscar(Long id);

}
