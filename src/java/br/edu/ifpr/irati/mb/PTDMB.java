package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.AdministracaoDAO;
import br.edu.ifpr.irati.dao.ApoioDAO;
import br.edu.ifpr.irati.dao.AtividadeASerPropostaDAO;
import br.edu.ifpr.irati.dao.AulaDAO;
import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.dao.IAdministracaoDao;
import br.edu.ifpr.irati.dao.IApoioDao;
import br.edu.ifpr.irati.dao.IAtividadeASerPropostaDao;
import br.edu.ifpr.irati.dao.IAulaDao;
import br.edu.ifpr.irati.dao.IManutencaoDao;
import br.edu.ifpr.irati.dao.IPTDDAO;
import br.edu.ifpr.irati.dao.IProjetoEnsinoDao;
import br.edu.ifpr.irati.dao.IProjetoPesquisaExtensaoDao;
import br.edu.ifpr.irati.dao.ManutencaoDAO;
import br.edu.ifpr.irati.dao.PTDDAO;
import br.edu.ifpr.irati.dao.ProjetoEnsinoDAO;
import br.edu.ifpr.irati.dao.ProjetoPesquisaExtensaoDAO;
import br.edu.ifpr.irati.modelo.Administracao;
import br.edu.ifpr.irati.modelo.Apoio;
import br.edu.ifpr.irati.modelo.AtividadeASerProposta;
import br.edu.ifpr.irati.modelo.Aula;
import br.edu.ifpr.irati.modelo.Curso;
import br.edu.ifpr.irati.modelo.DiretorEnsino;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.ManutencaoEnsino;
import br.edu.ifpr.irati.modelo.OutroTipoAtividade;
import br.edu.ifpr.irati.modelo.PTD;
import br.edu.ifpr.irati.modelo.Participacao;
import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.modelo.ProjetoPesquisaExtensao;
import br.edu.ifpr.irati.modelo.TipoAdministracao;
import br.edu.ifpr.irati.modelo.TipoApoio;
import br.edu.ifpr.irati.modelo.TipoManutencao;
import br.edu.ifpr.irati.modelo.TipoOferta;
import br.edu.ifpr.irati.modelo.Usuario;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.swing.text.Document;

@ManagedBean
@SessionScoped
public class PTDMB {

    private PTD ptd;
    private PTD ptdEmAvaliacao;
    private List<PTD> ptdsEmAvaliacao;
    private List<PTD> ptdsReprovados;
    private List<PTD> ptdsEmEdicao;
    private List<PTD> ptdsAprovados;
    private List<Participacao> participacoesAutor;
    private List<Participacao> participacoesColab;
    private double cargaHorariaTotalAdministracoes;
    private double cargaHorariaTotalApoios;
    private double cargaHorariaTotalAtividadesASeremPropostas;
    private double cargaHorariaTotalAulas;
    private double cargaHorariaTotalManutencoesEnsino;
    private double cargaHorariaTotalOutroTiposAtividade;
    private double cargaHorariaTotalProjetosPesquisaExtensaoAutor;
    private double cargaHorariaTotalProjetosPesquisaExtensaoColab;
    private double cargaHorariaTotalPTD;
//    private boolean obrigatoriedadeJustificativaAula;
//    private boolean obrigatoriedadeJustificativaApoio;
//    private boolean obrigatoriedadeJustificativaManutencaoEnsino;
//    private boolean obrigatoriedadeJustificativaPesquisaExtensao;
    private String estadoCargaHorariaPTD;
    private List<String> errosTabelaAula;
    private List<String> errosTabelaManuEnsino;
    private List<String> errosTabelaApoioEnsino;
    private List<String> errosTabelaPesquisaExtensaoAutor;
    private List<String> errosTabelaPesquisaExtensaoColaborador;
    private List<String> errosTabelaAdministrativas;
    private List<String> errosTabelaOutrasAtividades;
    private List<String> errosTabelaAtividadesASeremPropostas;
    private List<String> irregularidades;

    public PTDMB() {

        IPTDDAO ptdDAOEspecifico = new PTDDAO();
        ptd = new PTD();
        ptdEmAvaliacao = new PTD();
        ptdsEmAvaliacao = new ArrayList<>();
        ptdsReprovados = new ArrayList<>();
//        ptdsReprovados = ptdDAOEspecifico.buscarPTDsReprovados();
//        ptdsAprovados = ptdDAOEspecifico.buscarPTDsAprovados();
        ptdsEmEdicao = new ArrayList();
        ptdsEmAvaliacao = ptdDAOEspecifico.buscarPTDEmAvaliacao();
        participacoesAutor = new ArrayList<>();
        participacoesColab = new ArrayList<>();
        this.estadoCargaHorariaPTD = "";
        errosTabelaAula = new ArrayList<>();
        errosTabelaManuEnsino = new ArrayList<>();
        errosTabelaApoioEnsino = new ArrayList<>();
        errosTabelaPesquisaExtensaoAutor = new ArrayList<>();
        errosTabelaPesquisaExtensaoColaborador = new ArrayList<>();
        errosTabelaAdministrativas = new ArrayList<>();
        errosTabelaOutrasAtividades = new ArrayList<>();
        errosTabelaAtividadesASeremPropostas = new ArrayList<>();
        irregularidades = new ArrayList<>();

    }

    public void atualizarListasParticipacoes() {
        participacoesAutor = new ArrayList<>();
        participacoesColab = new ArrayList<>();
        for (Participacao part : ptd.getParticipacoes()) {
            if (part.getRotulo().equalsIgnoreCase("Autor")) {
                participacoesAutor.add(part);
            } else {
                participacoesColab.add(part);
            }
        }
    }

    public void realizarConferencias() {
        verificarErros();
        verificarCargaHorariaPTD();
    }

