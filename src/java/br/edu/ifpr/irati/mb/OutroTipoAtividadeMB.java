package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.dao.IOutroTipoAtividadeDao;
import br.edu.ifpr.irati.dao.OutroTipoAtividadeDAO;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.OutroTipoAtividade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class OutroTipoAtividadeMB {

    private OutroTipoAtividade outroTipoAtividade;
    private List<OutroTipoAtividade> outrosTiposAtividades;
    private Horario horario;
    private List<Horario> horarios;

    public OutroTipoAtividadeMB() {

        outroTipoAtividade = new OutroTipoAtividade();
        horario = new Horario();
        outrosTiposAtividades = new ArrayList();
    }

    public void salvarOutroTipoAtividade(Serializable idUsuario) {
        Dao<OutroTipoAtividade> outroTipoAtividadeDAO = new GenericDAO<>(OutroTipoAtividade.class);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        horarioDAO.salvar(horario);
        outroTipoAtividade.getHorariosOutroTipoAtividade().add(horario);
        outroTipoAtividade.setEstadoOutroTipoAtividade("Ativo");
        outroTipoAtividadeDAO.salvar(outroTipoAtividade);
        IOutroTipoAtividadeDao oDAO = new OutroTipoAtividadeDAO();
        outrosTiposAtividades = oDAO.buscarOutrosTipoAtividadesPorProfessor(idUsuario);

    }

    public String alterarOutroTipoAtividade(OutroTipoAtividade outroTipoAtividade) {
        Dao<OutroTipoAtividade> outroTipoAtividadeDAO = new GenericDAO<>(OutroTipoAtividade.class);
        this.outroTipoAtividade = outroTipoAtividade;
        outroTipoAtividadeDAO.alterar(outroTipoAtividade);
        return "/adicionar aqui";
    }

    public String desabilitarOutroTipoAtividade(OutroTipoAtividade outroTipoAtividade) {
        Dao<OutroTipoAtividade> outroTipoAtividadeDAO = new GenericDAO<>(OutroTipoAtividade.class);
        outroTipoAtividade.setEstadoOutroTipoAtividade("Desativado");
        outroTipoAtividadeDAO.alterar(outroTipoAtividade);
        return "/adicionar html aqui";
    }

    public String excluirOutroTipoAtividade(OutroTipoAtividade outroTipoAtividade) {
        Dao<OutroTipoAtividade> outroTipoAtividadeDAO = new GenericDAO<>(OutroTipoAtividade.class);
        outroTipoAtividadeDAO.excluir(outroTipoAtividade);
        outrosTiposAtividades = outroTipoAtividadeDAO.buscarTodos(OutroTipoAtividade.class);
        return "/adicionar aqui";
    }

    public void adicionarHorarioOutroTipoAtividade() {
        horarios.add(horario);
        horario = new Horario();
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

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

}
