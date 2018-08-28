package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.ProjetoExtensao;
import java.io.Serializable;
import java.util.List;

public interface IProjetoExtensaoDao {

public List<ProjetoExtensao> buscarProjetosExtensaoAtivos(Serializable idUsuario);

}
