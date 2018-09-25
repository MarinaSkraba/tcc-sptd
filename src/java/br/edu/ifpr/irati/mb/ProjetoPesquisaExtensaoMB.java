package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.dao.IProjetoExtensaoDao;
import br.edu.ifpr.irati.dao.IProjetoPesquisaDao;
import br.edu.ifpr.irati.dao.ProjetoExtensaoDAO;
import br.edu.ifpr.irati.dao.ProjetoPesquisaDAO;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.PTD;
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
    private ProjetoPesquisa projetoPesquisaSelecionadoParaProjetoPesquisa;
    private ProjetoPesquisa projetoPesquisaSelecionadoParaHorario;
    private ProjetoExtensao projetoExtensaoSelecionadoParaProjetoExtensao;
    private ProjetoExtensao projetoExtensaoSelecionadoParaHorario;
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

        projetoExtensaoSelecionadoParaProjetoExtensao = new ProjetoExtensao();
        projetoExtensaoSelecionadoParaHorario = new ProjetoExtensao();
        projetoPesquisaSelecionadoParaProjetoPesquisa = new ProjetoPesquisa();
        projetoPesquisaSelecionadoParaHorario = new ProjetoPesquisa();
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

    public void salvarProjetoExtensaoAutor(Professor professorAutor, PTD ptd) {

        Dao<ProjetoExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoExtensao.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        participacao.setRotulo("Autor");
        participacao.setEstadoParticipacao("Ativo");
        participacao.setProfessor(professorAutor);
        participacaoDAO.salvar(participacao);
        projetoExtensao.getParticipacoes().add(participacao);
        projetoExtensaoDAO.salvar(projetoExtensao);
        projetoExtensao = projetoExtensaoDAO.buscarTodos(ProjetoExtensao.class).get(projetoExtensaoDAO.buscarTodos(ProjetoExtensao.class).size() - 1);
        ptd.getProjetosExtensao().add(projetoExtensao);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        ptdDAO.alterar(ptd);
        projetoExtensao = new ProjetoExtensao();
    }

    public void salvarProjetoPesquisaAutor(Professor professorAutor, PTD ptd) {
        Dao<ProjetoPesquisa> projetoPesquisaDAO = new GenericDAO<>(ProjetoPesquisa.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        participacao.setRotulo("Autor");
        participacao.setEstadoParticipacao("Ativo");
        participacao.setProfessor(professorAutor);
        participacaoDAO.salvar(participacao);
        projetoPesquisa.getParticipacoes().add(participacao);
        projetoPesquisaDAO.salvar(projetoPesquisa);
        projetoPesquisa = projetoPesquisaDAO.buscarTodos(ProjetoPesquisa.class).get(projetoPesquisaDAO.buscarTodos(ProjetoPesquisa.class).size() - 1);
        ptd.getProjetosPesquisa().add(projetoPesquisa);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        ptdDAO.alterar(ptd);
        projetoPesquisa = new ProjetoPesquisa();
    }

    public void salvarColaboracaoExtensao(Professor professorColaborador) {

        Dao<ProjetoExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoExtensao.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        participacao.setRotulo("Colaborador");
        participacao.setEstadoParticipacao("Ativo");
        participacao.setProfessor(professorColaborador);
        participacaoDAO.salvar(participacao);
        projetoExtensaoSelecionado.getParticipacoes().add(participacao);
        projetoExtensaoDAO.alterar(projetoExtensaoSelecionado);

    }

    public void salvarColaboracaoPesquisa(Professor professorColaborador) {
        Dao<ProjetoPesquisa> projetoPesquisaDAO = new GenericDAO<>(ProjetoPesquisa.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        participacao.setRotulo("Colaborador");
        participacao.setEstadoParticipacao("Ativo");
        participacao.setProfessor(professorColaborador);
        participacaoDAO.salvar(participacao);
        projetoPesquisaSelecionado.getParticipacoes().add(participacao);
        projetoPesquisaDAO.alterar(projetoPesquisaSelecionado);
        IProjetoPesquisaDao ppDAO = new ProjetoPesquisaDAO();
        projetosPesquisaColab = ppDAO.buscarProjetosPesquisaColabPorProfessor(professorColaborador.getIdUsuario());
    }

    public String alterarProjetoExtensao(ProjetoExtensao projetoExtensao) {
        Dao<ProjetoExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoExtensao.class);
        projetoExtensaoDAO.alterar(projetoExtensaoSelecionadoParaProjetoExtensao);
        projetoExtensaoSelecionadoParaProjetoExtensao = new ProjetoExtensao();
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String desabilitarProjetoExtensao(ProjetoExtensao projetoExtensao) {
        Dao<ProjetoExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoExtensao.class);
        projetoExtensao.setEstadoProjetoExtensao("Desativado");
        projetoExtensaoDAO.alterar(projetoExtensao);
        return "/adicionar html aqui";
    }

    public String excluirProjetoExtensao(ProjetoExtensao projetoExtensao, PTD ptd) {
        Dao<ProjetoExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoExtensao.class);
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
        ptd.getProjetosExtensao().remove(projetoExtensao);
        ptdDAO.alterar(ptd);
        projetoExtensaoDAO.excluir(projetoExtensao);

        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String alterarProjetoPesquisa(ProjetoPesquisa projetoPesquisa) {
        Dao<ProjetoPesquisa> projetoPesquisaDAO = new GenericDAO<>(ProjetoPesquisa.class
        );
        projetoPesquisaDAO.alterar(projetoPesquisaSelecionadoParaProjetoPesquisa);
        projetoPesquisaSelecionadoParaProjetoPesquisa = new ProjetoPesquisa();

        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String
            desabilitarProjetoPesquisa(ProjetoPesquisa projetoPesquisa) {
        Dao<ProjetoPesquisa> projetoPesquisaDAO = new GenericDAO<>(ProjetoPesquisa.class
        );
        projetoPesquisa.setEstadoProjetoPesquisa(
                "Desativado");
        projetoPesquisaDAO.alterar(projetoPesquisa);

        return "/adicionar html aqui";
    }

    public String excluirProjetoPesquisa(ProjetoPesquisa projetoPesquisa, PTD ptd) {
        Dao<ProjetoPesquisa> projetoPesquisaDAO = new GenericDAO<>(ProjetoPesquisa.class);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);

        List<Horario> aux = new ArrayList<>(projetoPesquisa.getHorariosProjetoPesquisa());
        for (Horario h : aux) {
            projetoPesquisa.getHorariosProjetoPesquisa().remove(h);
            projetoPesquisaDAO.alterar(projetoPesquisa);
            horarioDAO.excluir(h);
        }
        List<Participacao> aux2 = new ArrayList<>(projetoPesquisa.getParticipacoes());
        for (Participacao p : aux2) {
            projetoPesquisa.getParticipacoes().remove(p);
            projetoPesquisaDAO.alterar(projetoPesquisa);
            participacaoDAO.excluir(p);
        }
        ptd.getProjetosPesquisa().remove(projetoPesquisa);
        ptdDAO.alterar(ptd);
        projetoPesquisaDAO.excluir(projetoPesquisa);

        return "CriarCorrigirPTD?faces-redirect=true";
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

    public ProjetoPesquisa getProjetoPesquisaSelecionadoParaProjetoPesquisa() {
        return projetoPesquisaSelecionadoParaProjetoPesquisa;
    }

    public void setProjetoPesquisaSelecionadoParaProjetoPesquisa(ProjetoPesquisa projetoPesquisaSelecionadoParaProjetoPesquisa) {
        this.projetoPesquisaSelecionadoParaProjetoPesquisa = projetoPesquisaSelecionadoParaProjetoPesquisa;
    }

    public ProjetoPesquisa getProjetoPesquisaSelecionadoParaHorario() {
        return projetoPesquisaSelecionadoParaHorario;
    }

    public void setProjetoPesquisaSelecionadoParaHorario(ProjetoPesquisa projetoPesquisaSelecionadoParaHorario) {
        this.projetoPesquisaSelecionadoParaHorario = projetoPesquisaSelecionadoParaHorario;
    }

    public ProjetoExtensao getProjetoExtensaoSelecionadoParaProjetoExtensao() {
        return projetoExtensaoSelecionadoParaProjetoExtensao;
    }

    public void setProjetoExtensaoSelecionadoParaProjetoExtensao(ProjetoExtensao projetoExtensaoSelecionadoParaProjetoExtensao) {
        this.projetoExtensaoSelecionadoParaProjetoExtensao = projetoExtensaoSelecionadoParaProjetoExtensao;
    }

    public ProjetoExtensao getProjetoExtensaoSelecionadoParaHorario() {
        return projetoExtensaoSelecionadoParaHorario;
    }

    public void setProjetoExtensaoSelecionadoParaHorario(ProjetoExtensao projetoExtensaoSelecionadoParaHorario) {
        this.projetoExtensaoSelecionadoParaHorario = projetoExtensaoSelecionadoParaHorario;
    }
}
