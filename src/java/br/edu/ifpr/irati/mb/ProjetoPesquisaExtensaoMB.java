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

    private ProjetoPesquisaExtensao projetoPesquisaExtensaoSelecionadoParaProjetoPesquisaExtensao;
    private ProjetoPesquisaExtensao projetoPesquisaExtensaoSelecionadoParaHorario;
    private ProjetoPesquisaExtensao projetoPesquisaExtensao;
    private List<ProjetoPesquisaExtensao> projetosPesquisaExtensaoCadastrados;
    private List<ProjetoPesquisaExtensao> projetosPesquisaExtensaoCadastradosPorProfessor;
    private Horario horario;
    private List<Horario> horarios;
    private Participacao participacao;
    private ProjetoPesquisaExtensao projetoPesquisaExtensaoSelecionado;
    private String tipoProjetoAutor;
    private String tipoProjetoColab;
    private PTDMB ptdmb;

    public ProjetoPesquisaExtensaoMB() {

        projetoPesquisaExtensaoSelecionadoParaProjetoPesquisaExtensao = new ProjetoPesquisaExtensao();
        projetoPesquisaExtensaoSelecionadoParaHorario = new ProjetoPesquisaExtensao();
        tipoProjetoAutor = "";
        tipoProjetoColab = "";
        projetoPesquisaExtensao = new ProjetoPesquisaExtensao();
        horario = new Horario();
        participacao = new Participacao();
        projetosPesquisaExtensaoCadastrados = new ArrayList();
        Dao<ProjetoPesquisaExtensao> projetoPesquisaExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class);
        projetosPesquisaExtensaoCadastrados = projetoPesquisaExtensaoDAO.buscarTodos(ProjetoPesquisaExtensao.class);
        projetosPesquisaExtensaoCadastradosPorProfessor = new ArrayList();

    }

    public ProjetoPesquisaExtensaoMB(String tipoProjetoAutor, String tipoProjetoColab) {

        this.tipoProjetoAutor = tipoProjetoAutor;
        this.tipoProjetoColab = tipoProjetoColab;
    }

    public void salvarProjetoPesquisaExtensao(Professor professorAutor, PTD ptd) {

        Dao<ProjetoPesquisaExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        participacao = new Participacao();
        projetoExtensaoDAO.salvar(projetoPesquisaExtensao);
        projetoPesquisaExtensao = projetoExtensaoDAO.buscarTodos(ProjetoPesquisaExtensao.class).get(projetoExtensaoDAO.buscarTodos(ProjetoPesquisaExtensao.class).size() - 1);
        ptd.getProjetosPesquisaExtensao().add(projetoPesquisaExtensao);
        participacao.setRotulo("Autor");
        participacao.setProfessor(professorAutor);
        participacao.setProjetoPesquisaExtensao(projetoPesquisaExtensao);
        participacaoDAO.salvar(participacao);
        participacao = participacaoDAO.buscarTodos(Participacao.class).get(participacaoDAO.buscarTodos(Participacao.class).size() - 1);
        projetoPesquisaExtensao.getParticipacoes().add(participacao);
        projetoExtensaoDAO.alterar(projetoPesquisaExtensao);
        ptdDAO.alterar(ptd);
        projetoPesquisaExtensao = new ProjetoPesquisaExtensao();
        participacao = new Participacao();

    }

    public void salvarProjetoPesquisaExtensaoJaCadastradoAutor(Professor professorAutor, PTD ptd) {

        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        participacao = new Participacao();
        ptd.getProjetosPesquisaExtensao().add(projetoPesquisaExtensao);
        participacao.setRotulo("Autor");
        participacao.setProfessor(professorAutor);
        participacao.setProjetoPesquisaExtensao(projetoPesquisaExtensao);
        participacaoDAO.salvar(participacao);
        participacao = participacaoDAO.buscarTodos(Participacao.class).get(participacaoDAO.buscarTodos(Participacao.class).size() - 1);
        ptdDAO.alterar(ptd);
        projetoPesquisaExtensao = new ProjetoPesquisaExtensao();
        participacao = new Participacao();

    }

    public void salvarProjetoPesquisaExtensaoJaCadastradoColaboracao(Professor professorAutor, PTD ptd) {
        
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        participacao = new Participacao();
        ptd.getProjetosPesquisaExtensao().add(projetoPesquisaExtensao);
        participacao.setRotulo("Colaborador");
        participacao.setProfessor(professorAutor);
        participacao.setProjetoPesquisaExtensao(projetoPesquisaExtensao);
        participacaoDAO.salvar(participacao);
        participacao = participacaoDAO.buscarTodos(Participacao.class).get(participacaoDAO.buscarTodos(Participacao.class).size() - 1);
        ptdDAO.alterar(ptd);
        projetoPesquisaExtensao = new ProjetoPesquisaExtensao();
        participacao = new Participacao();
        
    }

    public String alterarProjetoPesquisaExtensao() {
        Dao<ProjetoPesquisaExtensao> projetoPesquisaExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class);
        projetoPesquisaExtensaoDAO.alterar(projetoPesquisaExtensaoSelecionadoParaProjetoPesquisaExtensao);
        projetoPesquisaExtensaoSelecionadoParaProjetoPesquisaExtensao = new ProjetoPesquisaExtensao();
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String desabilitarProjetoPesquisaExtensao(ProjetoPesquisaExtensao projetoPesquisaExtensao) {
        Dao<ProjetoPesquisaExtensao> projetoPesquisaExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class);
        projetoPesquisaExtensao.setEstadoProjetoPesquisaExtensao("Desativado");
        projetoPesquisaExtensaoDAO.alterar(projetoPesquisaExtensao);
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String excluirProjetoPesquisaExtensao(ProjetoPesquisaExtensao projetoPesquisaExtensao, PTD ptd) {
        Dao<ProjetoPesquisaExtensao> projetoPesquisaExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        ptd.getProjetosPesquisaExtensao().remove(projetoPesquisaExtensao);
        ptdDAO.alterar(ptd);
        projetoPesquisaExtensaoDAO.excluir(projetoPesquisaExtensao);

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

    public ProjetoPesquisaExtensao getProjetoPesquisaExtensaoSelecionadoParaProjetoPesquisaExtensao() {
        return projetoPesquisaExtensaoSelecionadoParaProjetoPesquisaExtensao;
    }

    public void setProjetoPesquisaExtensaoSelecionadoParaProjetoPesquisaExtensao(ProjetoPesquisaExtensao projetoPesquisaExtensaoSelecionadoParaProjetoPesquisaExtensao) {
        this.projetoPesquisaExtensaoSelecionadoParaProjetoPesquisaExtensao = projetoPesquisaExtensaoSelecionadoParaProjetoPesquisaExtensao;
    }

    public ProjetoPesquisaExtensao getProjetoPesquisaExtensaoSelecionadoParaHorario() {
        return projetoPesquisaExtensaoSelecionadoParaHorario;
    }

    public void setProjetoPesquisaExtensaoSelecionadoParaHorario(ProjetoPesquisaExtensao projetoPesquisaExtensaoSelecionadoParaHorario) {
        this.projetoPesquisaExtensaoSelecionadoParaHorario = projetoPesquisaExtensaoSelecionadoParaHorario;
    }

    public ProjetoPesquisaExtensao getProjetoPesquisaExtensao() {
        return projetoPesquisaExtensao;
    }

    public void setProjetoPesquisaExtensao(ProjetoPesquisaExtensao projetoPesquisaExtensao) {
        this.projetoPesquisaExtensao = projetoPesquisaExtensao;
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
}
