package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.ManutencaoEnsino;
import br.edu.ifpr.irati.modelo.Professor;
import java.util.List;

public interface IManutencaoDao {

    public List<ManutencaoEnsino> buscarManutencoesAtivas(Professor professor);

}
