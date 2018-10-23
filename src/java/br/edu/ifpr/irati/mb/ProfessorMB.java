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

    }

    public void alterarProfessor() throws HashGenerationException {

        String senhaSHA512 = "";
        Dao<Professor> professorDAO = new GenericDAO<>(Professor.class);
        senhaSHA512 = Digest.hashString(getProfessor().getSenhaAlfanumerica(), "SHA-512");
        professorSelecionado.setSenhaAlfanumerica(senhaSHA512);
        professorDAO.alterar(getProfessorSelecionado());
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
