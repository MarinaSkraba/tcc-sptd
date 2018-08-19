/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.ManutencaoEnsino;
import br.edu.ifpr.irati.modelo.TipoManutencao;
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
        Dao<ManutencaoEnsino> manutencaoEnsinoDAO = new GenericDAO<>(ManutencaoEnsino.class);
        manutencoesEnsino = manutencaoEnsinoDAO.buscarTodos(ManutencaoEnsino.class);
    }

    public void salvar() {

        Dao<ManutencaoEnsino> manutencaoEnsinoDAO = new GenericDAO<>(ManutencaoEnsino.class);
        Dao<TipoManutencao> tipoManutencaoDAO = new GenericDAO<>(TipoManutencao.class);
        tipoManutencaoDAO.salvar(tipoManutencao);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        horarioDAO.salvar(horario);
        manutencaoEnsino.setTipoManutencao(tipoManutencao);
        manutencaoEnsino.getHorariosManutecao().add(horario);
        manutencaoEnsino.setEstadoManutencaoEnsino("Ativo");
        ManutencaoEnsino mE = new ManutencaoEnsino(manutencaoEnsino.getIdManutencao(), manutencaoEnsino.getEstadoManutencaoEnsino(), manutencaoEnsino.getTipoManutencao(), manutencaoEnsino.getHorariosManutecao());
        manutencaoEnsinoDAO.salvar(mE);
        manutencoesEnsino = manutencaoEnsinoDAO.buscarTodos(ManutencaoEnsino.class);

    }

    public String alterar(ManutencaoEnsino manutencaoEnsino) {
        this.manutencaoEnsino = manutencaoEnsino;
        return "/adicionar aqui";
    }

    public String desabilitar(ManutencaoEnsino manutencaoEnsino) {
        Dao<ManutencaoEnsino> manutencaoEnsinoDAO = new GenericDAO<>(ManutencaoEnsino.class);
        manutencaoEnsino.setEstadoManutencaoEnsino("Desativado");
        manutencaoEnsinoDAO.alterar(manutencaoEnsino);
        return "/adicionar html aqui";
    }

    public String excluir(ManutencaoEnsino manutencaoEnsino) {
        Dao<ManutencaoEnsino> manutencaoEnsinoDAO = new GenericDAO<>(ManutencaoEnsino.class);
        manutencaoEnsinoDAO.excluir(manutencaoEnsino);
        manutencoesEnsino = manutencaoEnsinoDAO.buscarTodos(ManutencaoEnsino.class);
        return "/adicionar aqui";
    }

    public void adicionarHorario() {
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