    public String abrirCriarCorrigirPTDEmBranco(Usuario usuario) {
        Dao<PTD> ptdDAOGenerico = new GenericDAO<>(PTD.class);
        Dao<Professor> professorDAOGenerico = new GenericDAO<>(Professor.class);
        IPTDDAO ptdDAOEspecifico = new PTDDAO();
        Professor p = professorDAOGenerico.buscarPorId(usuario.getIdUsuario());

        getPtd().setProfessor(p);
        setPtdsEmEdicao(ptdDAOEspecifico.buscarPTDsEmEdicao(p.getIdUsuario()));
        for (PTD ptdE : getPtdsEmEdicao()) {
            ptdE.setEstadoPTD("CANCELADO");
            ptdDAOGenerico.alterar(ptdE);
        }
        setPtd(new PTD());

        getPtd().setProfessor(p);

        getPtd().setDiretorEnsino(null);
        getPtd().setEstadoPTD("EDICAO");
        ptdDAOGenerico.salvar(getPtd());

        if (!ptdDAOEspecifico.buscarPTDsEmEdicao(p.getIdUsuario()).isEmpty()) {
            setPtd(ptdDAOEspecifico.buscarPTDsEmEdicao(p.getIdUsuario()).get(0));
        }

        return "/CriarCorrigirPTD";
    }

    public String abrirCriarCorrigirPTDContinuarEdicao(Usuario usuario) {

        IPTDDAO ptdDAOEspecifico = new PTDDAO();
        List<PTD> ptdEmEdicao = ptdDAOEspecifico.buscarPTDsEmEdicao(usuario.getIdUsuario());
        setPtd(ptdEmEdicao.get(0));
        return "/CriarCorrigirPTD";
    }

    public String abrirCriarCorrigirPTDAPartirDoUltimoArquivado(Usuario usuario) {
        Dao<PTD> ptdDAOGenerico = new GenericDAO<>(PTD.class);
        IPTDDAO ptdDAOEspecifico = new PTDDAO();
        List<PTD> ptdsEmEdicao = ptdDAOEspecifico.buscarPTDsEmEdicao(usuario.getIdUsuario());
        for (PTD ptdE : ptdsEmEdicao) {
            ptdE.setEstadoPTD("CANCELADO");
            ptdDAOGenerico.alterar(ptdE);
        }
        List<PTD> ptdsAprovados = ptdDAOEspecifico.buscarPTDsAprovados(usuario.getIdUsuario());

        if (ptdsAprovados.isEmpty()
                != true) {
            setPtd(ptdsAprovados.get(ptdsAprovados.size() - 1));
            getPtd().setIdPTD(0);
            getPtd().setEstadoPTD("EDICAO");
            ptdDAOGenerico.salvar(getPtd());
            setPtd(ptdDAOEspecifico.buscarPTDsEmEdicao(usuario.getIdUsuario()).get(0));
            return "/CriarCorrigirPTD";
        } else {

            return "/NotificacoesDocente";
        }
    }

    public String abrirCriarCorrigirPTDAPartirDeUmReprovado(PTD ptdReprovado) {
        Dao<PTD> ptdDAOGenerico = new GenericDAO<>(PTD.class);
        IPTDDAO ptdDAOEspecifico = new PTDDAO();
        List<PTD> ptdsEmEdicao = ptdDAOEspecifico.buscarPTDsEmEdicao(ptdReprovado.getProfessor().getIdUsuario());
        for (PTD ptdE : ptdsEmEdicao) {
            ptdE.setEstadoPTD("CANCELADO");
            ptdDAOGenerico.alterar(ptdE);
        }
        ptdReprovado.setEstadoPTD("EDICAO");
        ptdReprovado.setIdPTD(0);
        ptdDAOGenerico.salvar(ptdReprovado);
        setPtd(ptdDAOEspecifico.buscarPTDsEmEdicao(ptdReprovado.getProfessor().getIdUsuario()).get(0));
        return "/CriarCorrigirPTD";
    }

