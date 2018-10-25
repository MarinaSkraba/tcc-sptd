package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.DiretorEnsino;
import java.util.List;

public interface IDiretorEnsinoDAO {
    
    public List<DiretorEnsino> buscarDiretoresASeremHabilitados();
    
}
