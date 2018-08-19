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

@Entity(name = "administracao")
public class Administracao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idAdministracao;

    @Column(name = "estadoAtividadeAdministracao", nullable = false, length = 10)
    private String estadoAtividadeAdministracao;

    @OneToOne
    private TipoAdministracao tipoAdministracao;

    @OneToMany
    private List<Horario> horariosAdministracao;

    public Administracao() {

        this.idAdministracao = 0;
        this.estadoAtividadeAdministracao = "";
        this.tipoAdministracao = new TipoAdministracao();
        this.horariosAdministracao = new ArrayList<>();

    }

    public Administracao(int idAdministracao, String estadoAtividadeAdministracao) {
        this.idAdministracao = idAdministracao;
        this.estadoAtividadeAdministracao = estadoAtividadeAdministracao;
        this.tipoAdministracao = new TipoAdministracao();
        this.horariosAdministracao = new ArrayList();
    }

    public Administracao(int idAdministracao, String estadoAtividadeAdministracao, TipoAdministracao tipoAdministracao, List<Horario> horariosAdministracao) {
        this.idAdministracao = idAdministracao;
        this.estadoAtividadeAdministracao = estadoAtividadeAdministracao;
        this.tipoAdministracao = tipoAdministracao;
        this.horariosAdministracao = horariosAdministracao;
    }

    public int getIdAdministracao() {
        return idAdministracao;
    }

    public void setIdAdministracao(int idAdministracao) {
        this.idAdministracao = idAdministracao;
    }

    public void setTipoAdministracao(TipoAdministracao tipoAdministracao) {
        this.tipoAdministracao = tipoAdministracao;
    }

    public TipoAdministracao getTipoAdministracao() {
        return tipoAdministracao;
    }

    public List<Horario> getHorariosAdministracao() {
        return horariosAdministracao;
    }

    public void setHorariosAdministracao(List<Horario> horariosAdministracao) {
        this.horariosAdministracao = horariosAdministracao;
    }

    public String getEstadoAtividadeAdministracao() {
        return estadoAtividadeAdministracao;
    }

    public void setEstadoAtividadeAdministracao(String estadoAtividadeAdministracao) {
        this.estadoAtividadeAdministracao = estadoAtividadeAdministracao;
    }

}
