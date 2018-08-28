
package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.Administracao;
import java.io.Serializable;
import java.util.List;

public interface IAdministracaoDao {
 
     public List<Administracao> buscarAdministracoesAtivas(Serializable idUsuario);
}
