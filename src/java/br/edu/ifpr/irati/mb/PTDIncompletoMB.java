/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.AdministracaoDAO;
import br.edu.ifpr.irati.dao.ApoioDAO;
import br.edu.ifpr.irati.dao.AtividadeASerPropostaDAO;
import br.edu.ifpr.irati.dao.AulaDAO;
import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.dao.IAdministracaoDao;
import br.edu.ifpr.irati.dao.IApoioDao;
import br.edu.ifpr.irati.dao.IAtividadeASerPropostaDao;
import br.edu.ifpr.irati.dao.IAulaDao;
import br.edu.ifpr.irati.dao.IManutencaoDao;
import br.edu.ifpr.irati.dao.IProjetoEnsinoDao;
import br.edu.ifpr.irati.dao.IProjetoExtensaoDao;
import br.edu.ifpr.irati.dao.IProjetoPesquisaDao;
import br.edu.ifpr.irati.dao.ManutencaoDAO;
import br.edu.ifpr.irati.dao.ProjetoEnsinoDAO;
import br.edu.ifpr.irati.dao.ProjetoExtensaoDAO;
import br.edu.ifpr.irati.dao.ProjetoPesquisaDAO;
import br.edu.ifpr.irati.modelo.Administracao;
import br.edu.ifpr.irati.modelo.Apoio;
import br.edu.ifpr.irati.modelo.AtividadeASerProposta;
import br.edu.ifpr.irati.modelo.Aula;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.ManutencaoEnsino;
import br.edu.ifpr.irati.modelo.OutroTipoAtividade;
import br.edu.ifpr.irati.modelo.PTDIncompleto;
import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.modelo.ProjetoEnsino;
import br.edu.ifpr.irati.modelo.ProjetoExtensao;
import br.edu.ifpr.irati.modelo.ProjetoPesquisa;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class PTDIncompletoMB {

    private PTDIncompleto ptdIncompleto;
    private List<PTDIncompleto> ptdsIncompletos;
    private Administracao administracao;
    private List<Administracao> administracoes;
    private Apoio apoio;
    private List<Apoio> apoios;
    private AtividadeASerProposta atividadeASerProposta;
    private List<AtividadeASerProposta> atividadesASeremPropostas;
    private Aula aula;
    private List<Aula> aulas;
    private ManutencaoEnsino manutencaoEnsino;
    private List<ManutencaoEnsino> manutencoesEnsino;
    private OutroTipoAtividade outroTipoAtividade;
    private List<OutroTipoAtividade> outrosTiposAtividades;
    private ProjetoEnsino projetoEnsino;
    private List<ProjetoEnsino> projetosEnsino;
    private ProjetoExtensao projetoExtensao;
    private List<ProjetoExtensao> projetosExtensao;
    private ProjetoPesquisa projetoPesquisa;
    private List<ProjetoPesquisa> projetosPesquisa;

    public PTDIncompletoMB() {
        ptdIncompleto = new PTDIncompleto();
        administracao = new Administracao();
        apoio = new Apoio();
        atividadeASerProposta = new AtividadeASerProposta();
        aula = new Aula();
        manutencaoEnsino = new ManutencaoEnsino();
        outroTipoAtividade = new OutroTipoAtividade();
        projetoEnsino = new ProjetoEnsino();
        projetoExtensao = new ProjetoExtensao();
        projetoPesquisa = new ProjetoPesquisa();
        administracoes = new ArrayList();
        apoios = new ArrayList();
        atividadesASeremPropostas = new ArrayList();
        aulas = new ArrayList();
        manutencoesEnsino = new ArrayList();
        outrosTiposAtividades = new ArrayList();
        projetosEnsino = new ArrayList();
        projetosExtensao = new ArrayList();
        projetosPesquisa = new ArrayList();
    }

    public void salvarPTDIncompleto(Professor professor) {

        Dao<PTDIncompleto> ptdIncompletoDAO = new GenericDAO<>(PTDIncompleto.class);
        ptdIncompleto.setProfessor(professor);
        PTDIncompleto ptdIncom = new PTDIncompleto();
        ptdIncompletoDAO.salvar(ptdIncom);

    }

    public void alterarPTDIncompleto(Professor professor) {

        Dao<PTDIncompleto> ptdIncompletoDAO = new GenericDAO<>(PTDIncompleto.class);
        ptdIncompleto.setProfessor(professor);
        IAdministracaoDao administracaoDAO = new AdministracaoDAO();
        administracoes = administracaoDAO.buscarAdministracoesAtivas(professor);
        ptdIncompleto.setAdministrativas(administracoes);
        IApoioDao apoioDAO = new ApoioDAO();
        apoios = apoioDAO.buscarApoiosAtivos(professor);
        ptdIncompleto.setApoios(apoios);
        IAtividadeASerPropostaDao atividadeASerPropostaDAO = new AtividadeASerPropostaDAO();
        atividadesASeremPropostas = atividadeASerPropostaDAO.buscarAtividadesAtivas(professor);
        ptdIncompleto.setAtividadesASeremPropostas(atividadesASeremPropostas);
        IAulaDao aulaDAO = new AulaDAO();
        aulas = aulaDAO.buscarAulasAtivas(professor);
        ptdIncompleto.setAulas(aulas);
        IManutencaoDao manutencaoDAO = new ManutencaoDAO();
        manutencoesEnsino = manutencaoDAO.buscarManutencoesAtivas(professor);
        ptdIncompleto.setManutencoesEnsino(manutencoesEnsino);
        IProjetoEnsinoDao projetoEnsinoDAO = new ProjetoEnsinoDAO();
        projetosEnsino = projetoEnsinoDAO.buscarProjetosEnsinoAtivos(professor);
        ptdIncompleto.setProjetosEnsino(projetosEnsino);
        IProjetoExtensaoDao projetoExtensaoDAO = new ProjetoExtensaoDAO();
        projetosExtensao = projetoExtensaoDAO.buscarProjetosExtensaoAtivos(professor);
        ptdIncompleto.setProjetosExtensao(projetosExtensao);
        IProjetoPesquisaDao projetoPesquisaDAO = new ProjetoPesquisaDAO();
        projetosPesquisa = projetoPesquisaDAO.buscarProjetosPesquisaAtivos(professor);
        ptdIncompleto.setProjetosPesquisa(projetosPesquisa);
        PTDIncompleto ptdIncom = new PTDIncompleto();
        ptdIncompletoDAO.alterar(ptdIncom);
    }

    public PTDIncompleto getPtdIncompleto() {
        return ptdIncompleto;
    }

    public void setPtdIncompleto(PTDIncompleto ptdIncompleto) {
        this.ptdIncompleto = ptdIncompleto;
    }

    public List<PTDIncompleto> getPtdsIncompletos() {
        return ptdsIncompletos;
    }

    public void setPtdsIncompletos(List<PTDIncompleto> ptdsIncompletos) {
        this.ptdsIncompletos = ptdsIncompletos;
    }

    public Administracao getAdministracao() {
        return administracao;
    }

    public void setAdministracao(Administracao administracao) {
        this.administracao = administracao;
    }

    public Apoio getApoio() {
        return apoio;
    }

    public void setApoio(Apoio apoio) {
        this.apoio = apoio;
    }

    public List<Apoio> getApoios() {
        return apoios;
    }

    public void setApoios(List<Apoio> apoios) {
        this.apoios = apoios;
    }

    public AtividadeASerProposta getAtividadeASerProposta() {
        return atividadeASerProposta;
    }

    public void setAtividadeASerProposta(AtividadeASerProposta atividadeASerProposta) {
        this.atividadeASerProposta = atividadeASerProposta;
    }

    public List<AtividadeASerProposta> getAtividadesASeremPropostas() {
        return atividadesASeremPropostas;
    }

    public void setAtividadesASeremPropostas(List<AtividadeASerProposta> atividadesASeremPropostas) {
        this.atividadesASeremPropostas = atividadesASeremPropostas;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    public ManutencaoEnsino getManutencaoEnsino() {
        return manutencaoEnsino;
    }

    public void setManutencaoEnsino(ManutencaoEnsino manutencaoEnsino) {
        this.manutencaoEnsino = manutencaoEnsino;
    }

    public List<ManutencaoEnsino> getManutencoesEnsino() {
        return manutencoesEnsino;
    }

    public void setManutencoesEnsino(List<ManutencaoEnsino> manutencoesEnsino) {
        this.manutencoesEnsino = manutencoesEnsino;
    }

    public OutroTipoAtividade getOutroTipoAtividade() {
        return outroTipoAtividade;
    }

    public void setOutroTipoAtividade(OutroTipoAtividade outroTipoAtividade) {
        this.outroTipoAtividade = outroTipoAtividade;
    }

    public List<OutroTipoAtividade> getOutrosTiposAtividades() {
        return outrosTiposAtividades;
    }

    public void setOutrosTiposAtividades(List<OutroTipoAtividade> outrosTiposAtividades) {
        this.outrosTiposAtividades = outrosTiposAtividades;
    }

    public ProjetoEnsino getProjetoEnsino() {
        return projetoEnsino;
    }

    public void setProjetoEnsino(ProjetoEnsino projetoEnsino) {
        this.projetoEnsino = projetoEnsino;
    }

    public ProjetoExtensao getProjetoExtensao() {
        return projetoExtensao;
    }

    public void setProjetoExtensao(ProjetoExtensao projetoExtensao) {
        this.projetoExtensao = projetoExtensao;
    }

    public List<ProjetoExtensao> getProjetosExtensao() {
        return projetosExtensao;
    }

    public void setProjetosExtensao(List<ProjetoExtensao> projetosExtensao) {
        this.projetosExtensao = projetosExtensao;
    }

    public ProjetoPesquisa getProjetoPesquisa() {
        return projetoPesquisa;
    }

    public void setProjetoPesquisa(ProjetoPesquisa projetoPesquisa) {
        this.projetoPesquisa = projetoPesquisa;
    }

    public List<ProjetoPesquisa> getProjetosPesquisa() {
        return projetosPesquisa;
    }

    public void setProjetosPesquisa(List<ProjetoPesquisa> projetosPesquisa) {
        this.projetosPesquisa = projetosPesquisa;
    }

    public List<Administracao> getAdministracoes() {
        return administracoes;
    }

    public void setAdministracoes(List<Administracao> administracoes) {
        this.administracoes = administracoes;
    }

    public List<ProjetoEnsino> getProjetosEnsino() {
        return projetosEnsino;
    }

    public void setProjetosEnsino(List<ProjetoEnsino> projetosEnsino) {
        this.projetosEnsino = projetosEnsino;
    }

}
