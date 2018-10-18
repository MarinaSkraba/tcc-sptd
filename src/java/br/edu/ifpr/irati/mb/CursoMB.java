package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.Curso;
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
    
    public String salvarCurso() {
        Dao<Curso> cursoDAO = new GenericDAO<>(Curso.class);
        cursoDAO.salvar(curso);
        cursos = cursoDAO.buscarTodos(Curso.class);
        curso = new Curso();
        return "NotificacoesDiretorEnsino?faces-redirect=true";
    }
    
    public String alterarCurso() {
        Dao<Curso> cursoDAO = new GenericDAO<>(Curso.class);
        for (Curso c : cursos) {
            
            cursoDAO.alterar(c);
        }
        return "NotificacoesDiretorEnsino?faces-redirect=true";
    }
    
    public void excluirCurso(Curso curso) {
        Dao<Curso> cursoDAO = new GenericDAO<>(Curso.class);
        cursoDAO.excluir(curso);
        cursos = cursoDAO.buscarTodos(Curso.class);
        
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
