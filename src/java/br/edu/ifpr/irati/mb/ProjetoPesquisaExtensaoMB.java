package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.Participacao;
import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.modelo.ProjetoExtensao;
import br.edu.ifpr.irati.modelo.ProjetoPesquisa;
import br.edu.ifpr.irati.modelo.Usuario;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ProjetoPesquisaExtensaoMB {

    private ProjetoPesquisa projetoPesquisa;
    private ProjetoExtensao projetoExtensao;
    private List<ProjetoPesquisa> projetosPesquisa;
    private List<ProjetoExtensao> projetosExtensao;
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
        Dao<ProjetoPesquisa> projetoPesquisaDAO = new GenericDAO<>(ProjetoPesquisa.class);
        projetosPesquisa = projetoPesquisaDAO.buscarTodos(ProjetoPesquisa.class);
        Dao<ProjetoExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoExtensao.class);
        projetosExtensao = projetoExtensaoDAO.buscarTodos(ProjetoExtensao.class);
    }

    public ProjetoPesquisaExtensaoMB(String tipoProjetoAutor, String tipoProjetoColab) {

        this.tipoProjetoAutor = tipoProjetoAutor;
        this.tipoProjetoColab = tipoProjetoColab;
    }

    public void salvarProjetoPesquisaExtensaoAutor(Professor professorAutor) {
        switch (tipoProjetoAutor) {
            case "Pesquisa": {
                Dao<ProjetoExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoExtensao.class);
                Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
                participacao.setRotulo("Autor");
                participacao.setEstadoParticipacao("Ativo");
                Participacao p = new Participacao(participacao.getIdParticipacao(), participacao.getRotulo(), participacao.getEstadoParticipacao(), professorAutor);
                participacaoDAO.salvar(p);
                Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
                horarioDAO.salvar(horario);
                projetoExtensao.getParticipacoes().add(p);
                projetoExtensao.getHorariosProjetoExtensao().add(horario);
                ProjetoExtensao pE = new ProjetoExtensao(projetoExtensao.getIdProjetoExtensao(), projetoExtensao.getEstadoProjetoExtensao(), projetoExtensao.getNumeroProcesso(), projetoExtensao.getTituloProcesso(), projetoExtensao.getPrevisaoConclusao(), projetoExtensao.getInstituicaoPesquisa(), projetoExtensao.getHorariosProjetoExtensao(), projetoExtensao.getParticipacoes());
                projetoExtensaoDAO.salvar(pE);
                projetosExtensao = projetoExtensaoDAO.buscarTodos(ProjetoExtensao.class);
                break;
            }
            case "Extensão": {
                Dao<ProjetoExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoExtensao.class);
                Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
                participacao.setRotulo("Autor");
                participacao.setEstadoParticipacao("Ativo");
                Participacao p = new Participacao(participacao.getIdParticipacao(), participacao.getRotulo(), participacao.getEstadoParticipacao());
                participacaoDAO.salvar(p);
                Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
                horarioDAO.salvar(horario);
                projetoExtensao.getParticipacoes().add(p);
                projetoExtensao.getHorariosProjetoExtensao().add(horario);
                ProjetoExtensao pE = new ProjetoExtensao(projetoExtensao.getIdProjetoExtensao(), projetoExtensao.getEstadoProjetoExtensao(), projetoExtensao.getNumeroProcesso(), projetoExtensao.getTituloProcesso(), projetoExtensao.getPrevisaoConclusao(), projetoExtensao.getInstituicaoPesquisa(), projetoExtensao.getHorariosProjetoExtensao(), projetoExtensao.getParticipacoes());
                projetoExtensaoDAO.salvar(pE);
                projetosExtensao = projetoExtensaoDAO.buscarTodos(ProjetoExtensao.class);
                break;
            }
        }

    }

    public void salvarColaboracaoPesquisaExtensao(Professor professorColaborador) {
        switch (tipoProjetoColab) {
            case "Pesquisa": {
                Dao<ProjetoPesquisa> projetoPesquisaDAO = new GenericDAO<>(ProjetoPesquisa.class);
                Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
                participacao.setRotulo("Colaborador");
                participacao.setEstadoParticipacao("Ativo");
                Participacao p = new Participacao(participacao.getIdParticipacao(), participacao.getRotulo(), participacao.getEstadoParticipacao(),professorColaborador);
                participacaoDAO.salvar(p);
                Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
                horarioDAO.salvar(horario);
                projetoPesquisaSelecionado.getParticipacoes().add(p);
                projetoPesquisaSelecionado.getHorariosProjetoPesquisa().add(horario);
                projetoPesquisaDAO.alterar(projetoPesquisaSelecionado);
                break;
            }
            case "Extensão": {
                Dao<ProjetoExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoExtensao.class);
                Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
                participacao.setRotulo("Colaborador");
                participacao.setEstadoParticipacao("Ativo");
                Participacao p = new Participacao(participacao.getIdParticipacao(), participacao.getRotulo(), participacao.getEstadoParticipacao());
                participacaoDAO.salvar(p);
                Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
                horarioDAO.salvar(horario);
                projetoExtensaoSelecionado.getParticipacoes().add(p);
                projetoExtensaoSelecionado.getHorariosProjetoExtensao().add(horario);
                projetoExtensaoDAO.alterar(projetoExtensaoSelecionado);
                break;
            }
        }

    }

    public String alterarProjetoExtensao(ProjetoExtensao projetoExtensao) {
        this.projetoExtensao = projetoExtensao;
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

}
