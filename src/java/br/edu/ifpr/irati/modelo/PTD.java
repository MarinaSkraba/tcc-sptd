package br.edu.ifpr.irati.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "PTD")
@Inheritance(strategy = InheritanceType.JOINED)
public class PTD implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idPTD;

    @Column(name = "campoJustificativaAtividadeEnsino", nullable = true, length = 500)
    private String campoJustificativaAtividadeEnsino;

    @Column(name = "campoJustificativaManutencaoEnsino", nullable = true, length = 500)
    private String campoJustificativaManutencaoEnsino;

    @Column(name = "campoJustificativaApoioEnsino", nullable = true, length = 500)
    private String campoJustificativaApoioEnsino;

    @Column(name = "campoJustificativaAdministracao", nullable = true, length = 500)
    private String campoJustificativaAdministracao;

    @Column(name = "campoJustificativaProjetoEnsino", nullable = true, length = 500)
    private String campoJustificativaProjetoEnsino;

    @Column(name = "campoJustificativaSeremPropostas", nullable = true, length = 500)
    private String campoJustificativaSeremPropostas;

    @Column(name = "campoJustificativaOutrasAtividades", nullable = true, length = 500)
    private String campoJustificativaOutrasAtividades;

    @Column(name = "campoJustificativaProjetoPesquisa", nullable = true, length = 500)
    private String campoJustificativaProjetoPesquisa;

    @Column(name = "campoJustificativaProjetoExtensao", nullable = true, length = 500)
    private String campoJustificativaProjetoExtensao;

    @ManyToOne
    @JoinColumn(name = "professor_idUsuario")
    private Professor professor;

    @OneToMany
    private List<Aula> aulas;

    @OneToMany
    private List<Apoio> apoios;

    @OneToMany
    private List<ManutencaoEnsino> manutencoesEnsino;

    @OneToMany
    private List<Administracao> administrativas;

    @OneToMany
    private List<ProjetoEnsino> projetosEnsino;

    @OneToMany
    private List<ProjetoExtensao> projetosExtensao;

    @OneToMany
    private List<ProjetoPesquisa> projetosPesquisa;

    @OneToMany
    private List<AtividadeASerProposta> atividadesASeremPropostas;

    @OneToMany
    private List<OutroTipoAtividade> outrosTiposAtividades;

    public PTD() {

        campoJustificativaAtividadeEnsino = "";
        campoJustificativaManutencaoEnsino = "";
        campoJustificativaApoioEnsino = "";
        campoJustificativaAdministracao = "";
        campoJustificativaProjetoEnsino = "";
        campoJustificativaSeremPropostas = "";
        campoJustificativaOutrasAtividades = "";
        campoJustificativaProjetoPesquisa = "";
        campoJustificativaProjetoExtensao = "";
        professor = new Professor();
        aulas = new ArrayList();
        apoios = new ArrayList();
        manutencoesEnsino = new ArrayList();
        administrativas = new ArrayList();
        projetosEnsino = new ArrayList();
        projetosExtensao = new ArrayList();
        projetosPesquisa = new ArrayList();
        atividadesASeremPropostas = new ArrayList();
        outrosTiposAtividades = new ArrayList();

    }

    public PTD(int idPTD, String campoJustificativaAtividadeEnsino, String campoJustificativaManutencaoEnsino, String campoJustificativaApoioEnsino, String campoJustificativaAdministracao, String campoJustificativaProjetoEnsino, String campoJustificativaSeremPropostas, String campoJustificativaOutrasAtividades, String campoJustificativaProjetoPesquisa, String campoJustificativaProjetoExtensao) {
        this.idPTD = idPTD;
        this.campoJustificativaAtividadeEnsino = campoJustificativaAtividadeEnsino;
        this.campoJustificativaManutencaoEnsino = campoJustificativaManutencaoEnsino;
        this.campoJustificativaApoioEnsino = campoJustificativaApoioEnsino;
        this.campoJustificativaAdministracao = campoJustificativaAdministracao;
        this.campoJustificativaProjetoEnsino = campoJustificativaProjetoEnsino;
        this.campoJustificativaSeremPropostas = campoJustificativaSeremPropostas;
        this.campoJustificativaOutrasAtividades = campoJustificativaOutrasAtividades;
        this.campoJustificativaProjetoPesquisa = campoJustificativaProjetoPesquisa;
        this.campoJustificativaProjetoExtensao = campoJustificativaProjetoExtensao;
        professor = new Professor();
        aulas = new ArrayList();
        apoios = new ArrayList();
        manutencoesEnsino = new ArrayList();
        administrativas = new ArrayList();
        projetosEnsino = new ArrayList();
        projetosExtensao = new ArrayList();
        projetosPesquisa = new ArrayList();
        atividadesASeremPropostas = new ArrayList();
        outrosTiposAtividades = new ArrayList();
    }

    public PTD(int idPTD, String campoJustificativaAtividadeEnsino, String campoJustificativaManutencaoEnsino, String campoJustificativaApoioEnsino, String campoJustificativaAdministracao, String campoJustificativaProjetoEnsino, String campoJustificativaSeremPropostas, String campoJustificativaOutrasAtividades, String campoJustificativaProjetoPesquisa, String campoJustificativaProjetoExtensao, Professor professor, List<Aula> aulas, List<Apoio> apoios, List<ManutencaoEnsino> manutencoesEnsino, List<Administracao> administrativas, List<ProjetoEnsino> projetosEnsino, List<ProjetoExtensao> projetosExtensao, List<ProjetoPesquisa> projetosPesquisa, List<AtividadeASerProposta> atividadesASeremPropostas, List<OutroTipoAtividade> outrosTiposAtividades) {
        this.idPTD = idPTD;
        this.campoJustificativaAtividadeEnsino = campoJustificativaAtividadeEnsino;
        this.campoJustificativaManutencaoEnsino = campoJustificativaManutencaoEnsino;
        this.campoJustificativaApoioEnsino = campoJustificativaApoioEnsino;
        this.campoJustificativaAdministracao = campoJustificativaAdministracao;
        this.campoJustificativaProjetoEnsino = campoJustificativaProjetoEnsino;
        this.campoJustificativaSeremPropostas = campoJustificativaSeremPropostas;
        this.campoJustificativaOutrasAtividades = campoJustificativaOutrasAtividades;
        this.campoJustificativaProjetoPesquisa = campoJustificativaProjetoPesquisa;
        this.campoJustificativaProjetoExtensao = campoJustificativaProjetoExtensao;
        this.professor = professor;
        this.aulas = aulas;
        this.apoios = apoios;
        this.manutencoesEnsino = manutencoesEnsino;
        this.administrativas = administrativas;
        this.projetosEnsino = projetosEnsino;
        this.projetosExtensao = projetosExtensao;
        this.projetosPesquisa = projetosPesquisa;
        this.atividadesASeremPropostas = atividadesASeremPropostas;
        this.outrosTiposAtividades = outrosTiposAtividades;
    }


    public int getIdPTD() {
        return idPTD;
    }

    public void setIdPTD(int idPTD) {
        this.idPTD = idPTD;
    }

    public String getCampoJustificativaAtividadeEnsino() {
        return campoJustificativaAtividadeEnsino;
    }

    public void setCampoJustificativaAtividadeEnsino(String campoJustificativaAtividadeEnsino) {
        this.campoJustificativaAtividadeEnsino = campoJustificativaAtividadeEnsino;
    }

    public String getCampoJustificativaManutencaoEnsino() {
        return campoJustificativaManutencaoEnsino;
    }

    public void setCampoJustificativaManutencaoEnsino(String campoJustificativaManutencaoEnsino) {
        this.campoJustificativaManutencaoEnsino = campoJustificativaManutencaoEnsino;
    }

    public String getCampoJustificativaApoioEnsino() {
        return campoJustificativaApoioEnsino;
    }

    public void setCampoJustificativaApoioEnsino(String campoJustificativaApoioEnsino) {
        this.campoJustificativaApoioEnsino = campoJustificativaApoioEnsino;
    }

    public String getCampoJustificativaAdministracao() {
        return campoJustificativaAdministracao;
    }

    public void setCampoJustificativaAdministracao(String campoJustificativaAdministracao) {
        this.campoJustificativaAdministracao = campoJustificativaAdministracao;
    }

    public String getCampoJustificativaProjetoEnsino() {
        return campoJustificativaProjetoEnsino;
    }

    public void setCampoJustificativaProjetoEnsino(String campoJustificativaProjetoEnsino) {
        this.campoJustificativaProjetoEnsino = campoJustificativaProjetoEnsino;
    }

    public String getCampoJustificativaSeremPropostas() {
        return campoJustificativaSeremPropostas;
    }

    public void setCampoJustificativaSeremPropostas(String campoJustificativaSeremPropostas) {
        this.campoJustificativaSeremPropostas = campoJustificativaSeremPropostas;
    }

    public String getCampoJustificativaOutrasAtividades() {
        return campoJustificativaOutrasAtividades;
    }

    public void setCampoJustificativaOutrasAtividades(String campoJustificativaOutrasAtividades) {
        this.campoJustificativaOutrasAtividades = campoJustificativaOutrasAtividades;
    }

    public String getCampoJustificativaProjetoPesquisa() {
        return campoJustificativaProjetoPesquisa;
    }

    public void setCampoJustificativaProjetoPesquisa(String campoJustificativaProjetoPesquisa) {
        this.campoJustificativaProjetoPesquisa = campoJustificativaProjetoPesquisa;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    public List<Apoio> getApoios() {
        return apoios;
    }

    public void setApoios(List<Apoio> apoios) {
        this.apoios = apoios;
    }

    public List<ManutencaoEnsino> getManutencoesEnsino() {
        return manutencoesEnsino;
    }

    public void setManutencoesEnsino(List<ManutencaoEnsino> manutencoesEnsino) {
        this.manutencoesEnsino = manutencoesEnsino;
    }

    public List<Administracao> getAdministrativas() {
        return administrativas;
    }

    public void setAdministrativas(List<Administracao> administrativas) {
        this.administrativas = administrativas;
    }

    public List<ProjetoEnsino> getProjetosEnsino() {
        return projetosEnsino;
    }

    public void setProjetosEnsino(List<ProjetoEnsino> projetosEnsino) {
        this.projetosEnsino = projetosEnsino;
    }

    public List<ProjetoExtensao> getProjetosExtensao() {
        return projetosExtensao;
    }

    public void setProjetosExtensao(List<ProjetoExtensao> projetosExtensao) {
        this.projetosExtensao = projetosExtensao;
    }

    public List<ProjetoPesquisa> getProjetosPesquisa() {
        return projetosPesquisa;
    }

    public void setProjetosPesquisa(List<ProjetoPesquisa> projetosPesquisa) {
        this.projetosPesquisa = projetosPesquisa;
    }

    public List<AtividadeASerProposta> getAtividadesASeremPropostas() {
        return atividadesASeremPropostas;
    }

    public void setAtividadesASeremPropostas(List<AtividadeASerProposta> atividadesASeremPropostas) {
        this.atividadesASeremPropostas = atividadesASeremPropostas;
    }

    public List<OutroTipoAtividade> getOutrosTiposAtividades() {
        return outrosTiposAtividades;
    }

    public void setOutrosTiposAtividades(List<OutroTipoAtividade> outrosTiposAtividades) {
        this.outrosTiposAtividades = outrosTiposAtividades;
    }

    public String getCampoJustificativaProjetoExtensao() {
        return campoJustificativaProjetoExtensao;
    }

    public void setCampoJustificativaProjetoExtensao(String campoJustificativaProjetoExtensao) {
        this.campoJustificativaProjetoExtensao = campoJustificativaProjetoExtensao;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

}
