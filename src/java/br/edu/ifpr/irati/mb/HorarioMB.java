/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.Aula;
import br.edu.ifpr.irati.modelo.Horario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class HorarioMB {

    private Horario horario;
    private List<Horario> horarios;
    private List<String> diasSemana;

    public HorarioMB() {

        horario = new Horario();
        horarios = new ArrayList<>();
        diasSemana = new ArrayList<>();
        diasSemana.add("Segunda");
        diasSemana.add("Terça");
        diasSemana.add("Quarta");
        diasSemana.add("Quinta");
        diasSemana.add("Sexta");
        diasSemana.add("Sábado");
        
    }

    public void salvar(Aula aula) {
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        Dao<Aula> aulaDAO = new GenericDAO<>(Aula.class);
        horario.setEstadoHorario("Ativo");
        aula.getHorariosAula().add(horario);
        horarioDAO.salvar(aula.getHorariosAula().get(aula.getHorariosAula().size() - 1));
        aulaDAO.alterar(aula);
        setHorarios(horarioDAO.buscarTodos(Horario.class));
    }

    public String alterar(Horario horario) {
        this.horario = horario;
        return "/adicionar aqui";
    }

    public String desabilitar(Horario horario) {
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        horario.setEstadoHorario("Desativado");
        horarioDAO.alterar(horario);
        return "/adicionar html aqui";
    }
    public String excluir(Horario horario) {
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        horarioDAO.excluir(horario);
        setHorarios(horarioDAO.buscarTodos(Horario.class));
        return "/adicionar aqui";
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
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
     * @return the diasSemana
     */
    public List<String> getDiasSemana() {
        return diasSemana;
    }

    /**
     * @param diasSemana the diasSemana to set
     */
    public void setDiasSemana(List<String> diasSemana) {
        this.diasSemana = diasSemana;
    }
}
