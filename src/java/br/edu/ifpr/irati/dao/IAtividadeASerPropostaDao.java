package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.AtividadeASerProposta;
import java.io.Serializable;
import java.util.List;

public interface IAtividadeASerPropostaDao {

    public List<AtividadeASerProposta> buscarAtividadesAtivas(Serializable idUsuario);

    public List<AtividadeASerProposta> buscarAtividadesPorProfessor(Serializable idUsuario);

}
