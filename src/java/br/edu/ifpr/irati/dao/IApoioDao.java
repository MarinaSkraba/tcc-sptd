
package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.Apoio;
import java.io.Serializable;
import java.util.List;

public interface IApoioDao {
    
     public List<Apoio> buscarApoiosAtivos(Serializable professor);

     public List<Apoio> buscarApoiosPorProfessor(Serializable professor);
}
