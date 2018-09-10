package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.AtividadeASerPropostaDAO;
import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.dao.IAtividadeASerPropostaDao;
import br.edu.ifpr.irati.modelo.AtividadeASerProposta;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.PTD;
import java.io.Serializable;
import java.util.ArrayList;
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
        atividadesASeremPropostas = new ArrayList();
    }

    public void salvarAtividadeASerProposta(Serializable idUsuario, PTD ptd) {

        Dao<AtividadeASerProposta> atividadeASerPropostaDAO = new GenericDAO<>(AtividadeASerProposta.class);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        horarioDAO.salvar(horario);
        atividadeASerProposta.getHorariosAtividadesASerProposta().add(horario);
        atividadeASerProposta.setEstadoAtividadeASerProposta("Ativo");
        atividadeASerPropostaDAO.salvar(atividadeASerProposta);
        atividadeASerProposta = atividadeASerPropostaDAO.buscarTodos(AtividadeASerProposta.class).get(atividadeASerPropostaDAO.buscarTodos(AtividadeASerProposta.class).size()-1);
        ptd.getAtividadesASeremPropostas().add(atividadeASerProposta);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        ptdDAO.alterar(ptd);
        atividadeASerProposta = new AtividadeASerProposta();

    }

    public String alterarAtividadeASerProposta(AtividadeASerProposta atividadeASerProposta) {
        Dao<AtividadeASerProposta> atividadeASerPropostaDAO = new GenericDAO<>(AtividadeASerProposta.class);
        this.atividadeASerProposta = atividadeASerProposta;
        atividadeASerPropostaDAO.alterar(atividadeASerProposta);
        return "/adicionar aqui";
    }

    public String desabilitarAtividadeASerProposta(AtividadeASerProposta atividadeASerProposta) {
        Dao<AtividadeASerProposta> atividadeASerPropostaDAO = new GenericDAO<>(AtividadeASerProposta.class);
        atividadeASerProposta.setEstadoAtividadeASerProposta("Desativado");
        atividadeASerPropostaDAO.alterar(atividadeASerProposta);
        return "/adicionar html aqui";
    }

    public String excluirAtividadeASerProposta(AtividadeASerProposta atividadeASerProposta, PTD ptd) {
         Dao<AtividadeASerProposta> atividadeASerPropostaDAO = new GenericDAO<>(AtividadeASerProposta.class);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);

        for (int i = 0; i <= atividadeASerProposta.getHorariosAtividadesASerProposta().size(); i++) {
            horarioDAO.excluir(horario);
            atividadeASerProposta.getHorariosAtividadesASerProposta().remove(horario);
        }

        ptd.getAtividadesASeremPropostas().remove(atividadeASerProposta);
        ptdDAO.alterar(ptd);
        atividadeASerPropostaDAO.excluir(atividadeASerProposta);

        return "/adicionar aqui";
    }

    public void adicionarHorarioAtividadeASerProposta() {
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
