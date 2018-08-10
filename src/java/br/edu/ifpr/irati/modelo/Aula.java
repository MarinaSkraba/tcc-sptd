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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "aula")
public class Aula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idAula;

    @Column(name = "componenteCurricular", nullable = false, length = 50)
    private String componenteCurricular;

    @OneToMany(fetch=FetchType.EAGER)
    private List<Curso> cursos;

    @OneToOne
    private TipoOferta tipoOferta;

    @OneToMany(fetch=FetchType.EAGER)
    private List<Horario> horariosAula;

    public Aula() {
        this.idAula = 0;
        this.componenteCurricular = "";
        this.cursos = new ArrayList();
        this.tipoOferta = new TipoOferta();
        this.horariosAula = new ArrayList<>();

    }

    public Aula(int idAula, String componenteCurricular) {
        this.idAula = idAula;
        this.componenteCurricular = componenteCurricular;
        this.cursos = new ArrayList();
        this.tipoOferta = new TipoOferta();
        this.horariosAula = new ArrayList<>();

    }

    public Aula(int idAula, String componenteCurricular, List<Curso> cursos, TipoOferta tipoOferta, List<Horario> horariosAula) {
        this.idAula = idAula;
        this.componenteCurricular = componenteCurricular;
        this.cursos = cursos;
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

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

}
