package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.ProjetoPesquisa;
import java.io.Serializable;
import java.util.List;

public interface IProjetoPesquisaDao {

    public List<ProjetoPesquisa> buscarProjetosPesquisaAtivos(Serializable idUsuario);

    public List<ProjetoPesquisa> buscarProjetosPesquisaPorProfessor(Serializable idUsuario);

    public List<ProjetoPesquisa> buscarProjetosPesquisaColabPorProfessor(Serializable idUsuario);
}
