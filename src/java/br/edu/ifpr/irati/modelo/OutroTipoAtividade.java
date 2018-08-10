/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Marina
 */
@Entity(name = "outrotipoatividade")
public class OutroTipoAtividade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idOutroTipoAtividade;

    @Column(name = "rotulo", nullable = false, length = 250)
    private String rotulo;
    
    @Column(name = "periodicidade", nullable = false, length = 20)
    private String periodicidade;

    @OneToMany
    private List<Horario> horariosOutroTipoAtividade;

    public OutroTipoAtividade() {
        this.idOutroTipoAtividade = 0;
        this.rotulo = "";
        this.periodicidade = "";     
        this.horariosOutroTipoAtividade = new ArrayList<>();
    }

    public OutroTipoAtividade(int idOutroTipoAtividade, String rotulo, String periodicidade) {
        this.idOutroTipoAtividade = idOutroTipoAtividade;
        this.rotulo = rotulo;
        this.periodicidade = periodicidade;
        this.horariosOutroTipoAtividade = new ArrayList();
    }

    public OutroTipoAtividade(int idOutroTipoAtividade, String rotulo, String periodicidade, List<Horario> horariosOutroTipoAtividade) {
        this.idOutroTipoAtividade = idOutroTipoAtividade;
        this.rotulo = rotulo;
        this.periodicidade = periodicidade;
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

    public List<Horario> getHorarios() {
        return horariosOutroTipoAtividade;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horariosOutroTipoAtividade = horarios;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public List<Horario> getHorariosOutroTipoAtividades() {
        return horariosOutroTipoAtividade;
    }

    public void setHorariosOutroTipoAtividades(List<Horario> horariosOutroTipoAtividades) {
        this.horariosOutroTipoAtividade = horariosOutroTipoAtividades;
    }

    public List<Horario> getHorariosOutroTipoAtividade() {
        return horariosOutroTipoAtividade;
    }

    public void setHorariosOutroTipoAtividade(List<Horario> horariosOutroTipoAtividade) {
        this.horariosOutroTipoAtividade = horariosOutroTipoAtividade;
    }
}
