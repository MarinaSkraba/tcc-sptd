package br.edu.ifpr.irati.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "PTDSubmetido")
@PrimaryKeyJoinColumn(name = "idPTD")
public class PTDSubmetido extends PTD implements Serializable {

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Temporal(TemporalType.DATE)
    private Date dataAvaliacao;

    @ManyToOne
    @JoinColumn(name = "diretorEnsino_idUsuario")
    private DiretorEnsino diretorEnsino;

    public PTDSubmetido() {
        super();
        estado = "";
        dataAvaliacao = new Date();
        diretorEnsino = new DiretorEnsino();
    }

    public PTDSubmetido(int idPTDSubmetido, String estado, Date dataAvaliacao) {
        this.estado = estado;
        this.dataAvaliacao = dataAvaliacao;
        this.diretorEnsino = new DiretorEnsino();
    }

    public PTDSubmetido(String estado, Date dataAvaliacao, DiretorEnsino diretorEnsino) {
        this.estado = estado;
        this.dataAvaliacao = dataAvaliacao;
        this.diretorEnsino = diretorEnsino;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public DiretorEnsino getDiretorEnsino() {
        return diretorEnsino;
    }

    public void setDiretorEnsino(DiretorEnsino diretorEnsino) {
        this.diretorEnsino = diretorEnsino;
    }
}
