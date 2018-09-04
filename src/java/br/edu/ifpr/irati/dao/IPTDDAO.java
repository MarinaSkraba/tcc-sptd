/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.PTD;
import br.edu.ifpr.irati.modelo.Professor;
import java.util.List;

public interface IPTDDAO {
    
    public List<PTD> buscarPTDsAprovados(Professor professor);
    
    public List<PTD> buscarPTDsEmEdicao(Professor professor);
    
    public List<PTD> buscarPTDEmAvaliacao(Professor professor);
    
}
