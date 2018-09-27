/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.Administracao;
import br.edu.ifpr.irati.modelo.Apoio;
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

    private Horario horarioAula;
    private Horario horarioManuEnsino;
    private Horario horarioApoioEnsino;
    private List<Horario> horarios;
    private List<String> diasSemana;

    public HorarioMB() {

        horarioAula = new Horario();
        horarioManuEnsino = new Horario();
        horarioApoioEnsino = new Horario();
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
        horarioAula.setProfessor(p);
        aula.getHorariosAula().add(horarioAula);
        horarioDAO.salvar(aula.getHorariosAula().get(aula.getHorariosAula().size() - 1));
        aulaDAO.alterar(aula);
        horarioAula = new Horario();
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String salvarHorarioAtividade(Object object, Usuario usuario) {

        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        Dao<Professor> professorDAO = new GenericDAO<>(Professor.class);
        Professor p = professorDAO.buscarPorId(usuario.getIdUsuario());

        double cargaHoraNovoHorario = 0;
        double minTotal = 0;

        double minInicio = getHorarioManuEnsino().getHoraInicio().getMinutes();
        double minTermino = getHorarioManuEnsino().getHoraTermino().getMinutes();
        double horaInicio = getHorarioManuEnsino().getHoraInicio().getHours();
        double horaTermino = getHorarioManuEnsino().getHoraTermino().getHours();
        double sessenta = 60;

        cargaHoraNovoHorario = horaTermino - horaInicio;
        if (minTermino > minInicio) {
            minTotal = minTermino - minInicio;
            System.out.println(minTotal / 60);
            cargaHoraNovoHorario = cargaHoraNovoHorario + (minTotal / sessenta);
        }
        if (minTermino < minTermino) {
            minTotal = (60 - minInicio) + minTermino;
            cargaHoraNovoHorario = cargaHoraNovoHorario + (minTotal / 60);
        }

        if (object instanceof ManutencaoEnsino) {
            Dao<ManutencaoEnsino> manutencaoEnsinoDAO = new GenericDAO<>(ManutencaoEnsino.class);
            ((ManutencaoEnsino) object).setCargaHorariaSemanalManutencaoEnsino(((ManutencaoEnsino) object).getCargaHorariaSemanalManutencaoEnsino() + cargaHoraNovoHorario);
            horarioManuEnsino.setProfessor(p);
            ((ManutencaoEnsino) object).getHorariosManutecao().add(getHorarioManuEnsino());
            horarioDAO.salvar(((ManutencaoEnsino) object).getHorariosManutecao().get(((ManutencaoEnsino) object).getHorariosManutecao().size() - 1));
            manutencaoEnsinoDAO.alterar(((ManutencaoEnsino) object));
            setHorarioManuEnsino(new Horario());
        } if (object instanceof Apoio) {
            Dao<Apoio> apoioEnsinoDAO = new GenericDAO<>(ManutencaoEnsino.class);
            ((Apoio) object).setCargaHorariaSemanalApoio(((Apoio) object).getCargaHorariaSemanalApoio() + cargaHoraNovoHorario);
            horarioApoioEnsino.setProfessor(p);
            ((Apoio) object).getHorariosApoio().add(getHorarioManuEnsino());
            horarioDAO.salvar(((ManutencaoEnsino) object).getHorariosManutecao().get(((Apoio) object).getHorariosApoio().size() - 1));
            apoioEnsinoDAO.alterar(((Apoio) object));
            setHorarioManuEnsino(new Horario());
        }

        return "CriarCorrigirPTD?faces-redirect=true";

    }

    public String alterarHorarioAula(List<Horario> horariosSelecionada) {
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        for (Horario h : horariosSelecionada) {
            horarioDAO.alterar(h);
        }
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String alterarHorarioAtividade(List<Horario> horariosSelecionada, Object object) {
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        double cargaHoraNovoHorario = 0;
        double minTotal = 0;

        for (Horario h : horariosSelecionada) {

            double minInicio = h.getHoraInicio().getMinutes();
            double minTermino = h.getHoraTermino().getMinutes();
            double horaInicio = h.getHoraInicio().getHours();
            double horaTermino = h.getHoraTermino().getHours();

            cargaHoraNovoHorario = cargaHoraNovoHorario + (horaTermino - horaInicio);
            if (minTermino > minInicio) {
                minTotal = minTermino - minInicio;
                cargaHoraNovoHorario = cargaHoraNovoHorario + (minTotal / 60);
            }
            if (minTermino < minTermino) {
                minTotal = (60 - minInicio) + minTermino;
                cargaHoraNovoHorario = cargaHoraNovoHorario + (minTotal / 60);
            }
            horarioDAO.alterar(h);
        }

        if (object instanceof ManutencaoEnsino) {
            Dao<ManutencaoEnsino> manutencaoEnsinoDAO = new GenericDAO<>(ManutencaoEnsino.class);
            ((ManutencaoEnsino) object).setCargaHorariaSemanalManutencaoEnsino(cargaHoraNovoHorario);
            manutencaoEnsinoDAO.alterar((ManutencaoEnsino) object);
        }

        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String excluirHorarioAula(Horario horario, Aula aula) {
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        Dao<Aula> aulaDAO = new GenericDAO<>(Aula.class);
        aula.getHorariosAula().remove(horario);
        aulaDAO.alterar(aula);
        horarioDAO.excluir(horario);
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String excluirHorarioAtividade(Horario horario, Object object) {
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);

        double cargaHoraNovoHorario = 0;
        double minTotal = 0;
        double minInicio = horario.getHoraInicio().getMinutes();
        double minTermino = horario.getHoraTermino().getMinutes();
        double horaInicio = horario.getHoraInicio().getHours();
        double horaTermino = horario.getHoraTermino().getHours();

        cargaHoraNovoHorario = cargaHoraNovoHorario + (horaTermino - horaInicio);
        if (minTermino > minInicio) {
            minTotal = minTermino - minInicio;
            cargaHoraNovoHorario = cargaHoraNovoHorario + (minTotal / 60);
        }
        if (minTermino < minTermino) {
            minTotal = (60 - minInicio) + minTermino;
            cargaHoraNovoHorario = cargaHoraNovoHorario + (minTotal / 60);
        }

        if (object instanceof ManutencaoEnsino) {
            Dao<ManutencaoEnsino> manutencaoEnsinoDAO = new GenericDAO<>(ManutencaoEnsino.class);
            ((ManutencaoEnsino) object).setCargaHorariaSemanalManutencaoEnsino(((ManutencaoEnsino) object).getCargaHorariaSemanalManutencaoEnsino() - cargaHoraNovoHorario);
            manutencaoEnsinoDAO.alterar((ManutencaoEnsino) object);
            ((ManutencaoEnsino) object).getHorariosManutecao().remove(horario);
            manutencaoEnsinoDAO.alterar(((ManutencaoEnsino) object));
        }

        horarioDAO.excluir(horario);
        return "CriarCorrigirPTD?faces-redirect=true";
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

    /**
     * @return the horarioAula
     */
    public Horario getHorarioAula() {
        return horarioAula;
    }

    /**
     * @param horarioAula the horarioAula to set
     */
    public void setHorarioAula(Horario horarioAula) {
        this.horarioAula = horarioAula;
    }

    /**
     * @return the horarioManuEnsino
     */
    public Horario getHorarioManuEnsino() {
        return horarioManuEnsino;
    }

    /**
     * @param horarioManuEnsino the horarioManuEnsino to set
     */
    public void setHorarioManuEnsino(Horario horarioManuEnsino) {
        this.horarioManuEnsino = horarioManuEnsino;
    }

    /**
     * @return the horarioApoioEnsino
     */
    public Horario getHorarioApoioEnsino() {
        return horarioApoioEnsino;
    }

    /**
     * @param horarioApoioEnsino the horarioApoioEnsino to set
     */
    public void setHorarioApoioEnsino(Horario horarioApoioEnsino) {
        this.horarioApoioEnsino = horarioApoioEnsino;
    }

}
