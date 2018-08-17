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
import br.edu.ifpr.irati.modelo.TipoOferta;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class AulaMB {

    private Aula aula;
    private Horario horario;
    private List<Horario> horarios;
    private Curso cursoSelecionado;
    private TipoOferta tipoOferta;
    private List<Aula> aulas;

    public AulaMB() {

        aula = new Aula();
        horario = new Horario();
        horarios = new ArrayList<>();
        cursoSelecionado = new Curso();
        tipoOferta = new TipoOferta();
        Dao<Aula> aulaDAO = new GenericDAO<>(Aula.class);
        aulas = aulaDAO.buscarTodos(Aula.class);

    }

    public void adicionarHorario() {
        horarios.add(horario);
        horario = new Horario();
    }

    public void salvar() {

        Dao<Aula> aulaDAO = new GenericDAO<>(Aula.class);
        Dao<TipoOferta> tipoOfertaDAO = new GenericDAO<>(TipoOferta.class);
        tipoOfertaDAO.salvar(tipoOferta);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        for (Horario h : horarios) {
            aula.getHorariosAula().add(h);
        }
        for (Horario h : aula.getHorariosAula()) {
            horarioDAO.salvar(h);
        }
        aula.setTipoOferta(tipoOferta);
        aula.setCurso(cursoSelecionado);
        Aula a = new Aula(aula.getIdAula(), aula.getComponenteCurricular(), cursoSelecionado, tipoOferta, aula.getHorariosAula());
        aulaDAO.salvar(a);
        aulas = aulaDAO.buscarTodos(Aula.class);

    }
     public String alterar(Aula aula) {
        this.aula = aula;
        return "/adicionar aqui";
    }

    public String desabilitar(Aula aula) {
        //implementar depois
        return "";
    }
    public String excluir(Aula aula) {
        Dao<Aula> aulaDAO = new GenericDAO<>(Aula.class);
        aulaDAO.excluir(aula);
        aulas = aulaDAO.buscarTodos(Aula.class);
        return "/adicionar aqui";
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    public Curso getCursoSelecionado() {
        return cursoSelecionado;
    }

    public void setCursoSelecionado(Curso cursoSelecionado) {
        this.cursoSelecionado = cursoSelecionado;
    }

    public TipoOferta getTipoOferta() {
        return tipoOferta;
    }

    public void setTipoOferta(TipoOferta tipoOferta) {
        this.tipoOferta = tipoOferta;
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

}
