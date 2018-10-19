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

    public DiretorEnsinoMB() {

        ptd = new PTD();
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        ptds = ptdDAO.buscarTodos(PTD.class);

    }

    public String salvarDiretorEnsino() {

        Dao<DiretorEnsino> diretorEnsinoDAO = new GenericDAO<>(DiretorEnsino.class);
        if (diretorEnsino.getEmail().contains("@ifpr.edu.br")) {
            errosCadastroDiretorEnsino.add("O email informado deve ser institucional(@ifpr.edu.br)");
        } else {
            diretorEnsinoDAO.salvar(diretorEnsino);
            diretorEnsino = new DiretorEnsino();
        }

        return "/ adicionar html";
    }

    public String alterarDiretorEnsino() throws HashGenerationException {

        String senhaSHA512 = "";
        if (diretorEnsino.getSenhaAlfanumerica().length() >= 8 && diretorEnsino.getSenhaAlfanumerica().length() <= 16) {

            senhaSHA512 = Digest.hashString(diretorEnsino.getSenhaAlfanumerica(), "SHA-512");

        } else if (diretorEnsino.getSenhaAlfanumerica().length() < 8 | diretorEnsino.getSenhaAlfanumerica().length() > 16) {

            errosCadastroDiretorEnsino.add("Sua senha deve conter entre de 8 a 16 caracteres");

        }
        Date dataAtual = new Date();
        if (diretorEnsino.getDataContratacao().after(dataAtual)) {

            errosCadastroDiretorEnsino.add("A data que você inseriu como sua data de contratação "
                    + "é posterior a data atual");

        } else if (diretorEnsino.getEmail().contains("@ifpr.edu.br") == false) {

            errosCadastroDiretorEnsino.add("O email deve ser institucional(@ifpr.edu.br)");

        }
        if (errosCadastroDiretorEnsino.isEmpty() == true) {

            Dao<Usuario> usuarioDAO = new GenericDAO<>(Usuario.class);
            Usuario u = new Usuario(diretorEnsino.getIdUsuario(), diretorEnsino.getNomeCompleto(), diretorEnsino.getEmail(), diretorEnsino.getImagemPerfil(), senhaSHA512, "Habilitado");
            Dao<DiretorEnsino> diretorEnsinoDAO = new GenericDAO<>(DiretorEnsino.class);
            usuarioDAO.alterar(u);
            diretorEnsinoDAO.alterar(diretorEnsino);
            diretorEnsino = new DiretorEnsino();

        }
        return "";
    }

    public void desabilitarDiretorEnsino() {

        Dao<DiretorEnsino> diretorEnsinoDAO = new GenericDAO<>(DiretorEnsino.class);
        diretorEnsino.setEstadoUsuario("Desabilitado");
        diretorEnsinoDAO.alterar(diretorEnsino);

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
}
