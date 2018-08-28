package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.modelo.ProjetoPesquisa;
import java.util.List;

public interface IProjetoPesquisaDao {

    public List<ProjetoPesquisa> buscarProjetosPesquisaAtivos(Professor professor);

}
