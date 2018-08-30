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
import br.edu.ifpr.irati.modelo.ManutencaoEnsino;
import br.edu.ifpr.irati.modelo.OutroTipoAtividade;
import br.edu.ifpr.irati.modelo.PTDSubmetido;
import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.modelo.ProjetoEnsino;
import br.edu.ifpr.irati.modelo.ProjetoExtensao;
import br.edu.ifpr.irati.modelo.ProjetoPesquisa;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class PTDSubmetidoMB {

    private PTDSubmetido ptdSubmetido;
    private List<PTDSubmetido> ptdsSubmetidos;
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

    public PTDSubmetidoMB() {
        ptdSubmetido = new PTDSubmetido();
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

    public void submeterPTDSubmetido(Professor professor) {

        Dao<PTDSubmetido> ptdSubmetidoDAO = new GenericDAO<>(PTDSubmetido.class);
        IAdministracaoDao administracaoDAO = new AdministracaoDAO();
        ptdSubmetido.setProfessor(professor);
        administracoes = administracaoDAO.buscarAdministracoesAtivas(professor);
        ptdSubmetido.setAdministrativas(administracoes);
        IApoioDao apoioDAO = new ApoioDAO();
        apoios = apoioDAO.buscarApoiosAtivos(professor);
        ptdSubmetido.setApoios(apoios);
        IAtividadeASerPropostaDao atividadeASerPropostaDAO = new AtividadeASerPropostaDAO();
        atividadesASeremPropostas = atividadeASerPropostaDAO.buscarAtividadesAtivas(professor);
        ptdSubmetido.setAtividadesASeremPropostas(atividadesASeremPropostas);
        IAulaDao aulaDAO = new AulaDAO();
        aulas = aulaDAO.buscarAulasAtivas(professor);
        ptdSubmetido.setAulas(aulas);
        IManutencaoDao manutencaoDAO = new ManutencaoDAO();
        manutencoesEnsino = manutencaoDAO.buscarManutencoesAtivas(professor);
        ptdSubmetido.setManutencoesEnsino(manutencoesEnsino);
        IProjetoEnsinoDao projetoEnsinoDAO = new ProjetoEnsinoDAO();
        projetosEnsino = projetoEnsinoDAO.buscarProjetosEnsinoAtivos(professor);
        ptdSubmetido.setProjetosEnsino(projetosEnsino);
        IProjetoExtensaoDao projetoExtensaoDAO = new ProjetoExtensaoDAO();
        projetosExtensao = projetoExtensaoDAO.buscarProjetosExtensaoAtivos(professor);
        ptdSubmetido.setProjetosExtensao(projetosExtensao);
        IProjetoPesquisaDao projetoPesquisaDAO = new ProjetoPesquisaDAO();
        projetosPesquisa = projetoPesquisaDAO.buscarProjetosPesquisaAtivos(professor);
        ptdSubmetido.setProjetosPesquisa(projetosPesquisa);
        ptdSubmetido.setEstado("Em avaliação");
        ptdSubmetidoDAO.salvar(ptdSubmetido);
    }
    
    public String alterarPTDSubmetido(PTDSubmetido ptdSubmetido) {
        Dao<PTDSubmetido> ptdSubmetidoDAO = new GenericDAO<>(PTDSubmetido.class);
        this.ptdSubmetido = ptdSubmetido;
        ptdSubmetidoDAO.alterar(ptdSubmetido);
        return "/adicionar aqui";
    }

     public String excluirPTDSubmetido(PTDSubmetido ptdSubmetido) {
        Dao<PTDSubmetido> ptdSubmetidoDAO = new GenericDAO<>(PTDSubmetido.class);
        ptdSubmetidoDAO.excluir(ptdSubmetido);
        ptdsSubmetidos = ptdSubmetidoDAO.buscarTodos(PTDSubmetido.class);
        return "/adicionar aqui";
    }


    public Administracao getAdministracao() {
        return administracao;
    }

    public void setAdministracao(Administracao administracao) {
        this.administracao = administracao;
    }

    public List<Administracao> getAdministracoes() {
        return administracoes;
    }

    public void setAdministracoes(List<Administracao> administracoes) {
        this.administracoes = administracoes;
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

    public List<ProjetoEnsino> getProjetosEnsino() {
        return projetosEnsino;
    }

    public void setProjetosEnsino(List<ProjetoEnsino> projetosEnsino) {
        this.projetosEnsino = projetosEnsino;
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

    public PTDSubmetido getPtdSubmetido() {
        return ptdSubmetido;
    }

    public void setPtdSubmetido(PTDSubmetido ptdSubmetido) {
        this.ptdSubmetido = ptdSubmetido;
    }

    public List<PTDSubmetido> getPtdsSubmetidos() {
        return ptdsSubmetidos;
    }

    public void setPtdsSubmetidos(List<PTDSubmetido> ptdsSubmetidos) {
        this.ptdsSubmetidos = ptdsSubmetidos;
    }
}
