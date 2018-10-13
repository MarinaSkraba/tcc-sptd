package br.edu.ifpr.irati.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "ptd")
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

    @Column(name = "campoJustificativaProjetoPesquisaExtensao", nullable = true, length = 500)
    private String campoJustificativaProjetoPesquisaExtensao;

    @Column(name = "campoObservacoesDiretorEnsino", nullable = true, length = 1000)
    private String campoObservacoesDiretorEnsino;

    @Column(name = "estadoPTD", nullable = false, length = 20)
    private String estadoPTD;

    @Temporal(TemporalType.DATE)
    private Date dataAvaliacaoPTD;

    @ManyToOne
    @JoinColumn(name = "diretorEnsino_idUsuario", nullable = true)
    private DiretorEnsino diretorEnsino;

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
    private List<Participacao> participacoes;

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
        campoJustificativaProjetoPesquisaExtensao = "";
        campoObservacoesDiretorEnsino = "";
        professor = new Professor();
        aulas = new ArrayList();
        apoios = new ArrayList();
        manutencoesEnsino = new ArrayList();
        administrativas = new ArrayList();
        atividadesASeremPropostas = new ArrayList();
        outrosTiposAtividades = new ArrayList();
        estadoPTD = "";
        dataAvaliacaoPTD = new Date();
        diretorEnsino = new DiretorEnsino();
        participacoes = new ArrayList();
    }

    public PTD(int idPTD, String campoJustificativaAtividadeEnsino, String campoJustificativaManutencaoEnsino, String campoJustificativaApoioEnsino, String campoJustificativaAdministracao, String campoJustificativaProjetoEnsino, String campoJustificativaSeremPropostas, String campoJustificativaOutrasAtividades, String campoJustificativaProjetoPesquisaExtensao, String campoObservacoesDiretorEnsino, String estadoPTD) {
        this.idPTD = idPTD;
        this.campoJustificativaAtividadeEnsino = campoJustificativaAtividadeEnsino;
        this.campoJustificativaManutencaoEnsino = campoJustificativaManutencaoEnsino;
        this.campoJustificativaApoioEnsino = campoJustificativaApoioEnsino;
        this.campoJustificativaAdministracao = campoJustificativaAdministracao;
        this.campoJustificativaProjetoEnsino = campoJustificativaProjetoEnsino;
        this.campoJustificativaSeremPropostas = campoJustificativaSeremPropostas;
        this.campoJustificativaOutrasAtividades = campoJustificativaOutrasAtividades;
        this.campoJustificativaProjetoPesquisaExtensao = campoJustificativaProjetoPesquisaExtensao;
        this.campoObservacoesDiretorEnsino = campoObservacoesDiretorEnsino;
        this.estadoPTD = estadoPTD;
        this.dataAvaliacaoPTD = new Date();
        this.diretorEnsino = new DiretorEnsino();
        professor = new Professor();
        aulas = new ArrayList();
        apoios = new ArrayList();
        manutencoesEnsino = new ArrayList();
        administrativas = new ArrayList();
        participacoes = new ArrayList();
        atividadesASeremPropostas = new ArrayList();
        outrosTiposAtividades = new ArrayList();
    }

    public PTD(int idPTD, String campoJustificativaAtividadeEnsino, String campoJustificativaManutencaoEnsino, String campoJustificativaApoioEnsino, String campoJustificativaAdministracao, String campoJustificativaProjetoEnsino, String campoJustificativaSeremPropostas, String campoJustificativaOutrasAtividades, String campoJustificativaProjetoPesquisaExtensao, String campoObservacoesDiretorEnsino, String estadoPTD, Date dataAvaliacaoPTD, DiretorEnsino diretorEnsino, Professor professor, List<Aula> aulas, List<Apoio> apoios, List<ManutencaoEnsino> manutencoesEnsino, List<Administracao> administrativas, List<Participacao> participacoes, List<AtividadeASerProposta> atividadesASeremPropostas, List<OutroTipoAtividade> outrosTiposAtividades) {
        this.idPTD = idPTD;
        this.campoJustificativaAtividadeEnsino = campoJustificativaAtividadeEnsino;
        this.campoJustificativaManutencaoEnsino = campoJustificativaManutencaoEnsino;
        this.campoJustificativaApoioEnsino = campoJustificativaApoioEnsino;
        this.campoJustificativaAdministracao = campoJustificativaAdministracao;
        this.campoJustificativaProjetoEnsino = campoJustificativaProjetoEnsino;
        this.campoJustificativaSeremPropostas = campoJustificativaSeremPropostas;
        this.campoJustificativaOutrasAtividades = campoJustificativaOutrasAtividades;
        this.campoJustificativaProjetoPesquisaExtensao = campoJustificativaProjetoPesquisaExtensao;
        this.campoObservacoesDiretorEnsino = campoObservacoesDiretorEnsino;
        this.estadoPTD = estadoPTD;
        this.dataAvaliacaoPTD = dataAvaliacaoPTD;
        this.diretorEnsino = diretorEnsino;
        this.professor = professor;
        this.aulas = aulas;
        this.apoios = apoios;
        this.manutencoesEnsino = manutencoesEnsino;
        this.administrativas = administrativas;
        this.participacoes = participacoes;
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

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getEstadoPTD() {
        return estadoPTD;
    }

    public void setEstadoPTD(String estadoPTD) {
        this.estadoPTD = estadoPTD;
    }

    public Date getDataAvaliacaoPTD() {
        return dataAvaliacaoPTD;
    }

    public void setDataAvaliacaoPTD(Date dataAvaliacaoPTD) {
        this.dataAvaliacaoPTD = dataAvaliacaoPTD;
    }

    public DiretorEnsino getDiretorEnsino() {
        return diretorEnsino;
    }

    public void setDiretorEnsino(DiretorEnsino diretorEnsino) {
        this.diretorEnsino = diretorEnsino;
    }

    public String getCampoJustificativaProjetoPesquisaExtensao() {
        return campoJustificativaProjetoPesquisaExtensao;
    }

    public void setCampoJustificativaProjetoPesquisaExtensao(String campoJustificativaProjetoPesquisaExtensao) {
        this.campoJustificativaProjetoPesquisaExtensao = campoJustificativaProjetoPesquisaExtensao;
    }

    public String getCampoObservacoesDiretorEnsino() {
        return campoObservacoesDiretorEnsino;
    }

    public void setCampoObservacoesDiretorEnsino(String campoObservacoesDiretorEnsino) {
        this.campoObservacoesDiretorEnsino = campoObservacoesDiretorEnsino;
    }

    /**
     * @return the participacoesAutor
     */
    public List<Participacao> getParticipacoes() {
        return participacoes;
    }

    /**
     * @param participacoes the participacoesAutor to set
     */
    public void setParticipacoes(List<Participacao> participacoes) {
        this.participacoes = participacoes;
    }


}
