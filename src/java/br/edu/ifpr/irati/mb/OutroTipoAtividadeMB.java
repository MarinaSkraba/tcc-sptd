package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.OutroTipoAtividade;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class OutroTipoAtividadeMB {

    private OutroTipoAtividade outroTipoAtividade;
    private List<OutroTipoAtividade> outrosTiposAtividades;
    private Horario horario;

    public OutroTipoAtividadeMB() {

        outroTipoAtividade = new OutroTipoAtividade();
        horario = new Horario();
        Dao<OutroTipoAtividade> outroTipoAtividadeDAO = new GenericDAO<>(OutroTipoAtividade.class);
        outrosTiposAtividades = outroTipoAtividadeDAO.buscarTodos(OutroTipoAtividade.class);
    }

    public void salvar() {
        Dao<OutroTipoAtividade> outroTipoAtividadeDAO = new GenericDAO<>(OutroTipoAtividade.class);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        horarioDAO.salvar(horario);
        outroTipoAtividade.getHorariosOutroTipoAtividades().add(horario);
        OutroTipoAtividade ota = new OutroTipoAtividade(outroTipoAtividade.getIdOutroTipoAtividade(), outroTipoAtividade.getRotulo(), outroTipoAtividade.getPeriodicidade(), outroTipoAtividade.getHorariosOutroTipoAtividades());
        outroTipoAtividadeDAO.salvar(ota);
        outrosTiposAtividades = outroTipoAtividadeDAO.buscarTodos(OutroTipoAtividade.class);

    }

    public String alterar(OutroTipoAtividade outroTipoAtividade) {
        this.outroTipoAtividade = outroTipoAtividade;
        return "/adicionar aqui";
    }

    public String desabilitar(OutroTipoAtividade outroTipoAtividade) {
        //implementar depois
        return "";
    }
    public String excluir(OutroTipoAtividade outroTipoAtividade) {
        Dao<OutroTipoAtividade> outroTipoAtividadeDAO = new GenericDAO<>(OutroTipoAtividade.class);
        outroTipoAtividadeDAO.excluir(outroTipoAtividade);
        outrosTiposAtividades = outroTipoAtividadeDAO.buscarTodos(OutroTipoAtividade.class);
        return "/adicionar aqui";
    }

    public OutroTipoAtividade getOutroTipoAtividade() {
        return outroTipoAtividade;
    }

    public void setOutroTipoAtividade(OutroTipoAtividade outroTipoAtividade) {
        this.outroTipoAtividade = outroTipoAtividade;
    }

    public List<OutroTipoAtividade> getOutrosTiposAtividades() {
        return outrosTiposAtividades;
    }

    public void setOutrosTiposAtividades(List<OutroTipoAtividade> outrosTiposAtividades) {
        this.outrosTiposAtividades = outrosTiposAtividades;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

}
