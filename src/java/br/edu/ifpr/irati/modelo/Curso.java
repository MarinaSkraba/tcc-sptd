/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "curso")
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idCurso;

    @Column(name = "nomeCurso", nullable = false, length = 100)
    private String nomeCurso;

    @ManyToOne
    @JoinColumn(name = "diretorEnsino_idDiretorEnsino")
    private DiretorEnsino diretorEnsino;

    public Curso() {
        idCurso = 0;
        nomeCurso = "";
        diretorEnsino = new DiretorEnsino();

    }

    public Curso(int idCurso, String nomeCurso) {
        this.idCurso = idCurso;
        this.nomeCurso = nomeCurso;
        this.diretorEnsino = new DiretorEnsino();

    }

    public Curso(int idCurso, String nomeCurso, DiretorEnsino diretorEnsino) {
        this.idCurso = idCurso;
        this.nomeCurso = nomeCurso;
        this.diretorEnsino = diretorEnsino;
    }

    public int getIdUsuario() {
        return idCurso;
    }

    public void setIdUsuario(int idCurso) {
        this.idCurso = idCurso;
    }

    public DiretorEnsino getDiretorEnsino() {
        return diretorEnsino;
    }

    public void setDiretorEnsino(DiretorEnsino diretorEnsino) {
        this.diretorEnsino = diretorEnsino;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

}
