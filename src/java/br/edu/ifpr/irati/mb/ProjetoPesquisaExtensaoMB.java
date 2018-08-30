package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.dao.IProjetoExtensaoDao;
import br.edu.ifpr.irati.dao.IProjetoPesquisaDao;
import br.edu.ifpr.irati.dao.ProjetoExtensaoDAO;
import br.edu.ifpr.irati.dao.ProjetoPesquisaDAO;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.Participacao;
import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.modelo.ProjetoExtensao;
import br.edu.ifpr.irati.modelo.ProjetoPesquisa;
import br.edu.ifpr.irati.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ProjetoPesquisaExtensaoMB {

    private ProjetoPesquisa projetoPesquisa;
    private ProjetoExtensao projetoExtensao;
    private List<ProjetoPesquisa> projetosPesquisa;
    private List<ProjetoExtensao> projetosExtensao;
    private List<ProjetoPesquisa> projetosPesquisaColab;
    private List<ProjetoExtensao> projetosExtensaoColab;
    private Horario horario;
    private List<Horario> horarios;
    private Participacao participacao;
    private ProjetoPesquisa projetoPesquisaSelecionado;
    private ProjetoExtensao projetoExtensaoSelecionado;
    private String tipoProjetoAutor;
    private String tipoProjetoColab;

    public ProjetoPesquisaExtensaoMB() {
        tipoProjetoAutor = "";
        tipoProjetoColab = "";
        projetoPesquisa = new ProjetoPesquisa();
        projetoExtensao = new ProjetoExtensao();
        horario = new Horario();
        participacao = new Participacao();
        projetosExtensao = new ArrayList();
        projetosPesquisa = new ArrayList();
        Dao<ProjetoPesquisa> projetoPesquisaDAO = new GenericDAO<>(ProjetoPesquisa.class);
        projetosPesquisaColab = projetoPesquisaDAO.buscarTodos(ProjetoPesquisa.class);
        Dao<ProjetoExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoExtensao.class);
        projetosExtensaoColab = projetoExtensaoDAO.buscarTodos(ProjetoExtensao.class);
    }

    public ProjetoPesquisaExtensaoMB(String tipoProjetoAutor, String tipoProjetoColab) {

        this.tipoProjetoAutor = tipoProjetoAutor;
        this.tipoProjetoColab = tipoProjetoColab;
    }

    public void salvarProjetoExtensaoAutor(Professor professorAutor) {

        Dao<ProjetoExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoExtensao.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        participacao.setRotulo("Autor");
        participacao.setEstadoParticipacao("Ativo");
        participacao.setProfessor(professorAutor);
        participacaoDAO.salvar(participacao);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        horarioDAO.salvar(horario);
        projetoExtensao.getParticipacoes().add(participacao);
        projetoExtensao.getHorariosProjetoExtensao().add(horario);
        projetoExtensaoDAO.salvar(projetoExtensao);
        IProjetoExtensaoDao peDAO = new ProjetoExtensaoDAO();
        projetosExtensao = peDAO.buscarProjetosExtensaoPorProfessor(professorAutor.getIdUsuario());

    }

    public void salvarProjetoPesquisaAutor(Professor professorAutor) {
        Dao<ProjetoPesquisa> projetoPesquisaDAO = new GenericDAO<>(ProjetoPesquisa.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        participacao.setRotulo("Autor");
        participacao.setEstadoParticipacao("Ativo");
        participacao.setProfessor(professorAutor);
        participacaoDAO.salvar(participacao);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        horarioDAO.salvar(horario);
        projetoPesquisa.getParticipacoes().add(participacao);
        projetoPesquisa.getHorariosProjetoPesquisa().add(horario);
        projetoPesquisaDAO.salvar(projetoPesquisa);
        IProjetoPesquisaDao ppDAO = new ProjetoPesquisaDAO();
        projetosPesquisa = ppDAO.buscarProjetosPesquisaPorProfessor(professorAutor.getIdUsuario());
    }

    public void salvarColaboracaoExtensao(Professor professorColaborador) {

        Dao<ProjetoExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoExtensao.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        participacao.setRotulo("Colaborador");
        participacao.setEstadoParticipacao("Ativo");
        participacao.setProfessor(professorColaborador);
        participacaoDAO.salvar(participacao);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        horarioDAO.salvar(horario);
        projetoExtensaoSelecionado.getParticipacoes().add(participacao);
        projetoExtensaoSelecionado.getHorariosProjetoExtensao().add(horario);
        projetoExtensaoDAO.alterar(projetoExtensaoSelecionado);
        IProjetoExtensaoDao peDAO = new ProjetoExtensaoDAO();
        projetosExtensaoColab = peDAO.buscarProjetosExtensaoColabPorProfessor(professorColaborador.getIdUsuario());

    }

    public void salvarColaboracaoPesquisa(Professor professorColaborador) {
        Dao<ProjetoPesquisa> projetoPesquisaDAO = new GenericDAO<>(ProjetoPesquisa.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        participacao.setRotulo("Colaborador");
        participacao.setEstadoParticipacao("Ativo");
        participacao.setProfessor(professorColaborador);
        participacaoDAO.salvar(participacao);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        horarioDAO.salvar(horario);
        projetoPesquisaSelecionado.getParticipacoes().add(participacao);
        projetoPesquisaSelecionado.getHorariosProjetoPesquisa().add(horario);
        projetoPesquisaDAO.alterar(projetoPesquisaSelecionado);
        IProjetoPesquisaDao ppDAO = new ProjetoPesquisaDAO();
        projetosPesquisaColab = ppDAO.buscarProjetosPesquisaColabPorProfessor(professorColaborador.getIdUsuario());
    }

    public String alterarProjetoExtensao(ProjetoExtensao projetoExtensao) {
        Dao<ProjetoExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoExtensao.class);
        this.projetoExtensao = projetoExtensao;
        projetoExtensaoDAO.alterar(projetoExtensao);
        return "/adicionar aqui";
    }

    public String desabilitarProjetoExtensao(ProjetoExtensao projetoExtensao) {
        Dao<ProjetoExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoExtensao.class);
        projetoExtensao.setEstadoProjetoExtensao("Desativado");
        projetoExtensaoDAO.alterar(projetoExtensao);
        return "/adicionar html aqui";
    }

    public String excluirProjetoExtensao(ProjetoExtensao projetoExtensao) {
        Dao<ProjetoExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoExtensao.class);
        projetoExtensaoDAO.excluir(projetoExtensao);
        projetosExtensao = projetoExtensaoDAO.buscarTodos(ProjetoExtensao.class);
        return "/adicionar aqui";
    }

    public String alterarProjetoPesquisa(ProjetoPesquisa projetoPesquisa) {
        this.projetoPesquisa = projetoPesquisa;
        Dao<ProjetoPesquisa> projetoPesquisaDAO = new GenericDAO<>(ProjetoPesquisa.class);
        projetoPesquisaDAO.alterar(projetoPesquisa);
        return "/adicionar aqui";
    }

    public String desabilitarProjetoPesquisa(ProjetoPesquisa projetoPesquisa) {
        Dao<ProjetoPesquisa> projetoPesquisaDAO = new GenericDAO<>(ProjetoPesquisa.class);
        projetoPesquisa.setEstadoProjetoPesquisa("Desativado");
        projetoPesquisaDAO.alterar(projetoPesquisa);
        return "/adicionar html aqui";
    }

    public String excluirProjetoPesquisa(ProjetoPesquisa projetoPesquisa) {
        Dao<ProjetoPesquisa> projetoPesquisaDAO = new GenericDAO<>(ProjetoPesquisa.class);
        projetoPesquisaDAO.excluir(projetoPesquisa);
        projetosPesquisa = projetoPesquisaDAO.buscarTodos(ProjetoPesquisa.class);
        return "/adicionar aqui";
    }

    public void adicionarHorario() {
        horarios.add(horario);
        horario = new Horario();
    }

    public ProjetoPesquisa getProjetoPesquisa() {
        return projetoPesquisa;
    }

    public void setProjetoPesquisa(ProjetoPesquisa projetoPesquisa) {
        this.projetoPesquisa = projetoPesquisa;
    }

    public ProjetoExtensao getProjetoExtensao() {
        return projetoExtensao;
    }

    public void setProjetoExtensao(ProjetoExtensao projetoExtensao) {
        this.projetoExtensao = projetoExtensao;
    }

    public List<ProjetoPesquisa> getProjetosPesquisa() {
        return projetosPesquisa;
    }

    public void setProjetosPesquisa(List<ProjetoPesquisa> projetosPesquisa) {
        this.projetosPesquisa = projetosPesquisa;
    }

    public List<ProjetoExtensao> getProjetosExtensao() {
        return projetosExtensao;
    }

    public void setProjetosExtensao(List<ProjetoExtensao> projetosExtensao) {
        this.projetosExtensao = projetosExtensao;
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

    public ProjetoPesquisa getProjetoPesquisaSelecionado() {
        return projetoPesquisaSelecionado;
    }

    public void setProjetoPesquisaSelecionado(ProjetoPesquisa projetoPesquisaSelecionado) {
        this.projetoPesquisaSelecionado = projetoPesquisaSelecionado;
    }

    public ProjetoExtensao getProjetoExtensaoSelecionado() {
        return projetoExtensaoSelecionado;
    }

    public void setProjetoExtensaoSelecionado(ProjetoExtensao projetoExtensaoSelecionado) {
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

    public List<ProjetoPesquisa> getProjetosPesquisaColab() {
        return projetosPesquisaColab;
    }

    public void setProjetosPesquisaColab(List<ProjetoPesquisa> projetosPesquisaColab) {
        this.projetosPesquisaColab = projetosPesquisaColab;
    }

    public List<ProjetoExtensao> getProjetosExtensaoColab() {
        return projetosExtensaoColab;
    }

    public void setProjetosExtensaoColab(List<ProjetoExtensao> projetosExtensaoColab) {
        this.projetosExtensaoColab = projetosExtensaoColab;
    }

}
