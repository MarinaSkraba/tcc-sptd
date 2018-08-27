package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.Aula;
import br.edu.ifpr.irati.modelo.Professor;
import java.util.List;

public interface IAulaDao {

    public List<Aula> buscarAulasAtivas(Professor professor);

}
