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
import javax.persistence.OneToOne;

@Entity(name = "manutencao")
public class ManutencaoEnsino implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idManutencao;

    @Column(name = "estadoManutecaoEnsino", nullable = false, length = 10)
    private String estadoManutencaoEnsino;

    @OneToOne
    private TipoManutencao tipoManutencao;

    @OneToMany
    private List<Horario> horariosManutecao;

    public ManutencaoEnsino() {

        this.idManutencao = 0;
        this.estadoManutencaoEnsino = "";
        this.tipoManutencao = new TipoManutencao();
        this.horariosManutecao = new ArrayList<>();

    }

    public ManutencaoEnsino(int idManutencao, String estadoManutencaoEnsino) {
        this.idManutencao = idManutencao;
        this.estadoManutencaoEnsino = estadoManutencaoEnsino;
        this.tipoManutencao = new TipoManutencao();
        this.horariosManutecao = new ArrayList();
    }

    public ManutencaoEnsino(int idManutencao, String estadoManutencaoEnsino, TipoManutencao tipoManutencao, List<Horario> horariosManutecao) {
        this.idManutencao = idManutencao;
        this.estadoManutencaoEnsino = estadoManutencaoEnsino;
        this.tipoManutencao = tipoManutencao;
        this.horariosManutecao = horariosManutecao;
    }

    public int getIdManutencao() {
        return idManutencao;
    }

    public void setIdManutencao(int idManutencao) {
        this.idManutencao = idManutencao;
    }

    public TipoManutencao getTipoManutencao() {
        return tipoManutencao;
    }

    public void setTipoManutencao(TipoManutencao tipoManutencao) {
        this.tipoManutencao = tipoManutencao;
    }

    public List<Horario> getHorariosManutecao() {
        return horariosManutecao;
    }

    public void setHorariosManutecao(List<Horario> horariosManutecao) {
        this.horariosManutecao = horariosManutecao;
    }

    public String getEstadoManutencaoEnsino() {
        return estadoManutencaoEnsino;
    }

    public void setEstadoManutencaoEnsino(String estadoManutencaoEnsino) {
        this.estadoManutencaoEnsino = estadoManutencaoEnsino;
    }
}
