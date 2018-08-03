
package br.edu.ifpr.irati.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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

    @OneToOne
    private TipoAdministracao tipoAdministracao;

    @OneToMany
    private List<Horario> horariosAdministracao;

    public Administracao() {

        this.idAdministracao = 0;
        this.tipoAdministracao = new TipoAdministracao();
        this.horariosAdministracao = new ArrayList<>();
        
    }

    public Administracao(int idAdministracao, List<Horario> horarios, TipoAdministracao tipoAdministracao) {
        this.idAdministracao = idAdministracao;
        this.tipoAdministracao = tipoAdministracao;
        this.horariosAdministracao = horarios;
    }

    public int getIdAdministracao() {
        return idAdministracao;
    }

    public void setIdAdministracao(int idAdministracao) {
        this.idAdministracao = idAdministracao;
    }

    public List<Horario> getHorarios() {
        return horariosAdministracao;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horariosAdministracao = horarios;
    }

    public TipoAdministracao getTipoAdministracao() {
        return tipoAdministracao;
    }

    public void setTipoAdministracao(TipoAdministracao tipoAdministracao) {
        this.tipoAdministracao = tipoAdministracao;
    }

}
