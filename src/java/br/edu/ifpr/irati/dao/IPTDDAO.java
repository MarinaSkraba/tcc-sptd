/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.PTD;
import br.edu.ifpr.irati.modelo.Professor;
import java.io.Serializable;
import java.util.List;

public interface IPTDDAO {
    
    public List<PTD> buscarPTDsAprovados(Serializable idUsuario);
    
    public List<PTD> buscarPTDsEmEdicao(Serializable idUsuario);
    
    public List<PTD> buscarPTDEmAvaliacao();
    
    public List<PTD> buscarPTDsReprovados(Serializable idUsuario);
    
}
