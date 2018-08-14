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

/**
 *
 * @author Marina
 */
@Entity(name = "participacao")
public class Participacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idParticipacao;

    @Column(name = "rotulo", nullable = false, length = 250)
    private String rotulo;

    @ManyToOne
    @JoinColumn(name = "professor_idProfessor")
    private Professor professor;

    public Participacao() {

        this.idParticipacao = 0;
        this.professor = new Professor();

    }

    public Participacao(int idParticipacao, String rotulo) {
        this.idParticipacao = idParticipacao;
        this.rotulo = rotulo;
        this.professor = new Professor();

    }

    public Participacao(int idParticipacao, String rotulo, ProjetoExtensao projetoExtensao, ProjetoPesquisa projetoPesquisa, Professor professor) {
        this.idParticipacao = idParticipacao;
        this.rotulo = rotulo;
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

}
