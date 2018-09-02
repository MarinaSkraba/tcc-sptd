/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.PTDSubmetido;
import br.edu.ifpr.irati.modelo.Professor;
import java.util.List;

public interface IPTDSubmetidoDAO {
    
    public List<PTDSubmetido> buscarPTDsTodosSubmetidosPorProfessor(Professor professor);
    
    public List<PTDSubmetido> buscarPTDsSubmetidosAprovadosPorProfessor(Professor professor);
    
}
