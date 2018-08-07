/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.Aula;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class AulaMB {

    private Aula aula;
    private List<Aula> aulas;

    public AulaMB() {

        aula = new Aula();
        Dao<Aula> aulaDAO = new GenericDAO<>(Aula.class);
        aulas = aulaDAO.buscarTodos(Aula.class);

    }
    public String salvar() {

        Dao<Aula> aulaDAO = new GenericDAO<>(Aula.class);
        aulaDAO.salvar(aula);
        aula = new Aula();
        aulas = aulaDAO.buscarTodos(Aula.class);
        return "/CriarCorrigirPTD";
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

}
