/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.Apoio;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ApoioMB {

    private Apoio apoio;
    private List<Apoio> apoios;

    public ApoioMB() {

        apoio = new Apoio();
        Dao<Apoio> apoioDAO = new GenericDAO<>(Apoio.class);
        apoios = apoioDAO.buscarTodos(Apoio.class);

    }

    public void salvar() {

        Dao<Apoio> apoioDAO = new GenericDAO<>(Apoio.class);
        apoioDAO.salvar(apoio);
        apoio = new Apoio();
        apoios = apoioDAO.buscarTodos(Apoio.class);

    }

    public Apoio getApoio() {
        return apoio;
    }

    public void setApoio(Apoio apoio) {
        this.apoio = apoio;
    }

    public List<Apoio> getApoios() {
        return apoios;
    }

    public void setApoios(List<Apoio> apoios) {
        this.apoios = apoios;
    }
}
