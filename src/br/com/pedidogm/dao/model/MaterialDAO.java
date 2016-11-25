
package br.com.pedidogm.dao.model;

import br.com.pedidogm.domain.Material;
import java.util.List;

/**
 *
 * @author douglas
 */
public interface MaterialDAO {
    
    public List<Material> listarTodos();
    public void inserir(Material material);
    public void alterar(Material material);
    public Material buscar(Long id);
        
}
