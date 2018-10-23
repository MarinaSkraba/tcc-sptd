package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.DiretorEnsino;
import br.edu.ifpr.irati.modelo.PTD;
import br.edu.ifpr.irati.modelo.Usuario;
import br.edu.ifpr.irati.util.Digest;
import br.edu.ifpr.irati.util.HashGenerationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class DiretorEnsinoMB {

    private DiretorEnsino diretorEnsino;
    private DiretorEnsino diretorEnsinoSelecionado;
    private PTD ptd;
    private List<PTD> ptds;
    private List<String> errosCadastroDiretorEnsino;
    private String confirmacaoSenhaSelecionado;

    public DiretorEnsinoMB() {

        ptd = new PTD();
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        ptds = ptdDAO.buscarTodos(PTD.class);
        errosCadastroDiretorEnsino = new ArrayList<>();
        diretorEnsino = new DiretorEnsino();
        diretorEnsinoSelecionado = new DiretorEnsino();
        confirmacaoSenhaSelecionado = "";

    }

    public void salvarDiretorEnsino() {

        Dao<DiretorEnsino> diretorEnsinoDAO = new GenericDAO<>(DiretorEnsino.class);
        if (getDiretorEnsino().getEmail().contains("@ifpr.edu.br") == true) {
            diretorEnsinoDAO.salvar(getDiretorEnsino());
            setDiretorEnsino(new DiretorEnsino());
        }

    }

    public void verificarErrosCadastroAtualizacaoDiretorEnsino() {

        if (getDiretorEnsinoSelecionado().getSenhaAlfanumerica().length() < 8 | getDiretorEnsino().getSenhaAlfanumerica().length() > 16) {

            getErrosCadastroDiretorEnsino().add("Sua senha deve conter entre de 8 a 16 caracteres");

        } else if (getDiretorEnsinoSelecionado().getSenhaAlfanumerica().equals(getConfirmacaoSenhaSelecionado()) == false) {

            getErrosCadastroDiretorEnsino().add("As senhas informadas não coincidem");

        }
        Date dataAtual = new Date();
        if (getDiretorEnsinoSelecionado().getDataContratacao().after(dataAtual)) {

            getErrosCadastroDiretorEnsino().add("A data que você inseriu como sua data de contratação "
                    + "é posterior a data atual");

        } else if (getDiretorEnsinoSelecionado().getEmail().contains("@ifpr.edu.br") == false) {

            getErrosCadastroDiretorEnsino().add("O email deve ser institucional(@ifpr.edu.br)");

        } else if (diretorEnsinoSelecionado.getEmail().isEmpty() == true) {

            errosCadastroDiretorEnsino.add("O campo 'Email' deve ser obrigatóriamente preenchido");

        } else if (diretorEnsinoSelecionado.getNomeCompleto().isEmpty() == true) {

            errosCadastroDiretorEnsino.add("O campo 'Nome completo' deve ser obrigatóriamente preenchido");

        } else if (diretorEnsinoSelecionado.getSenhaAlfanumerica().isEmpty() == true) {

            errosCadastroDiretorEnsino.add("O campo 'Senha' deve ser obrigatóriamente preenchido");

        } else if (confirmacaoSenhaSelecionado.isEmpty() == true) {

            errosCadastroDiretorEnsino.add("O campo 'Confirmação senha' deve ser obrigatóriamente preenchido");

        }
    }

    public String verificarPossibilidadeAtualizacaoDiretorEnsino() {

        verificarErrosCadastroAtualizacaoDiretorEnsino();
        String nomeCaixaRetorno = "";

        if (errosCadastroDiretorEnsino.isEmpty() == false) {

            nomeCaixaRetorno = "erroEdicaoDiretorEnsinoDialog";
            return nomeCaixaRetorno;

        } else {

            nomeCaixaRetorno = "confirmarEdicaoDiretorEnsinoDialog";
            return nomeCaixaRetorno;
        }

    }

    public void alterarDiretorEnsino() throws HashGenerationException {

        String senhaSHA512 = "";
        senhaSHA512 = Digest.hashString(diretorEnsinoSelecionado.getSenhaAlfanumerica(), "SHA-512");
        Dao<Usuario> usuarioDAO = new GenericDAO<>(Usuario.class);
        Usuario u = new Usuario(diretorEnsinoSelecionado.getIdUsuario(), diretorEnsinoSelecionado.getNomeCompleto(), diretorEnsinoSelecionado.getEmail(), diretorEnsinoSelecionado.getImagemPerfil(), senhaSHA512, "Habilitado");
        Dao<DiretorEnsino> diretorEnsinoDAO = new GenericDAO<>(DiretorEnsino.class);
        usuarioDAO.alterar(u);
        diretorEnsinoDAO.alterar(diretorEnsinoSelecionado);
        setDiretorEnsino(new DiretorEnsino());

    }

    public void desabilitarDiretorEnsino() {

        Dao<DiretorEnsino> diretorEnsinoDAO = new GenericDAO<>(DiretorEnsino.class);
        getDiretorEnsino().setEstadoUsuario("Desabilitado");
        diretorEnsinoDAO.alterar(getDiretorEnsino());

    }

    public void aprovarPTD(PTD ptd) {

        Dao<PTD> ptdSubmetidoDAO = new GenericDAO<>(PTD.class);
        ptd.setEstadoPTD("Aprovado");
        ptdSubmetidoDAO.alterar(ptd);

    }

    public void reprovarPTD(PTD ptd) {

        Dao<PTD> ptdSubmetidoDAO = new GenericDAO<>(PTD.class);
        ptd.setEstadoPTD("Reprovado");
        ptdSubmetidoDAO.alterar(ptd);

    }

    /**
     * @return the diretorEnsino
     */
    public DiretorEnsino getDiretorEnsino() {
        return diretorEnsino;
    }

    /**
     * @param diretorEnsino the diretorEnsino to set
     */
    public void setDiretorEnsino(DiretorEnsino diretorEnsino) {
        this.diretorEnsino = diretorEnsino;
    }

    public PTD getPtd() {
        return ptd;
    }

    public void setPtd(PTD ptd) {
        this.ptd = ptd;
    }

    public List<PTD> getPtds() {
        return ptds;
    }

    public void setPtds(List<PTD> ptds) {
        this.ptds = ptds;
    }

    /**
     * @return the errosCadastroDiretorEnsino
     */
    public List<String> getErrosCadastroDiretorEnsino() {
        return errosCadastroDiretorEnsino;
    }

    /**
     * @param errosCadastroDiretorEnsino the errosCadastroDiretorEnsino to set
     */
    public void setErrosCadastroDiretorEnsino(List<String> errosCadastroDiretorEnsino) {
        this.errosCadastroDiretorEnsino = errosCadastroDiretorEnsino;
    }

    /**
     * @return the confirmacaoSenha
     */

    /**
     * @return the diretorEnsinoSelecionado
     */
    public DiretorEnsino getDiretorEnsinoSelecionado() {
        return diretorEnsinoSelecionado;
    }

    /**
     * @param diretorEnsinoSelecionado the diretorEnsinoSelecionado to set
     */
    public void setDiretorEnsinoSelecionado(DiretorEnsino diretorEnsinoSelecionado) {
        this.diretorEnsinoSelecionado = diretorEnsinoSelecionado;
    }

    /**
     * @return the confirmacaoSenhaSelecionado
     */
    public String getConfirmacaoSenhaSelecionado() {
        return confirmacaoSenhaSelecionado;
    }

    /**
     * @param confirmacaoSenhaSelecionado the confirmacaoSenhaSelecionado to set
     */
    public void setConfirmacaoSenhaSelecionado(String confirmacaoSenhaSelecionado) {
        this.confirmacaoSenhaSelecionado = confirmacaoSenhaSelecionado;
    }
}
