/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "participacao")
public class Participacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idParticipacao;

    @Column(name = "rotulo", nullable = false, length = 250)
    private String rotulo;

    @Column(name = "estadoParticipacao", nullable = false, length = 10)
    private String estadoParticipacao;

    @ManyToOne
    @JoinColumn(name = "professor_idUsuario")
    private Professor professor;

    public Participacao() {

        this.idParticipacao = 0;
        this.estadoParticipacao = "";
        this.professor = new Professor();

    }

    public Participacao(int idParticipacao, String rotulo, String estadoParticipacao) {
        this.idParticipacao = idParticipacao;
        this.rotulo = rotulo;
        this.estadoParticipacao = estadoParticipacao;
        this.professor = new Professor();
    }

    public Participacao(int idParticipacao, String rotulo, String estadoParticipacao, Professor professor) {
        this.idParticipacao = idParticipacao;
        this.rotulo = rotulo;
        this.estadoParticipacao = estadoParticipacao;
        this.professor = professor;
    }

    public int getIdParticipacao() {
        return idParticipacao;
    }

    public void setIdParticipacao(int idParticipacao) {
        this.idParticipacao = idParticipacao;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getEstadoParticipacao() {
        return estadoParticipacao;
    }

    public void setEstadoParticipacao(String estadoParticipacao) {
        this.estadoParticipacao = estadoParticipacao;
    }

}
