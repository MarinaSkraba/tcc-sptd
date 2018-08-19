/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Marina
 */
@Entity(name = "projetopesquisa")
public class ProjetoPesquisa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idProjetoPesquisa;

    @Column(name = "numeroProcesso", nullable = false, length = 20)
    private String numeroProcesso;

    @Column(name = "tituloProcesso", nullable = false, length = 300)
    private String tituloProcesso;

    @Temporal(TemporalType.DATE)
    private Date previsaoConclusao;

    @Column(name = "instituicaoPesquisa", nullable = false, length = 150)
    private String instituicaoPesquisa;

    @Column(name = "estadoProjetoPesquisa", nullable = false, length = 10)
    private String estadoProjetoPesquisa;

    @OneToMany
    private List<Horario> horariosProjetoPesquisa;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Participacao> participacoes;

    public ProjetoPesquisa() {
        this.idProjetoPesquisa = 0;
        this.estadoProjetoPesquisa = "";
        this.numeroProcesso = "";
        this.tituloProcesso = "";
        this.previsaoConclusao = new Date();
        this.instituicaoPesquisa = "";
    }

    public ProjetoPesquisa(int idProjetoPesquisa, String numeroProcesso, String tituloProcesso, String instituicaoPesquisa, String estadoProjetoPesquisa) {
        this.idProjetoPesquisa = idProjetoPesquisa;
        this.numeroProcesso = numeroProcesso;
        this.tituloProcesso = tituloProcesso;
        this.previsaoConclusao = new Date();
        this.instituicaoPesquisa = instituicaoPesquisa;
        this.estadoProjetoPesquisa = estadoProjetoPesquisa;
        this.horariosProjetoPesquisa = new ArrayList();
        this.participacoes = new ArrayList();
    }

    public ProjetoPesquisa(int idProjetoPesquisa, String numeroProcesso, String tituloProcesso, Date previsaoConclusao, String instituicaoPesquisa, String estadoProjetoPesquisa, List<Horario> horariosProjetoPesquisa, List<Participacao> participacoes) {
        this.idProjetoPesquisa = idProjetoPesquisa;
        this.numeroProcesso = numeroProcesso;
        this.tituloProcesso = tituloProcesso;
        this.previsaoConclusao = previsaoConclusao;
        this.instituicaoPesquisa = instituicaoPesquisa;
        this.estadoProjetoPesquisa = estadoProjetoPesquisa;
        this.horariosProjetoPesquisa = horariosProjetoPesquisa;
        this.participacoes = participacoes;
    }

    public int getIdProjetoPesquisa() {
        return idProjetoPesquisa;
    }

    public void setIdProjetoPesquisa(int idProjetoPesquisa) {
        this.idProjetoPesquisa = idProjetoPesquisa;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getTituloProcesso() {
        return tituloProcesso;
    }

    public void setTituloProcesso(String tituloProcesso) {
        this.tituloProcesso = tituloProcesso;
    }

    public Date getPrevisaoConclusao() {
        return previsaoConclusao;
    }

    public void setPrevisaoConclusao(Date previsaoConclusao) {
        this.previsaoConclusao = previsaoConclusao;
    }

    public String getInstituicaoPesquisa() {
        return instituicaoPesquisa;
    }

    public void setInstituicaoPesquisa(String instituicaoPesquisa) {
        this.instituicaoPesquisa = instituicaoPesquisa;
    }

    public List<Horario> getHorariosProjetoPesquisa() {
        return horariosProjetoPesquisa;
    }

    public void setHorariosProjetoPesquisa(List<Horario> horariosProjetoPesquisa) {
        this.horariosProjetoPesquisa = horariosProjetoPesquisa;
    }

    public List<Participacao> getParticipacoes() {
        return participacoes;
    }

    public void setParticipacoes(List<Participacao> participacoes) {
        this.participacoes = participacoes;
    }

    @Override
    public String toString() {
        return tituloProcesso;
    }

    @Override
    public boolean equals(Object obj) {
        ProjetoPesquisa pP = (ProjetoPesquisa) obj;
        if (this.idProjetoPesquisa == pP.getIdProjetoPesquisa()) {
            return true;
        } else {
            return false;
        }
    }

    public String getEstadoProjetoPesquisa() {
        return estadoProjetoPesquisa;
    }

    public void setEstadoProjetoPesquisa(String estadoProjetoPesquisa) {
        this.estadoProjetoPesquisa = estadoProjetoPesquisa;
    }
}
