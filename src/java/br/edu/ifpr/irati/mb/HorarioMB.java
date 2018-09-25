/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.Administracao;
import br.edu.ifpr.irati.modelo.Aula;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.ManutencaoEnsino;
import br.edu.ifpr.irati.modelo.PTD;
import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.modelo.Usuario;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.print.attribute.standard.DateTimeAtCompleted;

@ManagedBean
@SessionScoped
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

    public String salvarHorarioAula(Aula aula, Usuario usuario) {
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        Dao<Aula> aulaDAO = new GenericDAO<>(Aula.class);
        Dao<Professor> professorDAO = new GenericDAO<>(Professor.class);
        Professor p = professorDAO.buscarPorId(usuario.getIdUsuario());
        horario.setProfessor(p);
        horario.setEstadoHorario("Ativo");
        aula.getHorariosAula().add(horario);
        horarioDAO.salvar(aula.getHorariosAula().get(aula.getHorariosAula().size() - 1));
        aulaDAO.alterar(aula);
        horario = new Horario();
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String salvarManutencaoEnsino(ManutencaoEnsino manutencaoEnsino, Usuario usuario) {

        int cargaHoraNovoHorario = 0;
        int minTotal = 0;

        int minInicio = horario.getHoraInicio().getMinutes();
        int minTermino = horario.getHoraTermino().getMinutes();
        int horaInicio = horario.getHoraInicio().getHours();
        int horaTermino = horario.getHoraTermino().getHours();

        cargaHoraNovoHorario = horaTermino - horaInicio;
        if (minTermino > horaTermino) {
            minTotal = minTotal + (minTermino - minInicio);
        } else {
            minTotal = (60 - minInicio) + minTermino;
        }
        cargaHoraNovoHorario = cargaHoraNovoHorario + (minTotal/60);
        
        manutencaoEnsino.setCargaHorariaSemanalManutencaoEnsino(manutencaoEnsino.getCargaHorariaSemanalManutencaoEnsino() + cargaHoraNovoHorario);

        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        Dao<ManutencaoEnsino> manutencaoEnsinoDAO = new GenericDAO<>(ManutencaoEnsino.class);
        Dao<Professor> professorDAO = new GenericDAO<>(Professor.class);
        Professor p = professorDAO.buscarPorId(usuario.getIdUsuario());
        horario.setProfessor(p);
        horario.setEstadoHorario("Ativo");
        manutencaoEnsino.getHorariosManutecao().add(horario);
        horarioDAO.salvar(manutencaoEnsino.getHorariosManutecao().get(manutencaoEnsino.getHorariosManutecao().size() - 1));
        manutencaoEnsinoDAO.alterar(manutencaoEnsino);
        horario = new Horario();
        return "CriarCorrigirPTD?faces-redirect=true";

    }

    public String alterarHorario(List<Horario> horariosAulaSelecionada) {
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        for (Horario h : horariosAulaSelecionada) {
            horarioDAO.alterar(h);
        }
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String excluirHorario(Horario horario, Aula aula) {
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        Dao<Aula> aulaDAO = new GenericDAO<>(Aula.class);
        aula.getHorariosAula().remove(horario);
        aulaDAO.alterar(aula);
        horarioDAO.excluir(horario);
        return "CriarCorrigirPTD?faces-redirect=true";
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
