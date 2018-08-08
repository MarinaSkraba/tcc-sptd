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
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class AulaMB {

    private Aula aula;
    private Horario horario;
    private Curso cursoSelecionado;
    private TipoOferta tipoOferta;
    private List<Aula> aulas;

    public AulaMB() {

        aula = new Aula();
        Dao<Aula> aulaDAO = new GenericDAO<>(Aula.class);
        aulas = aulaDAO.buscarTodos(Aula.class);

    }

    public void salvar() {
   
        Dao<Aula> aulaDAO = new GenericDAO<>(Aula.class);
        aula.getCursos().add(cursoSelecionado);
        aula.getHorarios().add(horario);
        aula.setTipoOferta(tipoOferta);
        Aula a = new Aula(aula.getIdAula(), aula.getComponenteCurricular(), aula.getCursos(), tipoOferta, aula.getHorarios());
        aulaDAO.salvar(a);
        aulas = aulaDAO.buscarTodos(Aula.class);

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

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public TipoOferta getTipoOferta() {
        return tipoOferta;
    }

    public void setTipoOferta(TipoOferta tipoOferta) {
        this.tipoOferta = tipoOferta;
    }

}
