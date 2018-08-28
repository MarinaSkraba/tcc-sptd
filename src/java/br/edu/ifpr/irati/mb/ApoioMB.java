/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.Apoio;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.TipoApoio;
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
        Dao<Apoio> apoioDAO = new GenericDAO<>(Apoio.class);
        apoios = apoioDAO.buscarTodos(Apoio.class);

    }

    public void salvarApoio() {

        Dao<Apoio> apoioDAO = new GenericDAO<>(Apoio.class);
        Dao<TipoApoio> tipoApoioDAO = new GenericDAO<>(TipoApoio.class);
        tipoApoioDAO.salvar(tipoApoio);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        horarioDAO.salvar(horario);
        apoio.setTipoApoio(tipoApoio);
        apoio.getHorariosApoio().add(horario);
        apoio.setEstadoAtividadeApoio("Ativo");
        apoioDAO.salvar(apoio);
        apoios = apoioDAO.buscarTodos(Apoio.class);

    }

    public String alterarApoio(Apoio apoio) {
        this.apoio = apoio;
        return "/adicionar aqui";
    }

    public String desabilitarApoio(Apoio apoio) {
        Dao<Apoio> apoioDAO = new GenericDAO<>(Apoio.class);
        apoio.setEstadoAtividadeApoio("Desativado");
        apoioDAO.alterar(apoio);
        return "/adicionar html aqui";
    }

    public String excluirApoio(Apoio apoio) {
        Dao<Apoio> apoioDAO = new GenericDAO<>(Apoio.class);
        apoioDAO.excluir(apoio);
        apoios = apoioDAO.buscarTodos(Apoio.class);
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
