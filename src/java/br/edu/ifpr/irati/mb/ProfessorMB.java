package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.modelo.Usuario;
import br.edu.ifpr.irati.util.Digest;
import br.edu.ifpr.irati.util.HashGenerationException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ProfessorMB {

    private Professor professor;
    private Professor professorSelecionado;
    private List<Professor> professores;
    private List<String> errosCadastroProfessor;
    private String confirmacaoSenha;
    private String confirmacaoSenhaSelecionada;

    public ProfessorMB() {

        professor = new Professor();
        professorSelecionado = new Professor();
        professores = new ArrayList();
        confirmacaoSenha = "";
        confirmacaoSenhaSelecionada = "";
        errosCadastroProfessor = new ArrayList<>();

    }

    public void salvarProfessor() throws HashGenerationException {

        String senhaSHA512 = "";
        Dao<Usuario> usuarioDAO = new GenericDAO<>(Usuario.class);
        Dao<Professor> professorDAO = new GenericDAO<>(Professor.class);

        senhaSHA512 = Digest.hashString(getProfessor().getSenhaAlfanumerica(), "SHA-512");
        Usuario usuario = new Usuario(professor.getIdUsuario(), professor.getNomeCompleto(), professor.getEmail(),
                professor.getImagemPerfil(), senhaSHA512, "Desabilitado");
        usuarioDAO.salvar(usuario);
        professorDAO.salvar(professor);
        professores = professorDAO.buscarTodos(Professor.class);

    }

    public void verificarErrosCadastro() {

        errosCadastroProfessor = new ArrayList<>();

        if (professor.getSenhaAlfanumerica().length() < 8 | professor.getSenhaAlfanumerica().length() > 16) {

            errosCadastroProfessor.add("Sua senha deve conter entre de 8 a 16 caracteres");

        } else if (professor.getSenhaAlfanumerica().equals(confirmacaoSenha) == false) {

            errosCadastroProfessor.add("As senhas informadas não coincidem");

        }
        Date dataAtual = new Date();
        if (professor.getDataContratacao().after(dataAtual)) {

            errosCadastroProfessor.add("A data que você inseriu como sua data de contratação "
                    + "é posterior a data atual");

        } else if (professor.getEmail().contains("@ifpr.edu.br") == false) {

            errosCadastroProfessor.add("O email deve ser institucional(@ifpr.edu.br)");

        } else if(professor.getEmail().isEmpty() == true){
            
            errosCadastroProfessor.add("O campo 'Email' deve ser obrigatoriamente preenchido");
            
        }else if(professor.getMatriculaSIAPE().isEmpty() == true){
            
            errosCadastroProfessor.add("O campo 'Matrícula SIAPE' deve ser obrigatoriamente preenchido");
            
        }else if(professor.getNomeCompleto().isEmpty() == true){
           
            errosCadastroProfessor.add("O campo 'Nome completo' deve ser obrigatoriamente preenchido");
            
        }else if(professor.getRegimeTrabalho().isEmpty() == true){
            
            errosCadastroProfessor.add("O campo 'Regime de trabalho' deve ser obrigatoriamente preenchido");
            
        }else if(professor.getSenhaAlfanumerica().isEmpty() == true){
            
            errosCadastroProfessor.add("O campo 'Senha' deve ser obrigatoriamente preenchido");
            
        }else if(confirmacaoSenha.isEmpty() == true){
            
            errosCadastroProfessor.add("O campo 'Confirmação senha' deve ser obrigatoriamente preenchido");
            
        }else if(professor.getRegimeTrabalho().isEmpty() == true){
          
            errosCadastroProfessor.add("Você deve obrigatoriamente selcionar um opção em 'Regime trabalho'");
            
        }else if(professor.getTipoContrato().isEmpty() == true){
            
        }
    }
    
    public void verificarErrosAtualizacao() {

        errosCadastroProfessor = new ArrayList<>();

        if (professorSelecionado.getSenhaAlfanumerica().length() < 8 | professor.getSenhaAlfanumerica().length() > 16) {

            errosCadastroProfessor.add("Sua senha deve conter entre de 8 a 16 caracteres");

        } else if (professorSelecionado.getSenhaAlfanumerica().equals(confirmacaoSenhaSelecionada) == false) {

            errosCadastroProfessor.add("As senhas informadas não coincidem");

        }
        Date dataAtual = new Date();
        if (professorSelecionado.getDataContratacao().after(dataAtual)) {

            errosCadastroProfessor.add("A data que você inseriu como sua data de contratação "
                    + "é posterior a data atual");

        } else if (professorSelecionado.getEmail().contains("@ifpr.edu.br") == false) {

            errosCadastroProfessor.add("O email deve ser institucional(@ifpr.edu.br)");

        } else if(professorSelecionado.getEmail().isEmpty() == true){
            
            errosCadastroProfessor.add("O campo 'Email' deve ser obrigatóriamente preenchido");
            
        }else if(professorSelecionado.getMatriculaSIAPE().isEmpty() == true){
            
            errosCadastroProfessor.add("O campo 'Matrícula SIAPE' deve ser obrigatóriamente preenchido");
            
        }else if(professorSelecionado.getNomeCompleto().isEmpty() == true){
           
            errosCadastroProfessor.add("O campo 'Nome completo' deve ser obrigatóriamente preenchido");
            
        }else if(professorSelecionado.getRegimeTrabalho().isEmpty() == true){
            
            errosCadastroProfessor.add("O campo 'Regime de trabalho' deve ser obrigatóriamente preenchido");
            
        }else if(professorSelecionado.getSenhaAlfanumerica().isEmpty() == true){
            
            errosCadastroProfessor.add("O campo 'Senha' deve ser obrigatóriamente preenchido");
            
        }else if(confirmacaoSenhaSelecionada.isEmpty() == true){
            
            errosCadastroProfessor.add("O campo 'Confirmação senha' deve ser obrigatóriamente preenchido");
            
        }
    }

    public String verificarPossibilidadeCadastro() {

        verificarErrosCadastro();
        String nomeCaixaRetorno = "";

        if (errosCadastroProfessor.isEmpty() == false) {

            nomeCaixaRetorno = "erroCadastroDocenteDialog";
            return nomeCaixaRetorno;

        } else {

            nomeCaixaRetorno = "confirmarCadastroDocenteDialog";
            return nomeCaixaRetorno;
        }

    } public String verificarPossibilidadeAtualizacao() {

        verificarErrosAtualizacao();
        String nomeCaixaRetorno = "";

        if (errosCadastroProfessor.isEmpty() == false) {

            nomeCaixaRetorno = "erroEdicaoDocenteDialog";
            return nomeCaixaRetorno;

        } else {

            nomeCaixaRetorno = "confirmarEdicaoDocenteDialog";
            return nomeCaixaRetorno;
        }

    }

    public void alterarProfessor() throws HashGenerationException {

        String senhaSHA512 = "";
        Dao<Professor> professorDAO = new GenericDAO<>(Professor.class);
        senhaSHA512 = Digest.hashString(professorSelecionado.getSenhaAlfanumerica(), "SHA-512");
        professorSelecionado.setSenhaAlfanumerica(senhaSHA512);
        professorDAO.alterar(professorSelecionado);
        List<Professor> ps = professorDAO.buscarTodos(Professor.class);
        professorSelecionado = ps.get(ps.size() - 1);
    }

    public void desabilitarProfessor(Professor professor) {
        Dao<Professor> professorDAO = new GenericDAO<>(Professor.class);
        professor.setEstadoUsuario("Desabilitado");
        professorDAO.alterar(professor);
        professores = professorDAO.buscarTodos(Professor.class);
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

    public List<String> getErrosCadastroProfessor() {
        return errosCadastroProfessor;
    }

    public void setErrosCadastroProfessor(List<String> errosCadastroProfessor) {
        this.errosCadastroProfessor = errosCadastroProfessor;
    }

    /**
     * @return the confirmacaoSenha
     */
    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    /**
     * @param confirmacaoSenha the confirmacaoSenha to set
     */
    public void setConfirmacaoSenha(String confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }

    /**
     * @return the professorSelecionado
     */
    public Professor getProfessorSelecionado() {
        return professorSelecionado;
    }

    /**
     * @param professorSelecionado the professorSelecionado to set
     */
    public void setProfessorSelecionado(Professor professorSelecionado) {
        this.professorSelecionado = professorSelecionado;
    }

    /**
     * @return the confirmacaoSenhaSelecionada
     */
    public String getConfirmacaoSenhaSelecionada() {
        return confirmacaoSenhaSelecionada;
    }

    /**
     * @param confirmacaoSenhaSelecionada the confirmacaoSenhaSelecionada to set
     */
    public void setConfirmacaoSenhaSelecionada(String confirmacaoSenhaSelecionada) {
        this.confirmacaoSenhaSelecionada = confirmacaoSenhaSelecionada;
    }

}
