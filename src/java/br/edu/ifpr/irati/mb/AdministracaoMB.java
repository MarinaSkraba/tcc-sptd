/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.AdministracaoDAO;
import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.dao.IAdministracaoDao;
import br.edu.ifpr.irati.modelo.Administracao;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.PTD;
import br.edu.ifpr.irati.modelo.TipoAdministracao;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class AdministracaoMB {

    private Administracao administracao;
    private List<Administracao> administracoes;
    private Horario horario;
    private List<Horario> horarios;
    private TipoAdministracao tipoAdministracao;

    public AdministracaoMB() {

        horario = new Horario();
        tipoAdministracao = new TipoAdministracao();
        administracao = new Administracao();
        administracoes = new ArrayList();

    }

    public String salvarAdministracao(Serializable idUsuario, PTD ptd) {
        Dao<Administracao> administracaoDAO = new GenericDAO<>(Administracao.class);
        Dao<TipoAdministracao> tipoAdministracaoDAO = new GenericDAO<>(TipoAdministracao.class);
        tipoAdministracaoDAO.salvar(tipoAdministracao);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        horarioDAO.salvar(horario);
        administracao.setTipoAdministracao(tipoAdministracao);
        administracao.getHorariosAdministracao().add(horario);
        administracao.setEstadoAtividadeAdministracao("Ativo");
        administracaoDAO.salvar(administracao);
        administracao = administracaoDAO.buscarTodos(Administracao.class).get(administracaoDAO.buscarTodos(Administracao.class).size()-1);
        ptd.getAdministrativas().add(administracao);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        ptdDAO.alterar(ptd);
        administracao = new Administracao();
        return "CriarCorrigirPTD";
       
    }

    public String alterarAdministracao(Administracao administracao) {
        Dao<Administracao> administracaoDAO = new GenericDAO<>(Administracao.class);
        this.administracao = administracao;
        administracaoDAO.alterar(administracao);
        return "/adicionar html aqui";
    }

    public String desabilitarAdministracao(Administracao administracao) {
        Dao<Administracao> administracaoDAO = new GenericDAO<>(Administracao.class);
        administracao.setEstadoAtividadeAdministracao("Desativado");
        administracaoDAO.alterar(administracao);
        return "/adicionar html aqui";
    }

    public String excluirAdministracao(Administracao administracao, PTD ptd) {
     
        Dao<Administracao> administracaoDAO = new GenericDAO<>(Administracao.class);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);

        for (int i = 0; i <= administracao.getHorariosAdministracao().size(); i++) {
            horarioDAO.excluir(horario);
            administracao.getHorariosAdministracao().remove(horario);
        }

        ptd.getAdministrativas().remove(administracao);
        ptdDAO.alterar(ptd);
        administracaoDAO.excluir(administracao);

        return "/adicionar aqui";
    }

    public Date calcularCargaHorariaAdministracao() {
        int minTotal = 0;
        int horaTotal = 0;
        for (Administracao adm : administracoes) {
            for (Horario h : horarios) {
                int minInicio = h.getHoraInicio().getMinutes();
                int minTermino = h.getHoraTermino().getMinutes();
                int horaInicio = h.getHoraInicio().getHours();
                int horaTermino = h.getHoraTermino().getHours();

                minTotal = minTotal + (minTermino - minInicio);
                horaTotal = horaTotal + (horaTermino - horaInicio);
                
                int minConvertidos = 0;
                // while
                
            }
        }

        Date cargaHorariaAdministracao = new Time(horaTotal, minTotal, 0);
        return cargaHorariaAdministracao;

    }

    public void adicionarHorarioAdministracao() {
        horarios.add(horario);
        horario = new Horario();
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public TipoAdministracao getTipoAdministracao() {
        return tipoAdministracao;
    }

    public void setTipoAdministracao(TipoAdministracao tipoAdministracao) {
        this.tipoAdministracao = tipoAdministracao;
    }

    public Administracao getAdministracao() {
        return administracao;
    }

    public void setAdministracao(Administracao administracao) {
        this.administracao = administracao;
    }

    public List<Administracao> getAdministracoes() {
        return administracoes;
    }

    public void setAdministracoes(List<Administracao> administracoes) {
        this.administracoes = administracoes;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

}
