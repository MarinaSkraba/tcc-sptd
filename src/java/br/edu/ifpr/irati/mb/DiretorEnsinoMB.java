/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.DiretorEnsino;
import br.edu.ifpr.irati.modelo.PTD;
import br.edu.ifpr.irati.modelo.Usuario;
import br.edu.ifpr.irati.util.Digest;
import br.edu.ifpr.irati.util.HashGenerationException;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class DiretorEnsinoMB {

    private DiretorEnsino diretorEnsino;
    private PTD ptd;
    private List<PTD> ptds;
    private List<String> errosCadastroDiretorEnsino;
    private String confirmacaoSenha;

    public DiretorEnsinoMB() {

        ptd = new PTD();
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        ptds = ptdDAO.buscarTodos(PTD.class);

    }

    public String salvarDiretorEnsino() {

        Dao<DiretorEnsino> diretorEnsinoDAO = new GenericDAO<>(DiretorEnsino.class);
        if (getDiretorEnsino().getEmail().contains("@ifpr.edu.br")) {
            errosCadastroDiretorEnsino.add("O email informado deve ser institucional(@ifpr.edu.br)");
        } else {
            diretorEnsinoDAO.salvar(getDiretorEnsino());
            setDiretorEnsino(new DiretorEnsino());
        }

        return "/ adicionar html";
    }

    public String alterarDiretorEnsino() throws HashGenerationException {

        String senhaSHA512 = "";
        if (getDiretorEnsino().getSenhaAlfanumerica().length() >= 8 && getDiretorEnsino().getSenhaAlfanumerica().length() <= 16) {

            senhaSHA512 = Digest.hashString(getDiretorEnsino().getSenhaAlfanumerica(), "SHA-512");

        } else if (getDiretorEnsino().getSenhaAlfanumerica().length() < 8 | getDiretorEnsino().getSenhaAlfanumerica().length() > 16) {

            getErrosCadastroDiretorEnsino().add("Sua senha deve conter entre de 8 a 16 caracteres");

        } else if (getDiretorEnsino().getSenhaAlfanumerica().equals(getConfirmacaoSenha()) == false) {

            getErrosCadastroDiretorEnsino().add("As senhas informadas não coincidem");

        }
        Date dataAtual = new Date();
        if (getDiretorEnsino().getDataContratacao().after(dataAtual)) {

            getErrosCadastroDiretorEnsino().add("A data que você inseriu como sua data de contratação "
                    + "é posterior a data atual");

        } else if (getDiretorEnsino().getEmail().contains("@ifpr.edu.br") == false) {

            getErrosCadastroDiretorEnsino().add("O email deve ser institucional(@ifpr.edu.br)");

        }
        if (getErrosCadastroDiretorEnsino().isEmpty() == true) {

            Dao<Usuario> usuarioDAO = new GenericDAO<>(Usuario.class);
            Usuario u = new Usuario(getDiretorEnsino().getIdUsuario(), getDiretorEnsino().getNomeCompleto(), getDiretorEnsino().getEmail(), getDiretorEnsino().getImagemPerfil(), senhaSHA512, "Habilitado");
            Dao<DiretorEnsino> diretorEnsinoDAO = new GenericDAO<>(DiretorEnsino.class);
            usuarioDAO.alterar(u);
            diretorEnsinoDAO.alterar(getDiretorEnsino());
            setDiretorEnsino(new DiretorEnsino());

        }
        return "";
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
}
