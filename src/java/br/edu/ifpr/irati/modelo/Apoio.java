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

@Entity(name = "apoio")
public class Apoio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idApoio;

    @OneToOne
    private TipoApoio tipoApoio;

    @OneToMany
    private List<Horario> horariosApoio;

    public Apoio() {

        this.idApoio = 0;
        this.tipoApoio = new TipoApoio();
        this.horariosApoio = new ArrayList<>();

    }

    public Apoio(int idApoio, List<Horario> horarios, TipoApoio tipoApoio) {
        this.idApoio = idApoio;
        this.tipoApoio = tipoApoio;
        this.horariosApoio = horarios;
    }

    public int getIdApoio() {
        return idApoio;
    }

    public void setIdApoio(int idApoio) {
        this.idApoio = idApoio;
    }

    public TipoApoio getTipoApoio() {
        return tipoApoio;
    }

    public void setTipoApoio(TipoApoio tipoApoio) {
        this.tipoApoio = tipoApoio;
    }

    public List<Horario> getHorariosApoio() {
        return horariosApoio;
    }

    public void setHorariosApoio(List<Horario> horariosApoio) {
        this.horariosApoio = horariosApoio;
    }

}
