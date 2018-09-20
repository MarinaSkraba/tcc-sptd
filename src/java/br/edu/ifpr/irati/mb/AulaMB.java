/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.Aula;
import br.edu.ifpr.irati.modelo.Curso;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.PTD;
import br.edu.ifpr.irati.modelo.TipoOferta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class AulaMB {

    private Aula aula;
    private Aula aulaSelecionadaParaAula;
    private Aula aulaSelecionadaParaHorario;
    private Horario horario;
    private List<Horario> horarios;
    private Curso cursoSelecionado;
    private TipoOferta tipoOfertaSelecionado;
    private double HorasAulaTotal;
    private int NumeroSemanas;

    public AulaMB() {

        aula = new Aula();
        horario = new Horario();
        aulaSelecionadaParaAula = new Aula();
        aulaSelecionadaParaHorario = new Aula();
        horarios = new ArrayList<>();
        cursoSelecionado = new Curso();
        tipoOfertaSelecionado = new TipoOferta();
        HorasAulaTotal = 0;
        NumeroSemanas = 0;
    }

    public String salvarAula(Serializable idUsuario, PTD ptd) {

        Dao<Aula> aulaDAO = new GenericDAO<>(Aula.class);
        aula.setTipoOferta(tipoOfertaSelecionado);
        aula.setCurso(cursoSelecionado);
        aula.setCargaHorariaTotal(HorasAulaTotal / NumeroSemanas);
        aula.setEstadoAula("Ativo");
        aulaDAO.salvar(aula);
        aula = aulaDAO.buscarTodos(Aula.class).get(aulaDAO.buscarTodos(Aula.class).size() - 1);
        ptd.getAulas().add(aula);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        ptdDAO.alterar(ptd);
        aula = new Aula();
        HorasAulaTotal = 0;
        NumeroSemanas = 0;
        return "CriarCorrigirPTD?faces-redirect=true";

    }

    public String alterarAula() {
        Dao<Aula> aulaDAO = new GenericDAO<>(Aula.class);
        aulaDAO.alterar(aulaSelecionadaParaAula);
        aulaSelecionadaParaAula = new Aula();
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String desabilitarAula(Aula aula) {
        Dao<Aula> aulaDAO = new GenericDAO<>(Aula.class);
        aula.setEstadoAula("Desativado");
        aulaDAO.alterar(aula);
        return "/adicionar html aqui";
    }

    public String excluirAula(Aula aula, PTD ptd) {

        Dao<Aula> aulaDAO = new GenericDAO<>(Aula.class);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);

        for (int i = 0; i <= aula.getHorariosAula().size(); i++) {
            horarioDAO.excluir(horario);
            aula.getHorariosAula().remove(horario);
        }

        ptd.getAulas().remove(aula);
        ptdDAO.alterar(ptd);
        aulaDAO.excluir(aula);

        return "/CriarCorrigirPTD";
    }

    public void adicionarHorarioAula() {
        horarios.add(horario);
        horario = new Horario();
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public Curso getCursoSelecionado() {
        return cursoSelecionado;
    }

    public void setCursoSelecionado(Curso cursoSelecionado) {
        this.cursoSelecionado = cursoSelecionado;
    }

    /**
     * @return the horarios
     */
    public List<Horario> getHorarios() {
        return horarios;
    }

    /**
     * @param horarios the horarios to set
     */
    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    /**
     * @return the horario
     */
    public Horario getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public TipoOferta getTipoOfertaSelecionado() {
        return tipoOfertaSelecionado;
    }

    public void setTipoOfertaSelecionado(TipoOferta tipoOfertaSelecionado) {
        this.tipoOfertaSelecionado = tipoOfertaSelecionado;
    }

    /**
     * @return the aulaSelecionadaParaAula
     */
    public Aula getAulaSelecionadaParaAula() {
        return aulaSelecionadaParaAula;
    }

    /**
     * @param aulaSelecionadaParaAula the aulaSelecionadaParaAula to set
     */
    public void setAulaSelecionadaParaAula(Aula aulaSelecionadaParaAula) {
        this.aulaSelecionadaParaAula = aulaSelecionadaParaAula;
    }

    /**
     * @return the aulaSelecionadaParaHorario
     */
    public Aula getAulaSelecionadaParaHorario() {
        return aulaSelecionadaParaHorario;
    }

    /**
     * @param aulaSelecionadaParaHorario the aulaSelecionadaParaHorario to set
     */
    public void setAulaSelecionadaParaHorario(Aula aulaSelecionadaParaHorario) {
        this.aulaSelecionadaParaHorario = aulaSelecionadaParaHorario;
    }

    /**
     * @return the HorasAulaTotal
     */
    public double getHorasAulaTotal() {
        return HorasAulaTotal;
    }

    /**
     * @param HorasAulaTotal the HorasAulaTotal to set
     */
    public void setHorasAulaTotal(double HorasAulaTotal) {
        this.HorasAulaTotal = HorasAulaTotal;
    }

    /**
     * @return the NumeroSemanas
     */
    public int getNumeroSemanas() {
        return NumeroSemanas;
    }

    /**
     * @param NumeroSemanas the NumeroSemanas to set
     */
    public void setNumeroSemanas(int NumeroSemanas) {
        this.NumeroSemanas = NumeroSemanas;
    }

}
