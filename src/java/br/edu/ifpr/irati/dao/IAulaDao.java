package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.Aula;
import java.util.List;

public interface IAulaDao {

    public List<Aula> buscarAulasAtivas();

}
