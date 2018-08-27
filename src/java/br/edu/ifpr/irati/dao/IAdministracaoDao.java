
package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.Administracao;
import br.edu.ifpr.irati.modelo.Professor;
import java.util.List;

public interface IAdministracaoDao {
 
     public List<Administracao> buscarAdministracoesAtivas(Professor professor);
}
