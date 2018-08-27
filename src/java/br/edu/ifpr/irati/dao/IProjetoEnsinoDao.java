package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.modelo.ProjetoEnsino;
import java.util.List;

public interface IProjetoEnsinoDao {

    public List<ProjetoEnsino> buscarProjetosEnsinoAtivos(Professor professor);

}
