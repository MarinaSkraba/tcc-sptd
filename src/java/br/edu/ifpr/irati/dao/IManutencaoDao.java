package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.ManutencaoEnsino;
import java.io.Serializable;
import java.util.List;

public interface IManutencaoDao {

    public List<ManutencaoEnsino> buscarManutencoesAtivas(Serializable idUsuario);
    
    public List<ManutencaoEnsino> buscarManutencoesPorProfessor(Serializable idUsuario);

}