    public String cancelarPTD() {

        Dao<Administracao> administracaoDAO = new GenericDAO<>(PTD.class
        );
        Dao<TipoAdministracao> tipoAdministracaoDAO = new GenericDAO<>(TipoAdministracao.class
        );
        Dao<Apoio> apoioDAO = new GenericDAO<>(Apoio.class
        );
        Dao<TipoApoio> tipoApoioDAO = new GenericDAO<>(TipoApoio.class
        );
        Dao<AtividadeASerProposta> aASPropostaDAO = new GenericDAO<>(AtividadeASerProposta.class
        );
        Dao<Aula> aulaDAO = new GenericDAO<>(Aula.class
        );
        Dao<Curso> cursoDAO = new GenericDAO<>(Curso.class
        );
        Dao<DiretorEnsino> diretorEnsinoDAO = new GenericDAO<>(DiretorEnsino.class
        );
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class
        );
        Dao<ManutencaoEnsino> manutencaoDAO = new GenericDAO<>(ManutencaoEnsino.class
        );
        Dao<TipoManutencao> tipoManutencaoDAO = new GenericDAO<>(TipoManutencao.class
        );
        Dao<OutroTipoAtividade> oTAtividadeDAO = new GenericDAO<>(OutroTipoAtividade.class
        );
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class
        );
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class
        );
        Dao<Professor> professorDAO = new GenericDAO<>(Professor.class
        );
        Dao<ProjetoPesquisaExtensao> pPesquisaExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class
        );
        Dao<TipoOferta> tipoOfertaDAO = new GenericDAO<>(TipoOferta.class
        );
        Dao<Usuario> usuarioDAO = new GenericDAO<>(Usuario.class
        );

        for (Administracao adm : getPtd().getAdministrativas()) {

            List<Horario> aux = new ArrayList<>(adm.getHorariosAdministracao());
            for (Horario h : aux) {
                adm.getHorariosAdministracao().remove(h);
                administracaoDAO.alterar(adm);
                horarioDAO.excluir(h);
            }
            getPtd().getAdministrativas().remove(adm);
            ptdDAO.alterar(getPtd());
            administracaoDAO.excluir(adm);
            tipoAdministracaoDAO.excluir(adm.getTipoAdministracao());

        }

        for (Apoio apoio : getPtd().getApoios()) {
            List<Horario> aux = new ArrayList<>(apoio.getHorariosApoio());
            for (Horario h : aux) {
                apoio.getHorariosApoio().remove(h);
                apoioDAO.alterar(apoio);
                horarioDAO.excluir(h);
            }
            getPtd().getApoios().remove(apoio);
            ptdDAO.alterar(getPtd());
            apoioDAO.excluir(apoio);
            tipoApoioDAO.excluir(apoio.getTipoApoio());
        }

        for (AtividadeASerProposta aASP : getPtd().getAtividadesASeremPropostas()) {
            List<Horario> aux = new ArrayList<>(aASP.getHorariosAtividadesASerProposta());
            for (Horario h : aux) {
                aASP.getHorariosAtividadesASerProposta().remove(h);
                aASPropostaDAO.alterar(aASP);
                horarioDAO.excluir(h);
            }

            getPtd().getAtividadesASeremPropostas().remove(aASP);
            ptdDAO.alterar(getPtd());
            aASPropostaDAO.excluir(aASP);
        }

        for (Aula aula : getPtd().getAulas()) {
            List<Horario> aux = new ArrayList<>(aula.getHorariosAula());
            for (Horario h : aux) {
                aula.getHorariosAula().remove(h);
                aulaDAO.alterar(aula);
                horarioDAO.excluir(h);
            }
            getPtd().getAulas().remove(aula);
            ptdDAO.alterar(getPtd());
            aulaDAO.excluir(aula);
            tipoOfertaDAO.excluir(aula.getTipoOferta());
        }

        for (ManutencaoEnsino mEnsino : getPtd().getManutencoesEnsino()) {
            List<Horario> aux = new ArrayList<>(mEnsino.getHorariosManutecao());
            for (Horario h : aux) {
                mEnsino.getHorariosManutecao().remove(h);
                manutencaoDAO.alterar(mEnsino);
                horarioDAO.excluir(h);
            }
            getPtd().getManutencoesEnsino().remove(mEnsino);
            ptdDAO.alterar(getPtd());
            manutencaoDAO.excluir(mEnsino);
            tipoManutencaoDAO.excluir(mEnsino.getTipoManutencao());
        }

        for (OutroTipoAtividade oTA : getPtd().getOutrosTiposAtividades()) {
            List<Horario> aux = new ArrayList<>(oTA.getHorariosOutroTipoAtividade());
            for (Horario h : aux) {
                oTA.getHorariosOutroTipoAtividade().remove(h);
                oTAtividadeDAO.alterar(oTA);
                horarioDAO.excluir(h);
            }

            getPtd().getOutrosTiposAtividades().remove(oTA);
            ptdDAO.alterar(getPtd());
            oTAtividadeDAO.excluir(oTA);
        }

        for (Participacao p : getPtd().getParticipacoes()) {

            if (p.getRotulo().equals("Autor")) {

                pPesquisaExtensaoDAO.excluir(p.getProjetoPesquisaExtensao());

            }

            getPtd().getParticipacoes().remove(p);
            ptdDAO.alterar(getPtd());
            participacaoDAO.excluir(p);

        }

        return "NotificacoesDocente";
    }

    public String cancelarPTDEfetuandoLogout() {
        String variavelDescartavel = cancelarPTD();
        variavelDescartavel = "";
        UsuarioMB usuarioMB = new UsuarioMB();
        return usuarioMB.realizarLogout();
    }

    public String submeterPTD() {
        Dao<PTD> ptdDAOGenerico = new GenericDAO<>(PTD.class);
        getPtd().setEstadoPTD("AVALIACAO");
        ptdDAOGenerico.alterar(getPtd());

        return "/NotificacoesDocente";
    }

    public String verificarPossibilidadeSubmissao() {

        atualizarListasParticipacoes();

        // Conferência da existência de erros
        realizarConferencias();

        if (errosTabelaAdministrativas.isEmpty() != true) {
            return "avisoErrosDialog";
        }
        if (errosTabelaApoioEnsino.isEmpty() != true) {
            return "avisoErrosDialog";
        }
        if (errosTabelaAtividadesASeremPropostas.isEmpty() != true) {
            return "avisoErrosDialog";
        }
        if (errosTabelaAula.isEmpty() != true) {
            return "avisoErrosDialog";
        }
        if (errosTabelaManuEnsino.isEmpty() != true) {
            return "avisoErrosDialog";
        }
        if (errosTabelaOutrasAtividades.isEmpty() != true) {
            return "avisoErrosDialog";
        }
        if (errosTabelaPesquisaExtensaoAutor.isEmpty() != true) {
            return "avisoErrosDialog";
        }
        if (errosTabelaPesquisaExtensaoColaborador.isEmpty() != true) {
            return "avisoErrosDialog";

        }
        if (irregularidades.isEmpty() != true) {
            for (int i = 0; i <= irregularidades.size(); i++) {
                if ((irregularidades.get(i).equals("A carga horária é superior "
                        + "à 4 horas em Apoio ao Ensino!") | irregularidades.get(i).equals("A carga horária é inferior à 4 horas em Apoio ao Ensino!")) && ptd.getCampoJustificativaApoioEnsino().isEmpty() != true) {
                    return "avisoIrregularidadeDialog";

                } else if ((irregularidades.get(i).equals("A carga horária de projetos de pesquisa "
                        + "e/ou extensão como colaborador "
                        + "e autor é superior à 16 horas!") | irregularidades.get(i).equals("A carga horária de projetos"
                                + "de pesquisa e/ou extensão como "
                                + "colaborador e autor é inferior à 16 horas!")) && ptd.getCampoJustificativaProjetoPesquisaExtensao().isEmpty() != true) {

                    return "avisoIrregularidadeDialog";

                } else if ((irregularidades.get(i).equals("A carga horária é inferior à 4 horas"
                        + " em Manutenção ao Ensino") | irregularidades.get(i).equals("A carga horária é superior à 4"
                                + " horas em Manutenção ao Ensino")) && ptd.getCampoJustificativaManutencaoEnsino().isEmpty() != true) {

                    return "avisoIrregularidadeDialog";

                } else if ((irregularidades.get(i).equals("A carga horária é inferior "
                        + "à 8 horas em Aula!") | irregularidades.get(i).equals("A carga horária é superior "
                                + "à 12 horas em Aula!") | irregularidades.get(i).equals("A carga horária é inferior "
                                + "à 12 horas em Aula!") | irregularidades.get(i).equals("A carga horária é superior"
                                + " à 16 horas em Aula!") | irregularidades.get(i).equals("Mesmo descontando a carga horária redistribuída"
                                + "de projeto de pesquisa e/ou extensão para aula"
                                + " e apoio ao ensino, o componente aula apresenta"
                                + " carga horária superior à 16 horas") | irregularidades.get(i).equals("Descontando a carga horária redistribuída de projeto de pesquisa"
                                + " e/ou extensão para aula e apoio ao ensino,"
                                + " o componente aula apresenta carga horária inferior à 12 horas")) && ptd.getCampoJustificativaAtividadeEnsino().isEmpty() != true) {

                    return "avisoIrregularidadeDialog";

                } else {
                    return "conclusãoDialog";
                }

            }

        }
        return "confirmacaoIrregularidadeDialog";
    }

    public void verificarErros() {
        errosTabelaAdministrativas = new ArrayList();
        errosTabelaApoioEnsino = new ArrayList();
        errosTabelaAtividadesASeremPropostas = new ArrayList();
        errosTabelaAula = new ArrayList();
        errosTabelaManuEnsino = new ArrayList();
        errosTabelaOutrasAtividades = new ArrayList();
        errosTabelaPesquisaExtensaoAutor = new ArrayList();
        errosTabelaPesquisaExtensaoColaborador = new ArrayList();

        for (Administracao adm : getPtd().getAdministrativas()) {
            for (Horario h : adm.getHorariosAdministracao()) {

                if (h.getHoraInicio().getTime() > h.getHoraTermino().getTime()) {
                    errosTabelaAdministrativas.add("Você inseriu um horário de início posterior ao de término!");

                } else if (h.getHoraInicio().getTime() == 0) {
                    errosTabelaAdministrativas.add("Insira um Horário de Início!");

                } else if (h.getHoraTermino().getTime() == 0) {
                    errosTabelaAdministrativas.add("Insira um Horário de Término!");

                } else if (adm.getCargaHorariaSemanalAdministracao() == 0) {
                    errosTabelaAdministrativas.add("Carga Horária Nula!");

                }
            }
            if (adm.getTipoAdministracao().getRotulo().equalsIgnoreCase("")) {
                errosTabelaAdministrativas.add("Adicione um tipo à atividade de Administração");

            }
        }

        for (Apoio apoio : getPtd().getApoios()) {
            for (Horario h : apoio.getHorariosApoio()) {

                if (h.getHoraInicio().getTime() > h.getHoraTermino().getTime()) {
                    errosTabelaApoioEnsino.add("Você inseriu um horário de início posterior ao de término!");

                } else if (h.getHoraInicio().getTime() == 0) {
                    errosTabelaApoioEnsino.add("Insira um Horário de Início!");

                } else if (h.getHoraTermino().getTime() == 0) {
                    errosTabelaApoioEnsino.add("Insira um Horário de Término!");

                } else if (apoio.getCargaHorariaSemanalApoio() == 0) {
                    errosTabelaApoioEnsino.add("Carga Horária Nula!");

                }
            }
            if (apoio.getTipoApoio().getRotulo().equalsIgnoreCase("")) {
                errosTabelaApoioEnsino.add("Adicione um tipo à atividadade de apoio");

            }
        }

        for (AtividadeASerProposta aSP : getPtd().getAtividadesASeremPropostas()) {
            for (Horario h : aSP.getHorariosAtividadesASerProposta()) {

                if (h.getHoraInicio().getTime() > h.getHoraTermino().getTime()) {
                    errosTabelaAtividadesASeremPropostas.add("Você inseriu um horário de início posterior ao de término!");

                } else if (h.getHoraInicio().getTime() == 0) {
                    errosTabelaAtividadesASeremPropostas.add("Insira um Horário de Início!");

                } else if (h.getHoraTermino().getTime() == 0) {
                    errosTabelaAtividadesASeremPropostas.add("Insira um Horário de Término!");

                } else if (aSP.getCargaHorariaSemanalAtividadeASerProposta() == 0) {
                    errosTabelaAtividadesASeremPropostas.add("Carga Horária Nula!");

                }
            }
            if (aSP.getRotulo().equalsIgnoreCase("")) {
                errosTabelaAtividadesASeremPropostas.add("Adicione um rótulo à atividade a ser proposta");

            }
        }

        for (Aula aula : getPtd().getAulas()) {
            for (Horario h : aula.getHorariosAula()) {

                if (h.getHoraInicio().getTime() > h.getHoraTermino().getTime()) {
                    errosTabelaAula.add("Você inseriu um horário de início posterior ao de término!");

                } else if (h.getHoraInicio().getTime() == 0) {
                    errosTabelaAula.add("Insira um Horário de Início!");

                } else if (h.getHoraTermino().getTime() == 0) {
                    errosTabelaAula.add("Insira um Horário de Término!");

                } else if (aula.getCargaHorariaTotal() == 0) {
                    errosTabelaAula.add("Carga Horária Nula!");

                }
            }
            if (aula.getTipoOferta().getRotulo().equalsIgnoreCase("")) {
                errosTabelaAula.add("Adicione um tipo de oferta à atividade aula!");
            } else if (aula.getCurso() == null) {
                errosTabelaAula.add("Adicione um curso à atividade aula!");
            } else if (aula.getComponenteCurricular().equalsIgnoreCase("")) {
                errosTabelaAula.add("Adicione um título para o componente curricular!");
            }
        }

        for (ManutencaoEnsino mE : getPtd().getManutencoesEnsino()) {
            for (Horario h : mE.getHorariosManutecao()) {

                if (h.getHoraInicio().getTime() > h.getHoraTermino().getTime()) {
                    errosTabelaManuEnsino.add("Você inseriu um horário de início posterior ao de término!");
                } else if (h.getHoraInicio().getTime() == 0) {
                    errosTabelaManuEnsino.add("Insira um Horário de Início!");
                } else if (h.getHoraTermino().getTime() == 0) {
                    errosTabelaManuEnsino.add("Insira um Horário de Término!");
                } else if (mE.getCargaHorariaSemanalManutencaoEnsino() == 0) {
                    errosTabelaManuEnsino.add("Carga Horária Nula!");
                }
            }
            if (mE.getTipoManutencao().getRotulo().equalsIgnoreCase("")) {
                errosTabelaManuEnsino.add("Adicione um tipo à atividade de manutenção de ensino");
            }

        }

        for (OutroTipoAtividade oTA : getPtd().getOutrosTiposAtividades()) {
            for (Horario h : oTA.getHorariosOutroTipoAtividade()) {

                if (h.getHoraInicio().getTime() > h.getHoraTermino().getTime()) {
                    errosTabelaOutrasAtividades.add("Você inseriu um horário de início posterior ao de término!");
                } else if (h.getHoraInicio().getTime() == 0) {
                    errosTabelaOutrasAtividades.add("Insira um Horário de Início!");
                } else if (h.getHoraTermino().getTime() == 0) {
                    errosTabelaOutrasAtividades.add("Insira um Horário de Término!");
                }
                if (oTA.getCargaHorariaSemanalOutroTipoAtividade() == 0) {
                    errosTabelaOutrasAtividades.add("Carga Horária Nula!");
                }
            }
            if (oTA.getRotulo().equalsIgnoreCase("")) {
                errosTabelaOutrasAtividades.add("Insira um título!");
            }
        }

        for (Participacao p : getPtd().getParticipacoes()) {
            for (Horario h : p.getHorariosParticipacao()) {
                if (h.getHoraInicio().getTime() > h.getHoraTermino().getTime() && p.getRotulo().equals("Autor")) {
                    errosTabelaPesquisaExtensaoAutor.add("Você inseriu um horário de início posterior ao de término!");

                } else if (h.getHoraInicio().getTime() == 0) {
                    errosTabelaPesquisaExtensaoAutor.add("Insira um Horário de Início!");

                } else if (h.getHoraTermino().getTime() == 0) {
                    errosTabelaPesquisaExtensaoAutor.add("Insira um Horário de Término!");

                } else if (p.getCargaHorariaSemanalParticipacao() == 0) {
                    errosTabelaPesquisaExtensaoAutor.add("Carga Horária Nula!");

                }
            }
            if (p.getProjetoPesquisaExtensao().getTituloProcesso().equals("")) {
                errosTabelaPesquisaExtensaoAutor.add("Adicione um título ao projeto de pesquisa/extensão");

            }
            for (Participacao p2 : getPtd().getParticipacoes()) {
                if (p.getProjetoPesquisaExtensao().getTituloProcesso().equals(p2.getProjetoPesquisaExtensao().getTituloProcesso()) && p.getIdParticipacao() != p2.getIdParticipacao()) {
                    errosTabelaPesquisaExtensaoAutor.add("Você tem mais de uma participação no mesmo projeto, caso trabalhe nele em mais de um dia, adicione um novo horário!");

                } else if (p.getProjetoPesquisaExtensao().getNumeroProcesso().equals(p2.getProjetoPesquisaExtensao().getNumeroProcesso()) && p.getIdParticipacao() != p2.getIdParticipacao()) {
                    errosTabelaPesquisaExtensaoAutor.add("Você tem mais de uma participação no mesmo projeto, caso trabalhe nele em mais de um dia, adicione um novo horário!");
                }
            }

        }
    }

    public int verificarConteúdoListaParaOpacidade(List<String> lista) {
        if (lista.isEmpty()) {
            return 0;
        } else {
            return 1;
        }
    }

    public void verificarCargaHorariaPTD() {

        irregularidades = new ArrayList<>();
        cargaHorariaTotalAdministracoes = 0;
        cargaHorariaTotalApoios = 0;
        cargaHorariaTotalAtividadesASeremPropostas = 0;
        cargaHorariaTotalAulas = 0;
        cargaHorariaTotalManutencoesEnsino = 0;
        cargaHorariaTotalOutroTiposAtividade = 0;
        for (Administracao adm : getPtd().getAdministrativas()) {
            setCargaHorariaTotalAdministracoes(getCargaHorariaTotalAdministracoes() + adm.getCargaHorariaSemanalAdministracao());
        }

        if (!getPtd().getParticipacoes().isEmpty()) {
            for (Participacao part : getPtd().getParticipacoes()) {
                if (part.getRotulo().equals("AUTOR")) {
                    setCargaHorariaTotalProjetosPesquisaExtensaoAutor(getCargaHorariaTotalProjetosPesquisaExtensaoAutor() + part.getCargaHorariaSemanalParticipacao());
                } else if (part.getRotulo().equals("COLABORADOR")) {
                    setCargaHorariaTotalProjetosPesquisaExtensaoColab(getCargaHorariaTotalProjetosPesquisaExtensaoColab() + part.getCargaHorariaSemanalParticipacao());
                }
            }
            if ((getCargaHorariaTotalProjetosPesquisaExtensaoAutor() + getCargaHorariaTotalProjetosPesquisaExtensaoColab()) != 16) {
                if (getCargaHorariaTotalProjetosPesquisaExtensaoAutor() + getCargaHorariaTotalProjetosPesquisaExtensaoColab() > 16) {

                    getIrregularidades().add("A carga horária de projetos de pesquisa e/ou extensão"
                            + " como colaborador e autor é superior à 16 horas!");

                } else if (getCargaHorariaTotalProjetosPesquisaExtensaoAutor() + getCargaHorariaTotalProjetosPesquisaExtensaoColab() < 16 && getCargaHorariaTotalProjetosPesquisaExtensaoAutor() + getCargaHorariaTotalProjetosPesquisaExtensaoColab() > 1) {

                    getIrregularidades().add("A carga horária de projetos de pesquisa e/ou extensão"
                            + " como colaborador e autor é inferior à 16 horas!");

                }
            }
        }

        if (!getPtd().getApoios().isEmpty()) {
            for (Apoio ap : getPtd().getApoios()) {
                setCargaHorariaTotalApoios(getCargaHorariaTotalApoios() + ap.getCargaHorariaSemanalApoio());
            }
            if (getCargaHorariaTotalApoios() != 4) {

                if (getCargaHorariaTotalApoios() > 4 && (getCargaHorariaTotalProjetosPesquisaExtensaoAutor() + getCargaHorariaTotalProjetosPesquisaExtensaoColab()) != 0) {
                    getIrregularidades().add("A carga horária é superior à 4 horas em Apoio ao Ensino!");
                } else {
                    getIrregularidades().add("A carga horária é  inferior à 4 horas em Apoio ao Ensino!");
                }

            }
        }

        if (!getPtd().getAulas().isEmpty()) {
            for (Aula a : getPtd().getAulas()) {
                setCargaHorariaTotalAulas(getCargaHorariaTotalAulas() + a.getCargaHorariaTotal());

            }

            if (getPtd().getProfessor().getRegimeTrabalho().equals("20h")) {

                if (getCargaHorariaTotalAulas() < 8) {

                    getIrregularidades().add("A carga horária é inferior à 8 horas em Aula!");

                } else if (getCargaHorariaTotalAulas() > 12) {

                    getIrregularidades().add("A carga horária é superior à 12 horas em Aula!");

                }

            } else if (getPtd().getProfessor().getRegimeTrabalho().equalsIgnoreCase("40h") | getPtd().getProfessor().getRegimeTrabalho().equalsIgnoreCase("Dedicação Exclusiva")) {
                if (getCargaHorariaTotalAulas() < 12) {

                    getIrregularidades().add("A carga horária é inferior à 12 horas em Aula!");

                } else if (getCargaHorariaTotalAulas() > 16) {

                    if (getCargaHorariaTotalApoios() > 4 && (getCargaHorariaTotalProjetosPesquisaExtensaoAutor() + getCargaHorariaTotalProjetosPesquisaExtensaoColab()) == 0) {

                        double excessoApoio = getCargaHorariaTotalApoios() - 4;
                        double excessoEsperadoAula = 16 - excessoApoio;

                        if ((getCargaHorariaTotalAulas() - excessoEsperadoAula) > 16) {

                            getIrregularidades().add("Mesmo descontando a carga horária redistribuída de projeto de pesquisa"
                                    + "e/ou extensão para aula e apoio ao ensino, o componente aula apresenta carga horária"
                                    + "superior à 16 horas");

                        }

                    } else {
                        getIrregularidades().add("A carga horária é superior à 16 horas em Aula!");
                    }

                    if (getCargaHorariaTotalAulas() >= 12 && getCargaHorariaTotalAulas() <= 16) {
                        if ((getCargaHorariaTotalProjetosPesquisaExtensaoAutor() + getCargaHorariaTotalProjetosPesquisaExtensaoColab()) == 0) {

                            double excessoApoio = getCargaHorariaTotalApoios() - 4;
                            double excessoEsperadoAula = 16 - excessoApoio;

                            if ((getCargaHorariaTotalAulas() - excessoEsperadoAula) < 12) {

                                getIrregularidades().add("Descontando a carga horária redistribuída de projeto de pesquisa"
                                        + "e/ou extensão para aula e apoio ao ensino, o componente aula apresenta carga horária"
                                        + "inferior à 12 horas");

                            }
                        }
                    }
                }
            }

            if (!getPtd().getManutencoesEnsino().isEmpty()) {
                for (ManutencaoEnsino me : getPtd().getManutencoesEnsino()) {
                    setCargaHorariaTotalManutencoesEnsino(getCargaHorariaTotalManutencoesEnsino() + me.getCargaHorariaSemanalManutencaoEnsino());
                }

                if (getCargaHorariaTotalManutencoesEnsino() != 4) {
                    if (getCargaHorariaTotalManutencoesEnsino() < 4) {

                        getIrregularidades().add("A carga horária é inferior"
                                + " à 4 horas em Manutenção ao Ensino");

                    } else {

                        getIrregularidades().add("A carga horária é superior "
                                + "à 4 horas em Manutenção ao Ensino!");

                    }
                }
            }
            for (OutroTipoAtividade ota : getPtd().getOutrosTiposAtividades()) {
                setCargaHorariaTotalOutroTiposAtividade(getCargaHorariaTotalOutroTiposAtividade() + ota.getCargaHorariaSemanalOutroTipoAtividade());
            }

            setCargaHorariaTotalPTD(getCargaHorariaTotalAdministracoes() + getCargaHorariaTotalApoios() + getCargaHorariaTotalAulas() + getCargaHorariaTotalManutencoesEnsino() + getCargaHorariaTotalOutroTiposAtividade() + getCargaHorariaTotalProjetosPesquisaExtensaoAutor() + getCargaHorariaTotalProjetosPesquisaExtensaoColab());
            double regime = 20;
            if (getPtd().getProfessor().getRegimeTrabalho().equalsIgnoreCase("40h") | getPtd().getProfessor().getRegimeTrabalho().equalsIgnoreCase("Dedicação Exclusiva")) {
                regime = 40;
            }
            if (regime == getCargaHorariaTotalPTD()) {

                setEstadoCargaHorariaPTD("CORRETO");

            } else {

                setEstadoCargaHorariaPTD("INCORRETO");
            }

        }
    }

    public String salvarJustificativasEComentários() {
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        ptdDAO.alterar(ptd);
        return "CriarCorrigirPTD?faces-redirect=true";
    }

    public String verificacaoIrregularidadesNotificacoesDiretorEnsino(PTD ptd) {
        String resposta = "Correto";
        verificarCargaHorariaPTD();
        if (irregularidades.isEmpty() != true) {
            resposta = "Incorreto";
        }
        return resposta;
    }

    public String abrirPTDEmAvaliacao(PTD ptd) {
        setPtdEmAvaliacao(ptd);
        verificarCargaHorariaPTD();
        return "PTDEmAvaliacao";
    }

    public String reprovarPTD() {
        Dao<PTD> ptdDAOGenerico = new GenericDAO<>(PTD.class
        );
        getPtdEmAvaliacao().setEstadoPTD("REPROVADO");
        getPtdEmAvaliacao().setDiretorEnsino(null);
        ptdDAOGenerico.alterar(getPtdEmAvaliacao());

        return "/NotificacoesDiretorEnsino";
    }

    public String aprovarPTD(DiretorEnsino diretorEnsino) {
        Dao<PTD> ptdDAOGenerico = new GenericDAO<>(PTD.class);
        getPtdEmAvaliacao().setEstadoPTD("APROVADO");
        getPtdEmAvaliacao().setDiretorEnsino(diretorEnsino);
        ptdDAOGenerico.alterar(getPtdEmAvaliacao());

        return "/NotificacoesDiretorEnsino";
    }

    public PTD getPtd() {
        return ptd;
    }

    public void setPtd(PTD ptd) {
        this.ptd = ptd;
    }

    /**
     * @return the ptdsEmAvaliacao
     */
    public List<PTD> getPtdsEmAvaliacao() {
        IPTDDAO ptdDAOEspecifico = new PTDDAO();
        ptdsEmAvaliacao = ptdDAOEspecifico.buscarPTDEmAvaliacao();
        return ptdsEmAvaliacao;
    }

    /**
     * @param ptdsEmAvaliacao the pdtsEmAvaliacao to set
     */
    public void setPtdsEmAvaliacao(List<PTD> ptdsEmAvaliacao) {
        this.ptdsEmAvaliacao = ptdsEmAvaliacao;
    }

    public List<PTD> getPtdsEmEdicao() {
        return ptdsEmEdicao;
    }

    public void setPtdsEmEdicao(List<PTD> ptdsEmEdicao) {
        this.ptdsEmEdicao = ptdsEmEdicao;
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

    public double getCargaHorariaTotalAdministracoes() {
        return cargaHorariaTotalAdministracoes;
    }

    public void setCargaHorariaTotalAdministracoes(double cargaHorariaTotalAdministracoes) {
        this.cargaHorariaTotalAdministracoes = cargaHorariaTotalAdministracoes;
    }

    public double getCargaHorariaTotalApoios() {
        return cargaHorariaTotalApoios;
    }

    public void setCargaHorariaTotalApoios(double cargaHorariaTotalApoios) {
        this.cargaHorariaTotalApoios = cargaHorariaTotalApoios;
    }

    public double getCargaHorariaTotalAtividadesASeremPropostas() {
        return cargaHorariaTotalAtividadesASeremPropostas;
    }

    public void setCargaHorariaTotalAtividadesASeremPropostas(double cargaHorariaTotalAtividadesASeremPropostas) {
        this.cargaHorariaTotalAtividadesASeremPropostas = cargaHorariaTotalAtividadesASeremPropostas;
    }

    public double getCargaHorariaTotalAulas() {
        return cargaHorariaTotalAulas;
    }

    public void setCargaHorariaTotalAulas(double cargaHorariaTotalAulas) {
        this.cargaHorariaTotalAulas = cargaHorariaTotalAulas;
    }

    public double getCargaHorariaTotalManutencoesEnsino() {
        return cargaHorariaTotalManutencoesEnsino;
    }

    public void setCargaHorariaTotalManutencoesEnsino(double cargaHorariaTotalManutencoesEnsino) {
        this.cargaHorariaTotalManutencoesEnsino = cargaHorariaTotalManutencoesEnsino;
    }

    public double getCargaHorariaTotalOutroTiposAtividade() {
        return cargaHorariaTotalOutroTiposAtividade;
    }

    public void setCargaHorariaTotalOutroTiposAtividade(double cargaHorariaTotalOutroTiposAtividade) {
        this.cargaHorariaTotalOutroTiposAtividade = cargaHorariaTotalOutroTiposAtividade;
    }

    public double getCargaHorariaTotalPTD() {
        return cargaHorariaTotalPTD;
    }

    public void setCargaHorariaTotalPTD(double cargaHorariaTotalPTD) {
        this.cargaHorariaTotalPTD = cargaHorariaTotalPTD;
    }

    public String getEstadoCargaHorariaPTD() {
        return estadoCargaHorariaPTD;
    }

    public void setEstadoCargaHorariaPTD(String estadoCargaHorariaPTD) {
        this.estadoCargaHorariaPTD = estadoCargaHorariaPTD;
    }

    public double getCargaHorariaTotalProjetosPesquisaExtensaoAutor() {
        return cargaHorariaTotalProjetosPesquisaExtensaoAutor;
    }

    public void setCargaHorariaTotalProjetosPesquisaExtensaoAutor(double cargaHorariaTotalProjetosPesquisaExtensaoAutor) {
        this.cargaHorariaTotalProjetosPesquisaExtensaoAutor = cargaHorariaTotalProjetosPesquisaExtensaoAutor;
    }

    public double getCargaHorariaTotalProjetosPesquisaExtensaoColab() {
        return cargaHorariaTotalProjetosPesquisaExtensaoColab;
    }

    public void setCargaHorariaTotalProjetosPesquisaExtensaoColab(double cargaHorariaTotalProjetosPesquisaExtensaoColab) {
        this.cargaHorariaTotalProjetosPesquisaExtensaoColab = cargaHorariaTotalProjetosPesquisaExtensaoColab;
    }

    /**
     * @return the errosTabelaAula
     */
    public List<String> getErrosTabelaAula() {
        return errosTabelaAula;
    }

    /**
     * @param errosTabelaAula the errosTabelaAula to set
     */
    public void setErrosTabelaAula(List<String> errosTabelaAula) {
        this.errosTabelaAula = errosTabelaAula;
    }

    /**
     * @return the errosTabelaManuEnsino
     */
    public List<String> getErrosTabelaManuEnsino() {
        return errosTabelaManuEnsino;
    }

    /**
     * @param errosTabelaManuEnsino the errosTabelaManuEnsino to set
     */
    public void setErrosTabelaManuEnsino(List<String> errosTabelaManuEnsino) {
        this.errosTabelaManuEnsino = errosTabelaManuEnsino;
    }

    /**
     * @return the errosTabelaApoioEnsino
     */
    public List<String> getErrosTabelaApoioEnsino() {
        return errosTabelaApoioEnsino;
    }

    /**
     * @param errosTabelaApoioEnsino the errosTabelaApoioEnsino to set
     */
    public void setErrosTabelaApoioEnsino(List<String> errosTabelaApoioEnsino) {
        this.errosTabelaApoioEnsino = errosTabelaApoioEnsino;
    }

    /**
     * @return the errosTabelaPesquisaExtensaoAutor
     */
    public List<String> getErrosTabelaPesquisaExtensaoAutor() {
        return errosTabelaPesquisaExtensaoAutor;
    }

    /**
     * @param errosTabelaPesquisaExtensaoAutor the
     * errosTabelaPesquisaExtensaoAutor to set
     */
    public void setErrosTabelaPesquisaExtensaoAutor(List<String> errosTabelaPesquisaExtensaoAutor) {
        this.errosTabelaPesquisaExtensaoAutor = errosTabelaPesquisaExtensaoAutor;
    }

    /**
     * @return the errosTabelaPesquisaExtensaoColaborador
     */
    public List<String> getErrosTabelaPesquisaExtensaoColaborador() {
        return errosTabelaPesquisaExtensaoColaborador;
    }

    /**
     * @param errosTabelaPesquisaExtensaoColaborador the
     * errosTabelaPesquisaExtensaoColaborador to set
     */
    public void setErrosTabelaPesquisaExtensaoColaborador(List<String> errosTabelaPesquisaExtensaoColaborador) {
        this.errosTabelaPesquisaExtensaoColaborador = errosTabelaPesquisaExtensaoColaborador;
    }

    /**
     * @return the errosTabelaAdministrativas
     */
    public List<String> getErrosTabelaAdministrativas() {
        return errosTabelaAdministrativas;
    }

    /**
     * @param errosTabelaAdministrativas the errosTabelaAdministrativas to set
     */
    public void setErrosTabelaAdministrativas(List<String> errosTabelaAdministrativas) {
        this.errosTabelaAdministrativas = errosTabelaAdministrativas;
    }

    /**
     * @return the errosTabelaOutrasAtividades
     */
    public List<String> getErrosTabelaOutrasAtividades() {
        return errosTabelaOutrasAtividades;
    }

    /**
     * @param errosTabelaOutrasAtividades the errosTabelaOutrasAtividades to set
     */
    public void setErrosTabelaOutrasAtividades(List<String> errosTabelaOutrasAtividades) {
        this.errosTabelaOutrasAtividades = errosTabelaOutrasAtividades;
    }

    /**
     * @return the errosTabelaAtividadesASeremPropostas
     */
    public List<String> getErrosTabelaAtividadesASeremPropostas() {
        return errosTabelaAtividadesASeremPropostas;
    }

    /**
     * @param errosTabelaAtividadesASeremPropostas the
     * errosTabelaAtividadesASeremPropostas to set
     */
    public void setErrosTabelaAtividadesASeremPropostas(List<String> errosTabelaAtividadesASeremPropostas) {
        this.errosTabelaAtividadesASeremPropostas = errosTabelaAtividadesASeremPropostas;
    }

    /**
     * @return the participacoesAutor
     */
    public List<Participacao> getParticipacoesAutor() {
        atualizarListasParticipacoes();
        return participacoesAutor;
    }

    /**
     * @param participacoesAutor the participacoesAutor to set
     */
    public void setParticipacoesAutor(List<Participacao> participacoesAutor) {
        this.participacoesAutor = participacoesAutor;
    }

    /**
     * @return the participacoesColab
     */
    public List<Participacao> getParticipacoesColab() {
        atualizarListasParticipacoes();
        return participacoesColab;
    }

    /**
     * @param participacoesColab the participacoesColab to set
     */
    public void setParticipacoesColab(List<Participacao> participacoesColab) {
        this.participacoesColab = participacoesColab;
    }

    /**
     * @return the ptdsReprovados
     */
    public List<PTD> getPtdsReprovados() {
        return ptdsReprovados;
    }

    /**
     * @param ptdsReprovados the ptdsReprovados to set
     */
    public void setPtdsReprovados(List<PTD> ptdsReprovados) {
        this.ptdsReprovados = ptdsReprovados;
    }

    /**
     * @return the ptdsAprovados
     */
    public List<PTD> getPtdsAprovados() {
        return ptdsAprovados;
    }

    /**
     * @param ptdsAprovados the ptdsAprovados to set
     */
    public void setPtdsAprovados(List<PTD> ptdsAprovados) {
        this.ptdsAprovados = ptdsAprovados;
    }

    /**
     * @return the irregularidades
     */
    public List<String> getIrregularidades() {
        return irregularidades;
    }

    /**
     * @param irregularidades the irregularidades to set
     */
    public void setIrregularidades(List<String> irregularidades) {
        this.irregularidades = irregularidades;
    }
}
