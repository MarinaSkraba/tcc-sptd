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

    @Column(name = "estadoAula", nullable = false, length = 10)
    private String estadoAula;

    @Column(name = "horasAulaTotal", nullable = false, length = 50)
    private double horasAulaTotal;

    @Column(name = "numeroSemanas", nullable = false, length = 50)
    private int numeroSemanas;

    @ManyToOne
    @JoinColumn(name = "curso_idCurso")
    private Curso curso;

    @OneToOne
    private TipoOferta tipoOferta;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Horario> horariosAula;

    public Aula() {
        this.idAula = 0;
        this.estadoAula = "";
        this.componenteCurricular = "";
        this.horasAulaTotal = 0;
        this.numeroSemanas = 0;
        this.curso = new Curso();
        this.tipoOferta = new TipoOferta();
        this.horariosAula = new ArrayList<>();

    }

    public Aula(int idAula, String componenteCurricular, String estadoAula, double horasAulaTotal, int numeroSemanas) {
        this.idAula = idAula;
        this.componenteCurricular = componenteCurricular;
        this.estadoAula = estadoAula;
        this.horasAulaTotal = horasAulaTotal;
        this.numeroSemanas = numeroSemanas;
        this.curso = new Curso();
        this.tipoOferta = new TipoOferta();
        this.horariosAula = new ArrayList();
    }

    public Aula(int idAula, String componenteCurricular, String estadoAula, double horasAulaTotal, int numeroSemanas, Curso curso, TipoOferta tipoOferta, List<Horario> horariosAula) {
        this.idAula = idAula;
        this.componenteCurricular = componenteCurricular;
        this.estadoAula = estadoAula;
        this.horasAulaTotal = horasAulaTotal;
        this.numeroSemanas = numeroSemanas;
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

    public String getEstadoAula() {
        return estadoAula;
    }

    public void setEstadoAula(String estadoAula) {
        this.estadoAula = estadoAula;
    }

    public double getHorasAulaTotal() {
        return horasAulaTotal;
    }

    public void setHorasAulaTotal(double horasAulaTotal) {
        this.horasAulaTotal = horasAulaTotal;
    }

    public int getNumeroSemanas() {
        return numeroSemanas;
    }

    public void setNumeroSemanas(int numeroSemanas) {
        this.numeroSemanas = numeroSemanas;
    }

}
