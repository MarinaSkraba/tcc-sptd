package br.edu.ifpr.irati.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "aula")
public class Aula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idAula;

    @Column(name = "componenteCurricular", nullable = false, length = 50)
    private String componenteCurricular;
    
    @ManyToOne
    @JoinColumn(name = "curso_idCurso")
    private Curso curso;

    @OneToOne
    private TipoOferta tipoOferta;

    @OneToMany(fetch=FetchType.EAGER)
    private List<Horario> horariosAula;

    public Aula() {
        this.idAula = 0;
        this.componenteCurricular = "";
        this.curso = new Curso();
        this.tipoOferta = new TipoOferta();
        this.horariosAula = new ArrayList<>();

    }

    public Aula(int idAula, String componenteCurricular) {
        this.idAula = idAula;
        this.componenteCurricular = componenteCurricular;
        this.curso = new Curso();
        this.tipoOferta = new TipoOferta();
        this.horariosAula = new ArrayList<>();

    }

    public Aula(int idAula, String componenteCurricular, Curso curso, TipoOferta tipoOferta, List<Horario> horariosAula) {
        this.idAula = idAula;
        this.componenteCurricular = componenteCurricular;
        this.curso = curso;
        this.tipoOferta = tipoOferta;
        this.horariosAula = horariosAula;
    }

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public String getComponenteCurricular() {
        return componenteCurricular;
    }

    public void setComponenteCurricular(String componenteCurricular) {
        this.componenteCurricular = componenteCurricular;
    }

    public List<Horario> getHorarios() {
        return horariosAula;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horariosAula = horarios;
    }

    public TipoOferta getTipoOferta() {
        return tipoOferta;
    }

    public void setTipoOferta(TipoOferta tipoOferta) {
        this.tipoOferta = tipoOferta;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Horario> getHorariosAula() {
        return horariosAula;
    }

    public void setHorariosAula(List<Horario> horariosAula) {
        this.horariosAula = horariosAula;
    }

}