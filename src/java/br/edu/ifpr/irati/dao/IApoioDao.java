
package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.Apoio;
import br.edu.ifpr.irati.modelo.Professor;
import java.util.List;

public interface IApoioDao {
    
     public List<Apoio> buscarApoiosAtivos(Professor professor);
     
}
