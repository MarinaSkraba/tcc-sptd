package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.Participacao;
import br.edu.ifpr.irati.modelo.ProjetoExtensao;
import br.edu.ifpr.irati.modelo.ProjetoPesquisa;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ProjetoPesquisaExtensaoMB {

    private ProjetoPesquisa projetoPesquisa;
    private ProjetoExtensao projetoExtensao;
    private List<ProjetoPesquisa> projetosPesquisa;
    private List<ProjetoExtensao> projetosExtensao;
    private Horario horario;
    private Participacao participacao;
    private ProjetoPesquisa projetoPesquisaSelecionado;
    private ProjetoExtensao projetoExtensaoSelecionado;

    public ProjetoPesquisaExtensaoMB() {

        projetoPesquisa = new ProjetoPesquisa();
        projetoExtensao = new ProjetoExtensao();
        horario = new Horario();
        participacao = new Participacao();
        Dao<ProjetoPesquisa> projetoPesquisaDAO = new GenericDAO<>(ProjetoPesquisa.class);
        projetosPesquisa = projetoPesquisaDAO.buscarTodos(ProjetoPesquisa.class);
        Dao<ProjetoExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoExtensao.class);
        projetosExtensao = projetoExtensaoDAO.buscarTodos(ProjetoExtensao.class);
    }

    public void salvarProjetoPesquisaAutor() {

        Dao<ProjetoPesquisa> projetoPesquisaDAO = new GenericDAO<>(ProjetoPesquisa.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        Participacao p = new Participacao(participacao.getIdParticipacao(), "Autor");
        participacaoDAO.salvar(p);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        horarioDAO.salvar(horario);
        projetoPesquisa.getParticipacoes().add(p);
        projetoPesquisa.getHorariosProjetoPesquisa().add(horario);
        ProjetoPesquisa pP = new ProjetoPesquisa(projetoPesquisa.getIdProjetoPesquisa(), projetoPesquisa.getNumeroProcesso(), projetoPesquisa.getTituloProcesso(), projetoPesquisa.getPrevisaoConclusao(), projetoPesquisa.getInstituicaoPesquisa(), projetoPesquisa.getHorariosProjetoPesquisa(), projetoPesquisa.getParticipacoes());
        projetoPesquisaDAO.salvar(pP);
        projetosPesquisa = projetoPesquisaDAO.buscarTodos(ProjetoPesquisa.class);

    }

    public void salvarProjetoExtensaoAutor() {
        Dao<ProjetoExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoExtensao.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        Participacao p = new Participacao(participacao.getIdParticipacao(), "Autor");
        participacaoDAO.salvar(p);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        horarioDAO.salvar(horario);
        projetoExtensao.getParticipacoes().add(p);
        projetoExtensao.getHorariosProjetoExtensao().add(horario);
        ProjetoExtensao pE = new ProjetoExtensao(projetoExtensao.getIdProjetoExtensao(), projetoExtensao.getNumeroProcesso(), projetoExtensao.getTituloProcesso(), projetoExtensao.getPrevisaoConclusao(), projetoExtensao.getInstituicaoPesquisa(), projetoExtensao.getHorariosProjetoExtensao(), projetoExtensao.getParticipacoes());
        projetoExtensaoDAO.salvar(pE);
        projetosExtensao = projetoExtensaoDAO.buscarTodos(ProjetoExtensao.class);
    }

    public void salvarColaboracaoProjetoPesquisa() {

        Dao<ProjetoPesquisa> projetoPesquisaDAO = new GenericDAO<>(ProjetoPesquisa.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        Participacao p = new Participacao(participacao.getIdParticipacao(), "Colaborador");
        participacaoDAO.salvar(p);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        horarioDAO.salvar(horario);
        projetoPesquisaSelecionado.getParticipacoes().add(p);
        projetoPesquisaSelecionado.getHorariosProjetoPesquisa().add(horario);
        projetoPesquisaDAO.alterar(projetoPesquisaSelecionado);
                
    }

    public void salvarColaboracaoProjetoExtensao() {

        Dao<ProjetoExtensao> projetoExtensaoDAO = new GenericDAO<>(ProjetoExtensao.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        Participacao p = new Participacao(participacao.getIdParticipacao(), "Colaborador");
        participacaoDAO.salvar(p);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        horarioDAO.salvar(horario);
        projetoExtensaoSelecionado.getParticipacoes().add(p);
        projetoExtensaoSelecionado.getHorariosProjetoExtensao().add(horario);
        projetoExtensaoDAO.alterar(projetoExtensaoSelecionado);
        
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

}
