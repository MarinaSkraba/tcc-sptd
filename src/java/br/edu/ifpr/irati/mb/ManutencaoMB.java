/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.ManutencaoEnsino;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ManutencaoMB {

    private ManutencaoEnsino manutencaoEnsino;
    private List<ManutencaoEnsino> manutencoesEnsino;

    public ManutencaoMB() {

        manutencaoEnsino = new ManutencaoEnsino();
        Dao<ManutencaoEnsino> manutencaoEnsinoDAO = new GenericDAO<>(ManutencaoEnsino.class);
        manutencoesEnsino = manutencaoEnsinoDAO.buscarTodos(ManutencaoEnsino.class);
    }

    public void salvar() {
        Dao<ManutencaoEnsino> manutencaoEnsinoDAO = new GenericDAO<>(ManutencaoEnsino.class);
        manutencaoEnsinoDAO.salvar(manutencaoEnsino);
        manutencaoEnsino = new ManutencaoEnsino();
        manutencoesEnsino = manutencaoEnsinoDAO.buscarTodos(ManutencaoEnsino.class);

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
}
