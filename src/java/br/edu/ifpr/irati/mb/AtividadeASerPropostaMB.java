package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.AtividadeASerProposta;
import br.edu.ifpr.irati.modelo.Horario;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class AtividadeASerPropostaMB {

    private AtividadeASerProposta atividadeASerProposta;
    private List<AtividadeASerProposta> atividadesASeremPropostas;
    private Horario horario;
    private List<Horario> horarios;

    public AtividadeASerPropostaMB() {

        atividadeASerProposta = new AtividadeASerProposta();
        horario = new Horario();
        Dao<AtividadeASerProposta> atividadeASerPropostaDAO = new GenericDAO<>(AtividadeASerProposta.class);
        atividadesASeremPropostas = atividadeASerPropostaDAO.buscarTodos(AtividadeASerProposta.class);
    }

    public void salvar() {

        Dao<AtividadeASerProposta> atividadeASerPropostaDAO = new GenericDAO<>(AtividadeASerProposta.class);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        horarioDAO.salvar(horario);
        atividadeASerProposta.getHorariosAtividadesASerProposta().add(horario);
        atividadeASerProposta.setEstadoAtividadeASerProposta("Ativo");
        AtividadeASerProposta atasp = new AtividadeASerProposta(atividadeASerProposta.getIdAtividadeASerProposta(), atividadeASerProposta.getEstadoAtividadeASerProposta(), atividadeASerProposta.getRotulo(), atividadeASerProposta.getPeriodicidade(), atividadeASerProposta.getHorariosAtividadesASerProposta());
        atividadeASerPropostaDAO.salvar(atasp);
        atividadesASeremPropostas = atividadeASerPropostaDAO.buscarTodos(AtividadeASerProposta.class);

    }

    public String alterar(AtividadeASerProposta atividadeASerProposta) {
        this.atividadeASerProposta = atividadeASerProposta;
        return "/adicionar aqui";
    }

    public String desabilitar(AtividadeASerProposta atividadeASerProposta) {
        Dao<AtividadeASerProposta> atividadeASerPropostaDAO = new GenericDAO<>(AtividadeASerProposta.class);
        atividadeASerProposta.setEstadoAtividadeASerProposta("Desativado");
        atividadeASerPropostaDAO.alterar(atividadeASerProposta);
        return "/adicionar html aqui";
    }

    public String excluir(AtividadeASerProposta atividadeASerProposta) {
        Dao<AtividadeASerProposta> atividadeASerPropostaDAO = new GenericDAO<>(AtividadeASerProposta.class);
        atividadeASerPropostaDAO.excluir(atividadeASerProposta);
        atividadesASeremPropostas = atividadeASerPropostaDAO.buscarTodos(AtividadeASerProposta.class);
        return "/adicionar aqui";
    }

    public void adicionarHorario() {
        horarios.add(horario);
        horario = new Horario();
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

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

}
