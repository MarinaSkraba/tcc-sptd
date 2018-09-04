/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.AulaDAO;
import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.dao.IAulaDao;
import br.edu.ifpr.irati.modelo.Aula;
import br.edu.ifpr.irati.modelo.Curso;
import br.edu.ifpr.irati.modelo.Horario;
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
    private Aula aulaSelecionada;
    private Horario horario;
    private List<Horario> horarios;
    private Curso cursoSelecionado;
    private TipoOferta tipoOfertaSelecionado;

    public AulaMB() {

        aula = new Aula();
        horario = new Horario();
        aulaSelecionada = new Aula();
        horarios = new ArrayList<>();
        cursoSelecionado = new Curso();
        tipoOfertaSelecionado = new TipoOferta();
    }

    public String salvarAula(Serializable idUsuario) {

        Dao<Aula> aulaDAO = new GenericDAO<>(Aula.class);
        aula.setTipoOferta(tipoOfertaSelecionado);
        aula.setCurso(cursoSelecionado);
        aula.setEstadoAula("Ativo");
        aulaDAO.salvar(aula);
        aula = new Aula();
        return "CriarCorrigirPTD";

    }

    public String alterarAula(Aula aula) {
        Dao<Aula> aulaDAO = new GenericDAO<>(Aula.class);
        this.aula = aula;
        aulaDAO.alterar(aula);
        return "/adicionar aqui";
    }

    public String desabilitarAula(Aula aula) {
        Dao<Aula> aulaDAO = new GenericDAO<>(Aula.class);
        aula.setEstadoAula("Desativado");
        aulaDAO.alterar(aula);
        return "/adicionar html aqui";
    }

    public String excluirAula(Aula aula) {
        Dao<Aula> aulaDAO = new GenericDAO<>(Aula.class);
        aulaDAO.excluir(aula);
        return "/adicionar aqui";
    }

    public double calcularCargaHorariaTotalAula() {
        double cargaHorariaTotalAula = 0;
        cargaHorariaTotalAula = aula.getHorasAulaTotal() / aula.getNumeroSemanas();
        return cargaHorariaTotalAula;

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

    /**
     * @return the aulaSelecionada
     */
    public Aula getAulaSelecionada() {
        return aulaSelecionada;
    }

    /**
     * @param aulaSelecionada the aulaSelecionada to set
     */
    public void setAulaSelecionada(Aula aulaSelecionada) {
        this.aulaSelecionada = aulaSelecionada;
    }

    public TipoOferta getTipoOfertaSelecionado() {
        return tipoOfertaSelecionado;
    }

    public void setTipoOfertaSelecionado(TipoOferta tipoOfertaSelecionado) {
        this.tipoOfertaSelecionado = tipoOfertaSelecionado;
    }

}
