package br.edu.ifpr.irati.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

    @ManyToOne
    @JoinColumn(name = "projetopesquisaextensao_idProjetoPesquisaExtensao")
    private List<ProjetoPesquisaExtensao> projetosPesquisaExtensao;

    @OneToMany
    private List<Horario> horariosParticipacao;

    public Participacao() {

        this.idParticipacao = 0;
        this.estadoParticipacao = "";
        this.professor = new Professor();
        this.horariosParticipacao = new ArrayList();
        this.projetosPesquisaExtensao = new ArrayList();

    }

    public Participacao(int idParticipacao, String rotulo, String estadoParticipacao) {
        this.idParticipacao = idParticipacao;
        this.rotulo = rotulo;
        this.estadoParticipacao = estadoParticipacao;
        this.professor = new Professor();
        this.horariosParticipacao = new ArrayList();
        this.projetosPesquisaExtensao = new ArrayList();
    }

    public Participacao(int idParticipacao, String rotulo, String estadoParticipacao, Professor professor, List<ProjetoPesquisaExtensao> projetosPesquisaExtensao, List<Horario> horariosParticipacao) {
        this.idParticipacao = idParticipacao;
        this.rotulo = rotulo;
        this.estadoParticipacao = estadoParticipacao;
        this.professor = professor;
        this.projetosPesquisaExtensao = projetosPesquisaExtensao;
        this.horariosParticipacao = horariosParticipacao;
    }

    public void adicionarHorario(Horario horario) {
        this.getHorariosParticipacao().add(horario);
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

    public List<Horario> getHorariosParticipacao() {
        return horariosParticipacao;
    }

    public void setHorariosParticipacao(List<Horario> horariosParticipacao) {
        this.horariosParticipacao = horariosParticipacao;
    }

    public List<ProjetoPesquisaExtensao> getProjetosPesquisaExtensao() {
        return projetosPesquisaExtensao;
    }

    public void setProjetosPesquisaExtensao(List<ProjetoPesquisaExtensao> projetosPesquisaExtensao) {
        this.projetosPesquisaExtensao = projetosPesquisaExtensao;
    }

}
