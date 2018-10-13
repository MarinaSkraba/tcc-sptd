package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.dao.IProjetoPesquisaExtensaoDao;
import br.edu.ifpr.irati.dao.ProjetoPesquisaExtensaoDAO;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.PTD;
import br.edu.ifpr.irati.modelo.Participacao;
import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.modelo.ProjetoPesquisaExtensao;
import br.edu.ifpr.irati.modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ProjetoPesquisaExtensaoMB {

    private Participacao participacaoAutorSelecionadoParaParticipacaoAutor;
    private Participacao participacaoAutorSelecionadoParaHorario;
    private Participacao participacaoColabSelecionadoParaParticipacaoColab;
    private Participacao participacaoSelecionadoParaHorarioColab;
    private List<ProjetoPesquisaExtensao> projetosPesquisaExtensaoCadastrados;
    private List<ProjetoPesquisaExtensao> projetosPesquisaExtensaoCadastradosPorProfessor;
    private Horario horario;
    private List<Horario> horarios;
    private Participacao participacao;
    private ProjetoPesquisaExtensao projetoAutorNovo;
    private ProjetoPesquisaExtensao projetoPesquisaExtensaoSelecionado;
    private String tipoProjetoAutor;
    private String tipoProjetoColab;
    private PTDMB ptdmb;

    public ProjetoPesquisaExtensaoMB() {

        participacaoAutorSelecionadoParaParticipacaoAutor = new Participacao();
        participacaoAutorSelecionadoParaHorario = new Participacao();
        participacaoColabSelecionadoParaParticipacaoColab = new Participacao();
        participacaoSelecionadoParaHorarioColab = new Participacao();
        tipoProjetoAutor = "";
        tipoProjetoColab = "";
        horario = new Horario();
        participacao = new Participacao();
        projetoAutorNovo = new ProjetoPesquisaExtensao();
        projetosPesquisaExtensaoCadastrados = new ArrayList();
        Dao<ProjetoPesquisaExtensao> projetoPesquisaExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class);
        projetosPesquisaExtensaoCadastrados = projetoPesquisaExtensaoDAO.buscarTodos(ProjetoPesquisaExtensao.class);
        projetosPesquisaExtensaoCadastradosPorProfessor = new ArrayList();

    }

    public String salvarProjetoPesquisaExtensao(Professor professorAutor, PTD ptd) {

        Dao<ProjetoPesquisaExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        projetoAutorNovo.setEstadoProjetoPesquisaExtensao("Ativo");
        participacao.setProjetoPesquisaExtensao(projetoAutorNovo);
        projetoExtensaoDAO.salvar(participacao.getProjetoPesquisaExtensao());
        participacao.setProjetoPesquisaExtensao(projetoExtensaoDAO.buscarTodos(ProjetoPesquisaExtensao.class).get(projetoExtensaoDAO.buscarTodos(ProjetoPesquisaExtensao.class).size() - 1));
        participacao.setRotulo("Autor");
        participacao.setEstadoParticipacao("Ativo");
        participacao.setProfessor(professorAutor);
        participacaoDAO.salvar(participacao);
        participacao = participacaoDAO.buscarTodos(Participacao.class).get(participacaoDAO.buscarTodos(Participacao.class).size() - 1);
        projetoExtensaoDAO.alterar(participacao.getProjetoPesquisaExtensao());
        ptd.getParticipacoes().add(participacao);
        ptdDAO.alterar(ptd);
        participacao = new Participacao();
        projetoAutorNovo = new ProjetoPesquisaExtensao();
        return "CriarCorrigirPTD?faces-redirect=true";

    }

    public String salvarProjetoPesquisaExtensaoJaCadastradoAutor(Professor professorAutor, PTD ptd) {

        Dao<ProjetoPesquisaExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        participacao.setRotulo("Autor");
        participacao.setProfessor(professorAutor);
        participacao.setEstadoParticipacao("Ativo");
        participacaoDAO.salvar(participacao);
        participacao = participacaoDAO.buscarTodos(Participacao.class).get(participacaoDAO.buscarTodos(Participacao.class).size() - 1);
        projetoExtensaoDAO.alterar(participacao.getProjetoPesquisaExtensao());
        ptd.getParticipacoes().add(participacao);
        ptdDAO.alterar(ptd);
        participacao = new Participacao();
        return "CriarCorrigirPTD?faces-redirect=true";

    }

    public String salvarProjetoPesquisaExtensaoJaCadastradoColaboracao(Professor professorAutor, PTD ptd) {

        Dao<ProjetoPesquisaExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        participacao.setRotulo("Colaborador");
        participacao.setProfessor(professorAutor);
        participacao.setEstadoParticipacao("Ativo");
        participacaoDAO.salvar(participacao);
        participacao = participacaoDAO.buscarTodos(Participacao.class).get(participacaoDAO.buscarTodos(Participacao.class).size() - 1);
        projetoExtensaoDAO.alterar(participacao.getProjetoPesquisaExtensao());
        ptd.getParticipacoes().add(participacao);
        ptdDAO.alterar(ptd);
        participacao = new Participacao();
        return "CriarCorrigirPTD?faces-redirect=true";

    }

    public String alterarParticipacaoAutor() {
        Dao<ProjetoPesquisaExtensao> projetoPesquisaExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        participacaoDAO.alterar(participacaoAutorSelecionadoParaParticipacaoAutor);
        projetoPesquisaExtensaoDAO.alterar(participacaoAutorSelecionadoParaParticipacaoAutor.getProjetoPesquisaExtensao());
        participacaoAutorSelecionadoParaParticipacaoAutor = new Participacao();
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String alterarParticipacaoColab() {
        Dao<ProjetoPesquisaExtensao> projetoPesquisaExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        participacaoDAO.alterar(participacaoColabSelecionadoParaParticipacaoColab);
        projetoPesquisaExtensaoDAO.alterar(participacaoColabSelecionadoParaParticipacaoColab.getProjetoPesquisaExtensao());
        participacaoColabSelecionadoParaParticipacaoColab = new Participacao();
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String desabilitarProjetoPesquisaExtensao(ProjetoPesquisaExtensao projetoPesquisaExtensao) {
        Dao<ProjetoPesquisaExtensao> projetoPesquisaExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class);
        projetoPesquisaExtensao.setEstadoProjetoPesquisaExtensao("Desativado");
        projetoPesquisaExtensaoDAO.alterar(projetoPesquisaExtensao);
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String excluirParticipacaoPesquisaExtensao(Participacao participacao, PTD ptd) {
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        ptd.getParticipacoes().remove(participacao);
        ptdDAO.alterar(ptd);
        participacaoDAO.excluir(participacao);
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public void atualizarListaProjetosCadastradosPorProfessor(Serializable idUsuario) {
        IProjetoPesquisaExtensaoDao projetoPesquisaExtensaoDAO = new ProjetoPesquisaExtensaoDAO();
        setProjetosPesquisaExtensaoCadastradosPorProfessor(projetoPesquisaExtensaoDAO.buscarProjetosExtensaoPorProfessor(idUsuario));
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Participacao getParticipacao() {
        return participacao;
    }

    public void setParticipacao(Participacao participacao) {
        this.participacao = participacao;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public String getTipoProjetoAutor() {
        return tipoProjetoAutor;
    }

    public void setTipoProjetoAutor(String tipoProjetoAutor) {
        this.tipoProjetoAutor = tipoProjetoAutor;
    }

    public String getTipoProjetoColab() {
        return tipoProjetoColab;
    }

    public void setTipoProjetoColab(String tipoProjetoColab) {
        this.tipoProjetoColab = tipoProjetoColab;
    }

    public PTDMB getPtdmb() {
        return ptdmb;
    }

    public void setPtdmb(PTDMB ptdmb) {
        this.ptdmb = ptdmb;
    }

    public List<ProjetoPesquisaExtensao> getProjetosPesquisaExtensaoCadastrados() {
        return projetosPesquisaExtensaoCadastrados;
    }

    public void setProjetosPesquisaExtensaoCadastrados(List<ProjetoPesquisaExtensao> projetosPesquisaExtensao) {
        this.projetosPesquisaExtensaoCadastrados = projetosPesquisaExtensao;
    }

    public ProjetoPesquisaExtensao getProjetoPesquisaExtensaoSelecionado() {
        return projetoPesquisaExtensaoSelecionado;
    }

    public void setProjetoPesquisaExtensaoSelecionado(ProjetoPesquisaExtensao projetoPesquisaExtensaoSelecionado) {
        this.projetoPesquisaExtensaoSelecionado = projetoPesquisaExtensaoSelecionado;
    }

    /**
     * @return the projetosPesquisaExtensaoCadastradosPorProfessor
     */
    public List<ProjetoPesquisaExtensao> getProjetosPesquisaExtensaoCadastradosPorProfessor() {
        return projetosPesquisaExtensaoCadastradosPorProfessor;
    }

    /**
     * @param projetosPesquisaExtensaoCadastradosPorProfessor the
     * projetosPesquisaExtensaoCadastradosPorProfessor to set
     */
    public void setProjetosPesquisaExtensaoCadastradosPorProfessor(List<ProjetoPesquisaExtensao> projetosPesquisaExtensaoCadastradosPorProfessor) {
        this.projetosPesquisaExtensaoCadastradosPorProfessor = projetosPesquisaExtensaoCadastradosPorProfessor;
    }

    /**
     * @return the participacaoSelecionadoParaHorarioColab
     */
    public Participacao getParticipacaoSelecionadoParaHorarioColab() {
        return participacaoSelecionadoParaHorarioColab;
    }

    /**
     * @param participacaoSelecionadoParaHorarioColab the
     * participacaoSelecionadoParaHorarioColab to set
     */
    public void setParticipacaoSelecionadoParaHorarioColab(Participacao participacaoSelecionadoParaHorarioColab) {
        this.participacaoSelecionadoParaHorarioColab = participacaoSelecionadoParaHorarioColab;
    }

    /**
     * @return the participacaoAutorSelecionadoParaParticipacaoAutor
     */
    public Participacao getParticipacaoAutorSelecionadoParaParticipacaoAutor() {
        return participacaoAutorSelecionadoParaParticipacaoAutor;
    }

    /**
     * @param participacaoAutorSelecionadoParaParticipacaoAutor the
     * participacaoAutorSelecionadoParaParticipacaoAutor to set
     */
    public void setParticipacaoAutorSelecionadoParaParticipacaoAutor(Participacao participacaoAutorSelecionadoParaParticipacaoAutor) {
        this.participacaoAutorSelecionadoParaParticipacaoAutor = participacaoAutorSelecionadoParaParticipacaoAutor;
    }

    /**
     * @return the participacaoAutorSelecionadoParaHorario
     */
    public Participacao getParticipacaoAutorSelecionadoParaHorario() {
        return participacaoAutorSelecionadoParaHorario;
    }

    /**
     * @param participacaoAutorSelecionadoParaHorario the
     * participacaoAutorSelecionadoParaHorario to set
     */
    public void setParticipacaoAutorSelecionadoParaHorario(Participacao participacaoAutorSelecionadoParaHorario) {
        this.participacaoAutorSelecionadoParaHorario = participacaoAutorSelecionadoParaHorario;
    }

    /**
     * @return the participacaoColabSelecionadoParaParticipacaoColab
     */
    public Participacao getParticipacaoColabSelecionadoParaParticipacaoColab() {
        return participacaoColabSelecionadoParaParticipacaoColab;
    }

    /**
     * @param participacaoColabSelecionadoParaParticipacaoColab the
     * participacaoColabSelecionadoParaParticipacaoColab to set
     */
    public void setParticipacaoColabSelecionadoParaParticipacaoColab(Participacao participacaoColabSelecionadoParaParticipacaoColab) {
        this.participacaoColabSelecionadoParaParticipacaoColab = participacaoColabSelecionadoParaParticipacaoColab;
    }

    /**
     * @return the projetoAutorNovo
     */
    public ProjetoPesquisaExtensao getProjetoAutorNovo() {
        return projetoAutorNovo;
    }

    /**
     * @param projetoAutorNovo the projetoAutorNovo to set
     */
    public void setProjetoAutorNovo(ProjetoPesquisaExtensao projetoAutorNovo) {
        this.projetoAutorNovo = projetoAutorNovo;
    }

}
