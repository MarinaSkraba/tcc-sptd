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
        AtividadeASerProposta atasp = new AtividadeASerProposta(atividadeASerProposta.getIdAtividadeASerProposta(), atividadeASerProposta.getRotulo(), atividadeASerProposta.getPeriodicidade(), atividadeASerProposta.getHorariosAtividadesASerProposta());
        atividadeASerPropostaDAO.salvar(atasp);
        atividadesASeremPropostas = atividadeASerPropostaDAO.buscarTodos(AtividadeASerProposta.class);

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

}
