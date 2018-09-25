package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.dao.IOutroTipoAtividadeDao;
import br.edu.ifpr.irati.dao.OutroTipoAtividadeDAO;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.OutroTipoAtividade;
import br.edu.ifpr.irati.modelo.PTD;
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

    public void salvarOutroTipoAtividade(Serializable idUsuario, PTD ptd) {
        Dao<OutroTipoAtividade> outroTipoAtividadeDAO = new GenericDAO<>(OutroTipoAtividade.class);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        horarioDAO.salvar(horario);
        outroTipoAtividade.getHorariosOutroTipoAtividade().add(horario);
        outroTipoAtividadeDAO.salvar(outroTipoAtividade);
        outroTipoAtividade = outroTipoAtividadeDAO.buscarTodos(OutroTipoAtividade.class).get(outroTipoAtividadeDAO.buscarTodos(OutroTipoAtividade.class).size()-1);
        ptd.getOutrosTiposAtividades().add(outroTipoAtividade);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        ptdDAO.alterar(ptd);
        outroTipoAtividade = new OutroTipoAtividade();
    }

    public String alterarOutroTipoAtividade(OutroTipoAtividade outroTipoAtividade) {
        Dao<OutroTipoAtividade> outroTipoAtividadeDAO = new GenericDAO<>(OutroTipoAtividade.class);
        this.outroTipoAtividade = outroTipoAtividade;
        outroTipoAtividadeDAO.alterar(outroTipoAtividade);
        return "/adicionar aqui";
    }

    public String excluirOutroTipoAtividade(OutroTipoAtividade outroTipoAtividade, PTD ptd) {
        Dao<OutroTipoAtividade> outroTipoAtividadeDAO = new GenericDAO<>(OutroTipoAtividade.class);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);

        for (int i = 0; i <= outroTipoAtividade.getHorariosOutroTipoAtividade().size(); i++) {
            horarioDAO.excluir(horario);
            outroTipoAtividade.getHorariosOutroTipoAtividade().remove(horario);
        }

        ptd.getOutrosTiposAtividades().remove(outroTipoAtividade);
        ptdDAO.alterar(ptd);
        outroTipoAtividadeDAO.excluir(outroTipoAtividade);

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
