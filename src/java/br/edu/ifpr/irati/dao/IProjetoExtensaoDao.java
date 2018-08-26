package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.ProjetoExtensao;
import java.util.List;

public interface IProjetoExtensaoDao {

    public List<ProjetoExtensao> buscarProjetosExtensaoAtivos();

}
