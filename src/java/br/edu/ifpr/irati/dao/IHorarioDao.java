
package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.Horario;
import java.io.Serializable;
import java.util.List;

public interface IHorarioDao {
    
    public List<Horario> buscarHorariosPorAula(Serializable idAula);
    
}
