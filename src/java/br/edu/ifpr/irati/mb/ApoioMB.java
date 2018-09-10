/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.ApoioDAO;
import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.dao.IApoioDao;
import br.edu.ifpr.irati.modelo.Apoio;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.PTD;
import br.edu.ifpr.irati.modelo.TipoApoio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ApoioMB {

    private Apoio apoio;
    private List<Apoio> apoios;
    private Horario horario;
    private List<Horario> horarios;
    private TipoApoio tipoApoio;

    public ApoioMB() {

        horario = new Horario();
        tipoApoio = new TipoApoio();
        apoio = new Apoio();
        apoios = new ArrayList();

    }

    public void salvarApoio(Serializable idUsuario, PTD ptd) {

        Dao<Apoio> apoioDAO = new GenericDAO<>(Apoio.class);
        Dao<TipoApoio> tipoApoioDAO = new GenericDAO<>(TipoApoio.class);
        tipoApoioDAO.salvar(tipoApoio);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        horarioDAO.salvar(horario);
        apoio.setTipoApoio(tipoApoio);
        apoio.getHorariosApoio().add(horario);
        apoio.setEstadoAtividadeApoio("Ativo");
        apoioDAO.salvar(apoio);
        apoio = apoioDAO.buscarTodos(Apoio.class).get(apoioDAO.buscarTodos(Apoio.class).size()-1);
        ptd.getApoios().add(apoio);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        ptdDAO.alterar(ptd);
        apoio = new Apoio();
        

    }

    public String alterarApoio(Apoio apoio) {
        Dao<Apoio> apoioDAO = new GenericDAO<>(Apoio.class);
        this.apoio = apoio;
        apoioDAO.alterar(apoio);
        return "/adicionar aqui";
    }

    public String desabilitarApoio(Apoio apoio) {
        Dao<Apoio> apoioDAO = new GenericDAO<>(Apoio.class);
        apoio.setEstadoAtividadeApoio("Desativado");
        apoioDAO.alterar(apoio);
        return "/adicionar html aqui";
    }

    public String excluirApoio(Apoio apoio, PTD ptd) {
        Dao<Apoio> apoioDAO = new GenericDAO<>(Apoio.class);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);

        for (int i = 0; i <= apoio.getHorariosApoio().size(); i++) {
            horarioDAO.excluir(horario);
            apoio.getHorariosApoio().remove(horario);
        }

        ptd.getApoios().remove(apoio);
        ptdDAO.alterar(ptd);
        apoioDAO.excluir(apoio);

        return "/adicionar aqui";
    }

    public void adicionarHorarioApoio() {
        horarios.add(horario);
        horario = new Horario();
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

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public TipoApoio getTipoApoio() {
        return tipoApoio;
    }

    public void setTipoApoio(TipoApoio tipoApoio) {
        this.tipoApoio = tipoApoio;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }
}
