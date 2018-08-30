
package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.OutroTipoAtividade;
import java.io.Serializable;
import java.util.List;

public interface IOutroTipoAtividadeDao {
 
    
     public List<OutroTipoAtividade> buscarOutrosTipoAtividadesAtivas(Serializable professor);
     
     public List<OutroTipoAtividade> buscarOutrosTipoAtividadesPorProfessor(Serializable professor);
    
}
