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

@Entity(name = "projetonsino")
public class ProjetoEnsino implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idProjetoEnsino;

    @OneToOne
    private TipoProjetoEnsino tipoProjetoEnsino;

    @OneToMany
    private List<Horario> horariosProjetoEnsino;

    public ProjetoEnsino() {

        this.idProjetoEnsino = 0;
        this.tipoProjetoEnsino = new TipoProjetoEnsino();
        this.horariosProjetoEnsino = new ArrayList<>();
        

    }

    public ProjetoEnsino(int idProjetoEnsino) {
        this.idProjetoEnsino = idProjetoEnsino;
        this.tipoProjetoEnsino = new TipoProjetoEnsino();
        this.horariosProjetoEnsino = new ArrayList();
        
    }

    public ProjetoEnsino(int idProjetoEnsino, TipoProjetoEnsino tipoProjetoEnsino, List<Horario> horariosProjetoEnsino) {
        this.idProjetoEnsino = idProjetoEnsino;
        this.tipoProjetoEnsino = tipoProjetoEnsino;
        this.horariosProjetoEnsino = horariosProjetoEnsino;
    }

    public int getIdProjetoEnsino() {
        return idProjetoEnsino;
    }

    public void setIdProjetoEnsino(int idProjetoEnsino) {
        this.idProjetoEnsino = idProjetoEnsino;
    }
    
    public TipoProjetoEnsino getTipoProjetoEnsino() {
        return tipoProjetoEnsino;
    }

    public void setTipoProjetoEnsino(TipoProjetoEnsino tipoProjetoEnsino) {
        this.tipoProjetoEnsino = tipoProjetoEnsino;
    }

    public List<Horario> getHorariosProjetoEnsino() {
        return horariosProjetoEnsino;
    }

    public void setHorariosProjetoEnsino(List<Horario> horariosProjetoEnsino) {
        this.horariosProjetoEnsino = horariosProjetoEnsino;
    }

}
