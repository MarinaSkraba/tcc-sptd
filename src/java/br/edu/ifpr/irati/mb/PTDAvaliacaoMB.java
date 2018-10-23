package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.Administracao;
import br.edu.ifpr.irati.modelo.Apoio;
import br.edu.ifpr.irati.modelo.DiretorEnsino;
import br.edu.ifpr.irati.modelo.PTD;
import br.edu.ifpr.irati.modelo.Participacao;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PTDAvaliacaoMB {

    private PTD ptdEmAvaliacao;
    private List<String> irregularidadesPTDAvaliacao;
    private List<Participacao> participacoesAutorPTDAvaliacao;
    private List<Participacao> participacoesColabPTDAvaliacao;

    public PTDAvaliacaoMB() {
        ptdEmAvaliacao = new PTD();
    }

    public String abrirPTDEmAvaliacao(PTD ptd) {
        setPtdEmAvaliacao(ptd);
        verificarCargaHorariaPTDEdicao();
        return "PTDEmAvaliacao";
    }
    
    public void verificarCargaHorariaPTDEdicao() {

        PTD ptd = new PTD();

        for (Administracao adm : getPtdEmAvaliacao().getAdministrativas()) {
            ptd.setCargaHorariaSecaoAdministracao(ptd.getCargaHorariaSecaoAdministracao()
                    + adm.getCargaHorariaSemanalAdministracao());
        }

        for (Participacao part : getPtdEmAvaliacao().getParticipacoes()) {
            if (part.getRotulo().equals("Autor")) {
                ptd.setCargaHorariaSecaoProjetoPesquisaExtensaoAutor(ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoAutor()
                        + part.getCargaHorariaSemanalParticipacao());
            } else if (part.getRotulo().equals("Colaborador")) {
                ptd.setCargaHorariaSecaoProjetoPesquisaExtensaoColab(ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoColab()
                        + part.getCargaHorariaSemanalParticipacao());
            }
        }
        if (getPtdEmAvaliacao().getProfessor().getRegimeTrabalho().equalsIgnoreCase("40h") | getPtdEmAvaliacao().getProfessor().getRegimeTrabalho().equalsIgnoreCase("Dedicação Exclusiva")) {
            if ((ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoAutor()
                    + ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoColab()) != 16) {
                if (ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoAutor()
                        + ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoColab() > 16) {

                    irregularidadesPTDAvaliacao.add("A carga horária de projetos de pesquisa e/ou extensão"
                            + " como colaborador e autor é superior à 16 horas!");

                } else if (ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoAutor() + ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoColab() < 16 && ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoAutor() + ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoColab() > 1) {

                    irregularidadesPTDAvaliacao.add("A carga horária de projetos de pesquisa e/ou extensão"
                            + " como colaborador e autor é inferior à 16 horas!");

                }
            }
        }
        for (Apoio ap : getPtdEmAvaliacao().getApoios()) {
            ptd.setCargaHorariaSecaoApoioEnsino(ptd.getCargaHorariaSecaoApoioEnsino() + ap.getCargaHorariaSemanalApoio());
        }
        if (ptd.getCargaHorariaSecaoApoioEnsino() != 4) {

            if (ptd.getCargaHorariaSecaoApoioEnsino() > 4 && (ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoAutor() + ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoColab()) != 0) {
                irregularidadesPTDAvaliacao.add("A carga horária é superior à 4 horas em Apoio ao Ensino!");
            } else if (ptd.getCargaHorariaSecaoApoioEnsino() < 4) {
                getIrregularidadesPTDEdicao().add("A carga horária é  inferior à 4 horas em Apoio ao Ensino!");
            }

        }

        for (Aula a : getPtd().getAulas()) {
            ptd.setCargaHorariaSecaoAulas(ptd.getCargaHorariaSecaoAulas() + a.getCargaHorariaTotal());

        }

        if (getPtd().getProfessor().getRegimeTrabalho().equals("20h")) {

            if (ptd.getCargaHorariaSecaoAulas() < 8) {

                getIrregularidadesPTDEdicao().add("A carga horária é inferior à 8 horas em Aula!");

            } else if (ptd.getCargaHorariaSecaoAulas() > 12) {

                getIrregularidadesPTDEdicao().add("A carga horária é superior à 12 horas em Aula!");

            }

        } else if (getPtd().getProfessor().getRegimeTrabalho().equalsIgnoreCase("40h") | getPtd().getProfessor().getRegimeTrabalho().equalsIgnoreCase("Dedicação Exclusiva")) {
            if (ptd.getCargaHorariaSecaoAulas() < 12) {

                getIrregularidadesPTDEdicao().add("A carga horária é inferior à 12 horas em Aula!");

            } else if (ptd.getCargaHorariaSecaoAulas() > 16) {

                if (ptd.getCargaHorariaSecaoApoioEnsino() > 4 && (ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoAutor() + ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoColab()) == 0) {

                    double excessoApoio = ptd.getCargaHorariaSecaoApoioEnsino() - 4;
                    double excessoEsperadoAula = 16 - excessoApoio;

                    if ((ptd.getCargaHorariaSecaoAulas() - excessoEsperadoAula) > 16) {

                        getIrregularidadesPTDEdicao().add("Mesmo descontando a carga horária redistribuída de projeto de pesquisa"
                                + "e/ou extensão para aula e apoio ao ensino, o componente aula apresenta carga horária"
                                + "superior à 16 horas");

                    }

                } else {
                    getIrregularidadesPTDEdicao().add("A carga horária é superior à 16 horas em Aula!");
                }

                if (ptd.getCargaHorariaSecaoAulas() >= 12 && ptd.getCargaHorariaSecaoAulas() <= 16) {
                    if ((ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoAutor() + ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoColab()) == 0) {

                        double excessoApoio = ptd.getCargaHorariaSecaoApoioEnsino() - 4;
                        double excessoEsperadoAula = 16 - excessoApoio;

                        if ((ptd.getCargaHorariaSecaoAulas() - excessoEsperadoAula) < 12) {

                            getIrregularidadesPTDEdicao().add("Descontando a carga horária redistribuída de projeto de pesquisa"
                                    + "e/ou extensão para aula e apoio ao ensino, o componente aula apresenta carga horária"
                                    + "inferior à 12 horas");

                        }
                    }
                }
            }
        }
        for (ManutencaoEnsino me : getPtd().getManutencoesEnsino()) {
            ptd.setCargaHorariaSecaoManutencaoEnsino(ptd.getCargaHorariaSecaoManutencaoEnsino() + me.getCargaHorariaSemanalManutencaoEnsino());
        }

        if (ptd.getCargaHorariaSecaoManutencaoEnsino() != 4) {
            if (ptd.getCargaHorariaSecaoManutencaoEnsino() < 4) {

                getIrregularidadesPTDEdicao().add("A carga horária é inferior"
                        + " à 4 horas em Manutenção ao Ensino");

            } else if (ptd.getCargaHorariaSecaoManutencaoEnsino() > 4) {

                getIrregularidadesPTDEdicao().add("A carga horária é superior "
                        + "à 4 horas em Manutenção ao Ensino!");

            }
        }

        for (OutroTipoAtividade ota : getPtd().getOutrosTiposAtividades()) {
            ptd.setCargaHorariaSecaoOutroTipoAtividade(ptd.getCargaHorariaSecaoOutroTipoAtividade() + ota.getCargaHorariaSemanalOutroTipoAtividade());
        }
        if (!getPtd().getApoios().isEmpty() && !getPtd().getAulas().isEmpty() && !getPtd().getManutencoesEnsino().isEmpty()) {
            if (getPtd().getProfessor().getRegimeTrabalho().equalsIgnoreCase("20h")) {
                if ((ptd.getCargaHorariaSecaoApoioEnsino() + ptd.getCargaHorariaSecaoAulas() + ptd.getCargaHorariaSecaoManutencaoEnsino()) < 8) {

                    irregularidadesPTDEdicao.add("A carga horária dedicada a Atividades de Ensino(apoio,manutenção e aulas) é inferior à 8 horas");

                } else if ((ptd.getCargaHorariaSecaoApoioEnsino() + ptd.getCargaHorariaSecaoAulas() + ptd.getCargaHorariaSecaoManutencaoEnsino()) > 20) {

                    irregularidadesPTDEdicao.add("A carga horária dedicada a Atividades de Ensino(apoio,manutenção e aulas) é superior à 12 horas");

                }
            }
            if (getPtd().getProfessor().getRegimeTrabalho().equalsIgnoreCase("40h") | getPtd().getProfessor().getRegimeTrabalho().equalsIgnoreCase("Dedicação Exclusiva")) {

                if ((ptd.getCargaHorariaSecaoApoioEnsino()
                        + ptd.getCargaHorariaSecaoAulas()
                        + ptd.getCargaHorariaSecaoManutencaoEnsino()) < 12) {

                    irregularidadesPTDEdicao.add("A carga horária dedicada a Atividades de Ensino(apoio,manutenção e aulas) é inferior à 12 horas");

                } else if ((ptd.getCargaHorariaSecaoApoioEnsino()
                        + ptd.getCargaHorariaSecaoAulas()
                        + ptd.getCargaHorariaSecaoManutencaoEnsino()) > 24) {

                    irregularidadesPTDEdicao.add("A carga horária dedicada a Atividades de Ensino(apoio,manutenção e aulas) é superior à 24 horas");

                }
            }
        }
        cargaHorariaTotalPTDAux = ptd.getCargaHorariaSecaoAdministracao()
                + ptd.getCargaHorariaSecaoApoioEnsino()
                + ptd.getCargaHorariaSecaoAulas()
                + ptd.getCargaHorariaSecaoManutencaoEnsino()
                + ptd.getCargaHorariaSecaoOutroTipoAtividade()
                + ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoAutor()
                + ptd.getCargaHorariaSecaoProjetoPesquisaExtensaoColab();
        double regime = 20;
        if ((getPtd().getProfessor().getRegimeTrabalho().equals("40h") | getPtd().getProfessor().getRegimeTrabalho().equals("Dedicação Exclusiva")) && cargaHorariaTotalPTDAux != 40) {

            irregularidadesPTDEdicao.add("A carga horária do PTD diverge com seu regime de trabalho de 40h");

        } else if (getPtd().getProfessor().getRegimeTrabalho().equals("20h") && cargaHorariaTotalPTDAux != 20) {

            irregularidadesPTDEdicao.add("A carga horária do PTD diverge com seu regime de trabalho de 20h");

        }
        if (getPtd().getProfessor().getRegimeTrabalho().equalsIgnoreCase("40h") | getPtd().getProfessor().getRegimeTrabalho().equalsIgnoreCase("Dedicação Exclusiva")) {
            regime = 40;
        }
        if (regime == cargaHorariaTotalPTDAux) {
            setEstadoCargaHorariaPTD("CORRETO");
        } else {
            setEstadoCargaHorariaPTD("INCORRETO");
        }

        for (String irregularidade : irregularidadesPTDEdicao) {
            getIrregularidadesPTDAvaliacao().add(irregularidade);
        }

        cargaHorariaTotalPTDPTDEdicao = cargaHorariaTotalPTDAux;

    }

    public String verificarPossibilidadeReprovacao() {
        String nomeCaixaDeAviso = "";
        Dao<PTD> ptdDAOGenerico = new GenericDAO<>(PTD.class);
        ptdDAOGenerico.alterar(getPtdEmAvaliacao());
        if (getPtdEmAvaliacao().getCampoObservacoesDiretorEnsino().equalsIgnoreCase("")) {

            nomeCaixaDeAviso = "avisoFalhaReprovacaoDialog";

        } else {

            nomeCaixaDeAviso = "avisoSucessoReprovacaoDialog";

        }
        return nomeCaixaDeAviso;
    }

    public void reprovarPTD() {

        Dao<PTD> ptdDAOGenerico = new GenericDAO<>(PTD.class);

        getPtdEmAvaliacao().setEstadoPTD("REPROVADO");
        getPtdEmAvaliacao().setDiretorEnsino(null);
        ptdDAOGenerico.alterar(getPtdEmAvaliacao());

    }

    public void aprovarPTD(DiretorEnsino diretorEnsino) {
        Dao<PTD> ptdDAOGenerico = new GenericDAO<>(PTD.class);
        getPtdEmAvaliacao().setEstadoPTD("APROVADO");
        getPtdEmAvaliacao().setDataAvaliacaoPTD(new Date());
        getPtdEmAvaliacao().setDiretorEnsino(diretorEnsino);
        ptdDAOGenerico.alterar(getPtdEmAvaliacao());
    }

    /**
     * @return the ptdEmAvaliacao
     */
    public PTD getPtdEmAvaliacao() {
        return ptdEmAvaliacao;
    }

    /**
     * @param ptdEmAvaliacao the ptdEmAvaliacao to set
     */
    public void setPtdEmAvaliacao(PTD ptdEmAvaliacao) {
        this.ptdEmAvaliacao = ptdEmAvaliacao;
    }

    /**
     * @return the participacoesAutorPTDAvaliacao
     */
    public List<Participacao> getParticipacoesAutorPTDAvaliacao() {
        return participacoesAutorPTDAvaliacao;
    }

    /**
     * @param participacoesAutorPTDAvaliacao the participacoesAutorPTDAvaliacao to set
     */
    public void setParticipacoesAutorPTDAvaliacao(List<Participacao> participacoesAutorPTDAvaliacao) {
        this.participacoesAutorPTDAvaliacao = participacoesAutorPTDAvaliacao;
    }

    /**
     * @return the participacoesColabPTDAvaliacao
     */
    public List<Participacao> getParticipacoesColabPTDAvaliacao() {
        return participacoesColabPTDAvaliacao;
    }

    /**
     * @param participacoesColabPTDAvaliacao the participacoesColabPTDAvaliacao to set
     */
    public void setParticipacoesColabPTDAvaliacao(List<Participacao> participacoesColabPTDAvaliacao) {
        this.participacoesColabPTDAvaliacao = participacoesColabPTDAvaliacao;
    }

    /**
     * @return the irregularidadesPTDAvaliacao
     */
    public List<String> getIrregularidadesPTDAvaliacao() {
        return irregularidadesPTDAvaliacao;
    }

    /**
     * @param irregularidadesPTDAvaliacao the irregularidadesPTDAvaliacao to set
     */
    public void setIrregularidadesPTDAvaliacao(List<String> irregularidadesPTDAvaliacao) {
        this.irregularidadesPTDAvaliacao = irregularidadesPTDAvaliacao;
    }

}
