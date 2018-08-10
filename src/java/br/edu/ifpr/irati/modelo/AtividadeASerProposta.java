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

@Entity(name = "atividadeaserproposta")
public class AtividadeASerProposta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idAtividadeASerProposta;

    @Column(name = "rotulo", nullable = false, length = 250)
    private String rotulo;

    @Column(name = "periodicidade", nullable = false, length = 20)
    private String periodicidade;

    @OneToMany
    private List<Horario> horariosAtividadesASerProposta;

    public AtividadeASerProposta() {

        this.idAtividadeASerProposta = 0;
        this.periodicidade = "";
        this.rotulo = "";
        this.horariosAtividadesASerProposta = new ArrayList();

    }

    public AtividadeASerProposta(int idAtividadeASerProposta, String rotulo, String periodicidade) {
        this.idAtividadeASerProposta = idAtividadeASerProposta;
        this.rotulo = rotulo;
        this.periodicidade = periodicidade;
        this.horariosAtividadesASerProposta = new ArrayList();
    }

    public AtividadeASerProposta(int idAtividadeASerProposta, String rotulo, String periodicidade, List<Horario> horariosAtividadesASerProposta) {
        this.idAtividadeASerProposta = idAtividadeASerProposta;
        this.rotulo = rotulo;
        this.periodicidade = periodicidade;
        this.horariosAtividadesASerProposta = horariosAtividadesASerProposta;
    }

    public int getIdAtividadeASerProposta() {
        return idAtividadeASerProposta;
    }

    public void setIdAtividadeASerProposta(int idAtividadeASerProposta) {
        this.idAtividadeASerProposta = idAtividadeASerProposta;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public String getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
    }

    public List<Horario> getHorariosAtividadesASerProposta() {
        return horariosAtividadesASerProposta;
    }

    public void setHorariosAtividadesASerProposta(List<Horario> horariosAtividadesASerProposta) {
        this.horariosAtividadesASerProposta = horariosAtividadesASerProposta;
    }
}
