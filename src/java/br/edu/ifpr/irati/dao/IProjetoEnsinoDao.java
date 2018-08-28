package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.ProjetoEnsino;
import java.io.Serializable;
import java.util.List;

public interface IProjetoEnsinoDao {

    public List<ProjetoEnsino> buscarProjetosEnsinoAtivos(Serializable professor);

}
