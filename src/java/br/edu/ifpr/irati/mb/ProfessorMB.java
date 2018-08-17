package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.modelo.Professor;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ProfessorMB {

    private Professor professor;
    private List<Professor> professores;

    public ProfessorMB() {

        professor = new Professor();
        professores = new ArrayList();
    }
 
    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }
    
    // Código fictício:
    public String criarCorrigirPTD() {
        return "/CriarCorrigirPTD";
    }

}
