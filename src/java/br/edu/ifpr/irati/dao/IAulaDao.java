package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.Aula;
import br.edu.ifpr.irati.modelo.Professor;
import java.io.Serializable;
import java.util.List;

public interface IAulaDao {

    public List<Aula> buscarAulasAtivas(Serializable idUsuario);
    
    public List<Aula> buscarAulasPorProfessor(Serializable idUsuario);

}
