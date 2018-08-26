/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.PTDSubmetido;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class DiretorEnsinoMB {

    private PTDSubmetido ptdSubmetido;
    private List<PTDSubmetido> ptdsSubmetidos;

    public DiretorEnsinoMB() {
        
        ptdSubmetido = new PTDSubmetido();
        Dao<PTDSubmetido> ptdSubmetidoDAO = new GenericDAO<>(PTDSubmetido.class);
        ptdsSubmetidos = ptdSubmetidoDAO.buscarTodos(PTDSubmetido.class);

    }

    public PTDSubmetido getPtdSubmetido() {
        return ptdSubmetido;
    }

    public void aprovarPTD(PTDSubmetido ptdSubmetido) {

        Dao<PTDSubmetido> ptdSubmetidoDAO = new GenericDAO<>(PTDSubmetido.class);
        ptdSubmetido.setEstado("Aprovado");
        ptdSubmetidoDAO.alterar(ptdSubmetido);

    }

    public void reprovarPTD(PTDSubmetido ptdSubmetido) {

        Dao<PTDSubmetido> ptdSubmetidoDAO = new GenericDAO<>(PTDSubmetido.class);
        ptdSubmetido.setEstado("Reprovado");
        ptdSubmetidoDAO.alterar(ptdSubmetido);

    }

    public void setPtdSubmetido(PTDSubmetido ptdSubmetido) {
        this.ptdSubmetido = ptdSubmetido;
    }

    public List<PTDSubmetido> getPtdsSubmetidos() {
        return ptdsSubmetidos;
    }

    public void setPtdsSubmetidos(List<PTDSubmetido> ptdsSubmetidos) {
        this.ptdsSubmetidos = ptdsSubmetidos;
    }

}
