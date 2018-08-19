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

@Entity(name = "apoio")
public class Apoio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idApoio;

    @Column(name = "estadoAtividadeApoio", nullable = false, length = 10)
    private String estadoAtividadeApoio;

    @OneToOne
    private TipoApoio tipoApoio;

    @OneToMany
    private List<Horario> horariosApoio;

    public Apoio() {

        this.idApoio = 0;
        this.estadoAtividadeApoio = "";
        this.tipoApoio = new TipoApoio();
        this.horariosApoio = new ArrayList<>();

    }

    public Apoio(int idApoio, String estadoAtividadeApoio) {
        this.idApoio = idApoio;
        this.estadoAtividadeApoio = estadoAtividadeApoio;
        this.tipoApoio = new TipoApoio();
        this.horariosApoio = new ArrayList();
    }

    public Apoio(int idApoio, String estadoAtividadeApoio, TipoApoio tipoApoio, List<Horario> horariosApoio) {
        this.idApoio = idApoio;
        this.estadoAtividadeApoio = estadoAtividadeApoio;
        this.tipoApoio = tipoApoio;
        this.horariosApoio = horariosApoio;
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

    public String getEstadoAtividadeApoio() {
        return estadoAtividadeApoio;
    }

    public void setEstadoAtividadeApoio(String estadoAtividadeApoio) {
        this.estadoAtividadeApoio = estadoAtividadeApoio;
    }

}
