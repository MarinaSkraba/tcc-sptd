package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.PTD;
import java.io.Serializable;
import java.util.List;

public interface IPTDDAO {
    
    public List<PTD> buscarPTDsConcluidos(Serializable idUsuario);
    
    public List<PTD> buscarPTDsAprovados(Serializable idUsuario);
    
    public List<PTD> buscarPTDsEmEdicao(Serializable idUsuario);
    
    public List<PTD> buscarPTDEmAvaliacao();
    
    public List<PTD> buscarPTDsReprovados(Serializable idUsuario);
    
}
