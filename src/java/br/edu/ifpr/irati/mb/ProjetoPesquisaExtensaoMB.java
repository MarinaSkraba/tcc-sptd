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
    private List<ProjetoPesquisaExtensao> projetosPesquisaExtensao;
    private Horario horario;
    private List<Horario> horarios;
    private Participacao participacao;
    private ProjetoPesquisaExtensao projetoExtensaoSelecionado;
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
        projetosPesquisaExtensao = new ArrayList();
        Dao<ProjetoPesquisaExtensao> projetoPesquisaExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class);
        projetosPesquisaExtensao= projetoPesquisaExtensaoDAO.buscarTodos(ProjetoPesquisaExtensao.class);        
    }

    public ProjetoPesquisaExtensaoMB(String tipoProjetoAutor, String tipoProjetoColab) {

        this.tipoProjetoAutor = tipoProjetoAutor;
        this.tipoProjetoColab = tipoProjetoColab;
    }

    public void salvarProjetoPesquisaExtensaoAutor(Professor professorAutor, PTD ptd) {

        Dao<ProjetoPesquisaExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        participacao.setRotulo("Autor");
        participacao.setEstadoParticipacao("Ativo");
        participacao.setProfessor(professorAutor);
        participacaoDAO.salvar(participacao);
        projetoPesquisaExtensao.getParticipacoes().add(participacao);
        projetoExtensaoDAO.salvar(projetoPesquisaExtensao);
        projetoPesquisaExtensao = projetoExtensaoDAO.buscarTodos(ProjetoPesquisaExtensao.class).get(projetoExtensaoDAO.buscarTodos(ProjetoPesquisaExtensao.class).size() - 1);
        ptd.getProjetosPesquisaExtensao().add(projetoPesquisaExtensao);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        ptdDAO.alterar(ptd);
        projetoPesquisaExtensao = new ProjetoPesquisaExtensao();
    }
    
    public void salvarColaboracaoPesquisaExtensao(Professor professorColaborador) {

        Dao<ProjetoPesquisaExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        participacao.setRotulo("Colaborador");
        participacao.setEstadoParticipacao("Ativo");
        participacao.setProfessor(professorColaborador);
        participacaoDAO.salvar(participacao);
        projetoExtensaoSelecionado.getParticipacoes().add(participacao);
        projetoExtensaoDAO.alterar(projetoExtensaoSelecionado);

    }

    public String alterarProjetoPesquisaExtensao(ProjetoPesquisaExtensao projetoExtensao) {
        Dao<ProjetoPesquisaExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class);
        projetoExtensaoDAO.alterar(projetoPesquisaExtensaoSelecionadoParaProjetoPesquisaExtensao);
        projetoPesquisaExtensaoSelecionadoParaProjetoPesquisaExtensao = new ProjetoPesquisaExtensao();
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String desabilitarProjetoPesquisaExtensao(ProjetoPesquisaExtensao projetoExtensao) {
        Dao<ProjetoPesquisaExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class);
        projetoExtensao.setEstadoProjetoExtensao("Desativado");
        projetoExtensaoDAO.alterar(projetoExtensao);
        return "/adicionar html aqui";
    }

    public String excluirProjetoPesquisaExtensao(ProjetoPesquisaExtensao projetoExtensao, PTD ptd) {
        Dao<ProjetoPesquisaExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);

        List<Horario> aux = new ArrayList<>(projetoExtensao.getHorariosProjetoExtensao());
        for (Horario h : aux) {
            projetoExtensao.getHorariosProjetoExtensao().remove(h);
            projetoExtensaoDAO.alterar(projetoExtensao);
            horarioDAO.excluir(h);
        }
        List<Participacao> aux2 = new ArrayList<>(projetoExtensao.getParticipacoes());
        for (Participacao p : aux2) {
            projetoExtensao.getParticipacoes().remove(p);
            projetoExtensaoDAO.alterar(projetoExtensao);
            participacaoDAO.excluir(p);
        }
        ptd.getProjetosPesquisaExtensao().remove(projetoExtensao);
        ptdDAO.alterar(ptd);
        projetoExtensaoDAO.excluir(projetoExtensao);

        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public void adicionarHorario() {
        horarios.add(horario);
        horario = new Horario();
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

    public ProjetoPesquisaExtensao getProjetoExtensaoSelecionado() {
        return projetoExtensaoSelecionado;
    }

    public void setProjetoExtensaoSelecionado(ProjetoPesquisaExtensao projetoExtensaoSelecionado) {
        this.projetoExtensaoSelecionado = projetoExtensaoSelecionado;
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

    public List<ProjetoPesquisaExtensao> getProjetosPesquisaExtensao() {
        return projetosPesquisaExtensao;
    }

    public void setProjetosPesquisaExtensao(List<ProjetoPesquisaExtensao> projetosPesquisaExtensao) {
        this.projetosPesquisaExtensao = projetosPesquisaExtensao;
    }
}
