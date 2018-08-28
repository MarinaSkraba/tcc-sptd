/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.Administracao;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.TipoAdministracao;
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
        Dao<Administracao> administracaoDAO = new GenericDAO<>(Administracao.class);
        administracoes = administracaoDAO.buscarTodos(Administracao.class);

    }

    public void salvarAdministracao() {
        Dao<Administracao> administracaoDAO = new GenericDAO<>(Administracao.class);
        Dao<TipoAdministracao> tipoAdministracaoDAO = new GenericDAO<>(TipoAdministracao.class);
        tipoAdministracaoDAO.salvar(tipoAdministracao);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        horarioDAO.salvar(horario);
        administracao.setTipoAdministracao(tipoAdministracao);
        administracao.getHorariosAdministracao().add(horario);
        administracao.setEstadoAtividadeAdministracao("Ativo");
        administracaoDAO.salvar(administracao);
        administracoes = administracaoDAO.buscarTodos(Administracao.class);
    }

    public String alterarAdministracao(Administracao administracao) {
        this.administracao = administracao;
        return "/adicionar html aqui";
    }

    public String desabilitarAdministracao(Administracao administracao) {
        Dao<Administracao> administracaoDAO = new GenericDAO<>(Administracao.class);
        administracao.setEstadoAtividadeAdministracao("Desativado");
        administracaoDAO.alterar(administracao);
        return "/adicionar html aqui";
    }

    public String excluirAdministracao(Administracao administracao) {
        Dao<Administracao> administracaoDAO = new GenericDAO<>(Administracao.class);
        administracaoDAO.excluir(administracao);
        administracoes = administracaoDAO.buscarTodos(Administracao.class);
        return "/adicionar aqui";
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
