package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.CursoDAO;
import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.dao.ICursoDAO;
import br.edu.ifpr.irati.modelo.Curso;
import br.edu.ifpr.irati.modelo.DiretorEnsino;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CursoMB {

    private Curso curso;
    private Curso cursoSelecionado;
    private List<Curso> cursos;

    public CursoMB() {
        cursoSelecionado = new Curso();
        curso = new Curso();
        Dao<Curso> cursoDAO = new GenericDAO<>(Curso.class);
        cursos = cursoDAO.buscarTodos(Curso.class);
    }

    public String salvarCurso(int idUsuario) {
        Dao<Curso> cursoDAO = new GenericDAO<>(Curso.class);
        Dao<DiretorEnsino> dEDAO = new GenericDAO<>(DiretorEnsino.class);
        ICursoDAO cursoDAOEspecifico = new CursoDAO();
        DiretorEnsino diretorEnsino = dEDAO.buscarPorId(idUsuario);
        curso.setDiretorEnsino(diretorEnsino);
        curso.setEstadoCurso("Ativo");
        cursoDAO.salvar(curso);
        cursos = cursoDAOEspecifico.buscarCursosAtivos();
        curso = new Curso();
        return "NotificacoesDiretorEnsino?faces-redirect=true";
    }

    public String alterarCurso() {
        Dao<Curso> cursoDAO = new GenericDAO<>(Curso.class);
        ICursoDAO cursoDAOEspecifico = new CursoDAO();
        for (Curso c : cursos) {
            cursoDAO.alterar(c);
        }
        cursos = cursoDAOEspecifico.buscarCursosAtivos();
        return "NotificacoesDiretorEnsino?faces-redirect=true";
    }

    public String excluirCurso(Curso curso) {
        Dao<Curso> cursoDAO = new GenericDAO<>(Curso.class);
        ICursoDAO cursoDAOEspecifico = new CursoDAO();
        curso.setEstadoCurso("Inativo");
        cursoDAO.excluir(curso);
        for (Curso c : cursos) {
            cursoDAO.alterar(c);
        }
        cursos = cursoDAOEspecifico.buscarCursosAtivos();
        return "NotificacoesDiretorEnsino?faces-redirect=true";
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Curso getCursoSelecionado() {
        return cursoSelecionado;
    }

    public void setCursoSelecionado(Curso cursoSelecionado) {
        this.cursoSelecionado = cursoSelecionado;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

}
