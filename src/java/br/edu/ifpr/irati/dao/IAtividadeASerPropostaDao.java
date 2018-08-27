
package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.AtividadeASerProposta;
import br.edu.ifpr.irati.modelo.Professor;
import java.util.List;

public interface IAtividadeASerPropostaDao {
     
     public List<AtividadeASerProposta> buscarAtividadesAtivas(Professor professor);
}
