/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.dao.IManutencaoDao;
import br.edu.ifpr.irati.dao.ManutencaoDAO;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.ManutencaoEnsino;
import br.edu.ifpr.irati.modelo.TipoManutencao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ManutencaoMB {

    private ManutencaoEnsino manutencaoEnsino;
    private List<ManutencaoEnsino> manutencoesEnsino;
    private Horario horario;
    private List<Horario> horarios;
    private TipoManutencao tipoManutencao;

    public ManutencaoMB() {

        horario = new Horario();
        tipoManutencao = new TipoManutencao();
        manutencaoEnsino = new ManutencaoEnsino();
        manutencoesEnsino = new ArrayList();
    }

    public void salvarManutencao(Serializable idUsuario) {

        Dao<ManutencaoEnsino> manutencaoEnsinoDAO = new GenericDAO<>(ManutencaoEnsino.class);
        Dao<TipoManutencao> tipoManutencaoDAO = new GenericDAO<>(TipoManutencao.class);
        tipoManutencaoDAO.salvar(tipoManutencao);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        horarioDAO.salvar(horario);
        manutencaoEnsino.setTipoManutencao(tipoManutencao);
        manutencaoEnsino.getHorariosManutecao().add(horario);
        manutencaoEnsino.setEstadoManutencaoEnsino("Ativo");
        manutencaoEnsinoDAO.salvar(manutencaoEnsino);
        IManutencaoDao mDAO = new ManutencaoDAO();
        manutencoesEnsino = mDAO.buscarManutencoesPorProfessor(idUsuario);

    }

    public String alterarManutencao(ManutencaoEnsino manutencaoEnsino) {
        Dao<ManutencaoEnsino> manutencaoEnsinoDAO = new GenericDAO<>(ManutencaoEnsino.class);
        this.manutencaoEnsino = manutencaoEnsino;
        manutencaoEnsinoDAO.alterar(manutencaoEnsino);
        return "/adicionar aqui";
    }

    public String desabilitarManutencao(ManutencaoEnsino manutencaoEnsino) {
        Dao<ManutencaoEnsino> manutencaoEnsinoDAO = new GenericDAO<>(ManutencaoEnsino.class);
        manutencaoEnsino.setEstadoManutencaoEnsino("Desativado");
        manutencaoEnsinoDAO.alterar(manutencaoEnsino);
        return "/adicionar html aqui";
    }

    public String excluirManutencao(ManutencaoEnsino manutencaoEnsino) {
        Dao<ManutencaoEnsino> manutencaoEnsinoDAO = new GenericDAO<>(ManutencaoEnsino.class);
        manutencaoEnsinoDAO.excluir(manutencaoEnsino);
        manutencoesEnsino = manutencaoEnsinoDAO.buscarTodos(ManutencaoEnsino.class);
        return "/adicionar aqui";
    }

    public void adicionarHorarioManutencao() {
        horarios.add(horario);
        horario = new Horario();
    }

    public ManutencaoEnsino getManutencaoEnsino() {
        return manutencaoEnsino;
    }

    public void setManutencaoEnsino(ManutencaoEnsino manutencaoEnsino) {
        this.manutencaoEnsino = manutencaoEnsino;
    }

    public List<ManutencaoEnsino> getManutencoesEnsino() {
        return manutencoesEnsino;
    }

    public void setManutencoesEnsino(List<ManutencaoEnsino> manutencoesEnsino) {
        this.manutencoesEnsino = manutencoesEnsino;
    }

    public TipoManutencao getTipoManutencao() {
        return tipoManutencao;
    }

    public void setTipoManutencao(TipoManutencao tipoManutencao) {
        this.tipoManutencao = tipoManutencao;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }
}
