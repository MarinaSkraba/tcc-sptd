
package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.OutroTipoAtividade;
import br.edu.ifpr.irati.modelo.Professor;
import java.util.List;

public interface IOutroTipoAtividadeDao {
 
    
     public List<OutroTipoAtividade> buscarOutrosTipoAtividadesAtivas(Professor professor);
    
}
