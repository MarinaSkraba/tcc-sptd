package br.edu.ifpr.irati.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "outrotipoatividade")
public class OutroTipoAtividade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idOutroTipoAtividade;

    @Column(name = "estadoOutroTipoAtividade", nullable = false, length = 10)
    private String estadoOutroTipoAtividade;

    @Column(name = "rotulo", nullable = false, length = 250)
    private String rotulo;

    @Column(name = "periodicidade", nullable = false, length = 20)
    private String periodicidade;

    @Column(name = "cargaHorariaSemanalOutroTipoAtividade", nullable = false)
    private double cargaHorariaSemanalOutroTipoAtividade;

    @OneToMany
    private List<Horario> horariosOutroTipoAtividade;

    public OutroTipoAtividade() {
        this.idOutroTipoAtividade = 0;
        this.estadoOutroTipoAtividade = "";
        this.rotulo = "";
        this.periodicidade = "";
        this.cargaHorariaSemanalOutroTipoAtividade = 0;
        this.horariosOutroTipoAtividade = new ArrayList<>();
    }

    public OutroTipoAtividade(int idOutroTipoAtividade, String estadoOutroTipoAtividade, String rotulo, String periodicidade, double cargaHorariaSemanalOutroTipoAtividade) {
        this.idOutroTipoAtividade = idOutroTipoAtividade;
        this.estadoOutroTipoAtividade = estadoOutroTipoAtividade;
        this.rotulo = rotulo;
        this.periodicidade = periodicidade;
        this.cargaHorariaSemanalOutroTipoAtividade = cargaHorariaSemanalOutroTipoAtividade;
        this.horariosOutroTipoAtividade = new ArrayList();
    }

    public OutroTipoAtividade(int idOutroTipoAtividade, String estadoOutroTipoAtividade, String rotulo, String periodicidade, double cargaHorariaSemanalOutroTipoAtividade, List<Horario> horariosOutroTipoAtividade) {
        this.idOutroTipoAtividade = idOutroTipoAtividade;
        this.estadoOutroTipoAtividade = estadoOutroTipoAtividade;
        this.rotulo = rotulo;
        this.periodicidade = periodicidade;
        this.cargaHorariaSemanalOutroTipoAtividade = cargaHorariaSemanalOutroTipoAtividade;
        this.horariosOutroTipoAtividade = horariosOutroTipoAtividade;
    }

    public int getIdOutroTipoAtividade() {
        return idOutroTipoAtividade;
    }

    public void setIdOutroTipoAtividade(int idOutroTipoAtividade) {
        this.idOutroTipoAtividade = idOutroTipoAtividade;
    }

    public String getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public String getEstadoOutroTipoAtividade() {
        return estadoOutroTipoAtividade;
    }

    public void setEstadoOutroTipoAtividade(String estadoOutroTipoAtividade) {
        this.estadoOutroTipoAtividade = estadoOutroTipoAtividade;
    }

    public List<Horario> getHorariosOutroTipoAtividade() {
        return horariosOutroTipoAtividade;
    }

    public void setHorariosOutroTipoAtividade(List<Horario> horariosOutroTipoAtividade) {
        this.horariosOutroTipoAtividade = horariosOutroTipoAtividade;
    }

    public double getCargaHorariaSemanalOutroTipoAtividade() {
        return cargaHorariaSemanalOutroTipoAtividade;
    }

    public void setCargaHorariaSemanalOutroTipoAtividade(double cargaHorariaSemanalOutroTipoAtividade) {
        this.cargaHorariaSemanalOutroTipoAtividade = cargaHorariaSemanalOutroTipoAtividade;
    }

}
