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
    private List<PTD> pdtsEmAvaliacao;
    private List<PTD> ptdsEmEdicao;
    private double cargaHorariaTotalAdministracoes;
    private double cargaHorariaTotalApoios;
    private double cargaHorariaTotalAtividadesASeremPropostas;
    private double cargaHorariaTotalAulas;
    private double cargaHorariaTotalManutencoesEnsino;
    private double cargaHorariaTotalOutroTiposAtividade;
    private double cargaHorariaTotalProjetosPesquisaExtensaoAutor;
    private double cargaHorariaTotalProjetosPesquisaExtensaoColab;
    private double cargaHorariaTotalPTD;
    private boolean obrigatoriedadeJustificativaAula;
    private boolean obrigatoriedadeJustificativaApoio;
    private boolean obrigatoriedadeJustificativaManutencaoEnsino;
    private boolean obrigatoriedadeJustificativaPesquisaExtensao;
    private String estadoCargaHorariaPTD;
    private List<ProjetoPesquisaExtensao> projetosPesquisaExtensaoAutor;
    private List<ProjetoPesquisaExtensao> projetosPesquisaExtensaoColab;
    private List<String> errosTabelaAula;
    private List<String> errosTabelaManuEnsino;
    private List<String> errosTabelaApoioEnsino;
    private List<String> errosTabelaPesquisaExtensaoAutor;
    private List<String> errosTabelaPesquisaExtensaoColaborador;
    private List<String> errosTabelaAdministrativas;
    private List<String> errosTabelaOutrasAtividades;
    private List<String> errosTabelaAtividadesASeremPropostas;

    public PTDMB() {

        ptd = new PTD();
        ptdEmAvaliacao = new PTD();
        pdtsEmAvaliacao = new ArrayList<>();
        ptdsEmEdicao = new ArrayList();
        IPTDDAO ptdDAOEspecifico = new PTDDAO();
        pdtsEmAvaliacao = ptdDAOEspecifico.buscarPTDEmAvaliacao();
        this.cargaHorariaTotalAdministracoes = 0;
        this.cargaHorariaTotalApoios = 0;
        this.cargaHorariaTotalAtividadesASeremPropostas = 0;
        this.cargaHorariaTotalAulas = 0;
        this.cargaHorariaTotalManutencoesEnsino = 0;
        this.cargaHorariaTotalOutroTiposAtividade = 0;
        this.cargaHorariaTotalProjetosPesquisaExtensaoAutor = 0;
        this.cargaHorariaTotalProjetosPesquisaExtensaoColab = 0;
        this.cargaHorariaTotalPTD = 0;
        this.obrigatoriedadeJustificativaApoio = false;
        this.obrigatoriedadeJustificativaAula = false;
        this.obrigatoriedadeJustificativaManutencaoEnsino = false;
        this.obrigatoriedadeJustificativaPesquisaExtensao = false;
        this.estadoCargaHorariaPTD = "";
        this.projetosPesquisaExtensaoAutor = new ArrayList();
        this.projetosPesquisaExtensaoColab = new ArrayList();
        errosTabelaAula = new ArrayList<>();
        errosTabelaManuEnsino = new ArrayList<>();
        errosTabelaApoioEnsino = new ArrayList<>();
        errosTabelaPesquisaExtensaoAutor = new ArrayList<>();
        errosTabelaPesquisaExtensaoColaborador = new ArrayList<>();
        errosTabelaAdministrativas = new ArrayList<>();
        errosTabelaOutrasAtividades = new ArrayList<>();
        errosTabelaAtividadesASeremPropostas = new ArrayList<>();

    }

    public void verificarCargaHorariaPTD() {

        for (Administracao adm : getPtd().getAdministrativas()) {
            setCargaHorariaTotalAdministracoes(getCargaHorariaTotalAdministracoes() + adm.getCargaHorariaSemanalAdministracao());
        }

        for (Apoio ap : getPtd().getApoios()) {
            setCargaHorariaTotalApoios(getCargaHorariaTotalApoios() + ap.getCargaHorariaSemanalApoio());
        }
        if (getCargaHorariaTotalApoios() != 4) {
            setObrigatoriedadeJustificativaApoio(true);
        }

        for (Aula a : getPtd().getAulas()) {
            setCargaHorariaTotalAulas(getCargaHorariaTotalAulas() + a.getCargaHorariaTotal());
        }

        if (getPtd().getProfessor().getRegimeTrabalho().equals("20h")) {

            if (getCargaHorariaTotalAulas() < 8 && getCargaHorariaTotalAulas() > 12) {

                setObrigatoriedadeJustificativaAula(true);

            }

        } else if (getPtd().getProfessor().getRegimeTrabalho().equals("40h")) {
            if (getCargaHorariaTotalAulas() < 12 && getCargaHorariaTotalAulas() > 16) {

                setObrigatoriedadeJustificativaAula(true);
            }
        }

        for (ManutencaoEnsino me : getPtd().getManutencoesEnsino()) {
            setCargaHorariaTotalManutencoesEnsino(getCargaHorariaTotalManutencoesEnsino() + me.getCargaHorariaSemanalManutencaoEnsino());
        }

        if (getCargaHorariaTotalManutencoesEnsino() != 4) {
            setObrigatoriedadeJustificativaManutencaoEnsino(true);
        }

        for (OutroTipoAtividade ota : getPtd().getOutrosTiposAtividades()) {
            setCargaHorariaTotalOutroTiposAtividade(getCargaHorariaTotalOutroTiposAtividade() + ota.getCargaHorariaSemanalOutroTipoAtividade());
        }
        for (ProjetoPesquisaExtensao ppe : getPtd().getProjetosPesquisaExtensao()) {
            for (Participacao part : ppe.getParticipacoesProjetoPesquisaExtensao()) {
                if (part.getRotulo().equals("AUTOR")) {
                    setCargaHorariaTotalProjetosPesquisaExtensaoAutor(getCargaHorariaTotalProjetosPesquisaExtensaoAutor() + part.getCargaHorariaSemanalParticipacao());
                } else if (part.getRotulo().equals("COLABORADOR")) {
                    setCargaHorariaTotalProjetosPesquisaExtensaoColab(getCargaHorariaTotalProjetosPesquisaExtensaoColab() + part.getCargaHorariaSemanalParticipacao());
                }
            }
        }
        if ((getCargaHorariaTotalProjetosPesquisaExtensaoAutor() + getCargaHorariaTotalProjetosPesquisaExtensaoColab()) != 16) {
            setObrigatoriedadeJustificativaPesquisaExtensao(true);
        }

        setCargaHorariaTotalPTD(getCargaHorariaTotalAdministracoes() + getCargaHorariaTotalApoios() + getCargaHorariaTotalAulas() + getCargaHorariaTotalManutencoesEnsino() + getCargaHorariaTotalOutroTiposAtividade() + getCargaHorariaTotalProjetosPesquisaExtensaoAutor() + getCargaHorariaTotalProjetosPesquisaExtensaoColab());
        if (Double.parseDouble(getPtd().getProfessor().getRegimeTrabalho()) == getCargaHorariaTotalPTD()) {

            setEstadoCargaHorariaPTD("CORRETO");

        } else {

            setEstadoCargaHorariaPTD("INCORRETO");
        }
    }

    public String abrirCriarCorrigirPTDEmBranco(Usuario usuario) {
        Dao<PTD> ptdDAOGenerico = new GenericDAO<>(PTD.class
        );
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

    public String cancelarPTD() {

        Dao<Administracao> administracaoDAO = new GenericDAO<>(PTD.class);
        Dao<TipoAdministracao> tipoAdministracaoDAO = new GenericDAO<>(TipoAdministracao.class);
        Dao<Apoio> apoioDAO = new GenericDAO<>(Apoio.class);
        Dao<TipoApoio> tipoApoioDAO = new GenericDAO<>(TipoApoio.class);
        Dao<AtividadeASerProposta> aASPropostaDAO = new GenericDAO<>(AtividadeASerProposta.class);
        Dao<Aula> aulaDAO = new GenericDAO<>(Aula.class);
        Dao<Curso> cursoDAO = new GenericDAO<>(Curso.class);
        Dao<DiretorEnsino> diretorEnsinoDAO = new GenericDAO<>(DiretorEnsino.class);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        Dao<ManutencaoEnsino> manutencaoDAO = new GenericDAO<>(ManutencaoEnsino.class);
        Dao<TipoManutencao> tipoManutencaoDAO = new GenericDAO<>(TipoManutencao.class);
        Dao<OutroTipoAtividade> oTAtividadeDAO = new GenericDAO<>(OutroTipoAtividade.class);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        Dao<Professor> professorDAO = new GenericDAO<>(Professor.class);
        Dao<ProjetoPesquisaExtensao> pPesquisaExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class);
        Dao<TipoOferta> tipoOfertaDAO = new GenericDAO<>(TipoOferta.class);
        Dao<Usuario> usuarioDAO = new GenericDAO<>(Usuario.class);

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

        for (ProjetoPesquisaExtensao pPesquisaExtensao : getPtd().getProjetosPesquisaExtensao()) {

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

    public void verificarErros() {
        errosTabelaAdministrativas = new ArrayList();
        errosTabelaApoioEnsino = new ArrayList();
        errosTabelaAtividadesASeremPropostas = new ArrayList();
        errosTabelaAula = new ArrayList();
        errosTabelaManuEnsino = new ArrayList();
        errosTabelaOutrasAtividades = new ArrayList();
        errosTabelaPesquisaExtensaoAutor = new ArrayList();
        errosTabelaPesquisaExtensaoColaborador = new ArrayList();

        // erro horaInício > horaTérmino
        for (Administracao adm : getPtd().getAdministrativas()) {
            for (Horario h : adm.getHorariosAdministracao()) {

                if (h.getHoraInicio().getTime() > h.getHoraTermino().getTime()) {
                    errosTabelaAdministrativas.add("erroInTerm");
                }else if (h.getHoraInicio() == null) {
                    errosTabelaAdministrativas.add("Innull");
                } else if (h.getHoraTermino() == null) {
                    errosTabelaAdministrativas.add("Termnull");
                }
            }
        }

        for (Apoio apoio : getPtd().getApoios()) {
            for (Horario h : apoio.getHorariosApoio()) {

                if (h.getHoraInicio().getTime() > h.getHoraTermino().getTime()) {
                    errosTabelaApoioEnsino.add("erroInTerm");
                }else if (h.getHoraInicio() == null) {
                    errosTabelaApoioEnsino.add("Innull");
                } else if (h.getHoraTermino() == null) {
                    errosTabelaApoioEnsino.add("Termnull");
                }
            }
        }

        for (AtividadeASerProposta aSP : getPtd().getAtividadesASeremPropostas()) {
            for (Horario h : aSP.getHorariosAtividadesASerProposta()) {

                if (h.getHoraInicio().getTime() > h.getHoraTermino().getTime()) {
                    errosTabelaAtividadesASeremPropostas.add("erroInTerm");
                }else if (h.getHoraInicio() == null) {
                    errosTabelaAtividadesASeremPropostas.add("Innull");
                } else if (h.getHoraTermino() == null) {
                    errosTabelaAtividadesASeremPropostas.add("Termnull");
                }
            }
        }

        for (Aula aula : getPtd().getAulas()) {
            for (Horario h : aula.getHorariosAula()) {

                if (h.getHoraInicio().getTime() > h.getHoraTermino().getTime()) {
                    errosTabelaAula.add("erroInTerm");

                } else if (h.getHoraInicio() == null) {
                    errosTabelaAula.add("Innull");
                } else if (h.getHoraTermino() == null) {
                    errosTabelaAula.add("Termnull");
                }
            }

            for (ManutencaoEnsino mE : getPtd().getManutencoesEnsino()) {
                for (Horario h : mE.getHorariosManutecao()) {

                    if (h.getHoraInicio().getTime() > h.getHoraTermino().getTime()) {
                        errosTabelaManuEnsino.add("erroInTerm");
                    } else if (h.getHoraInicio() == null) {
                        errosTabelaManuEnsino.add("Innull");
                    } else if (h.getHoraTermino() == null) {
                        errosTabelaManuEnsino.add("Termnull");
                    }
                }
            }

            for (OutroTipoAtividade oTA : getPtd().getOutrosTiposAtividades()) {
                for (Horario h : oTA.getHorariosOutroTipoAtividade()) {

                    if (h.getHoraInicio().getTime() > h.getHoraTermino().getTime()) {
                        errosTabelaOutrasAtividades.add("erroInTerm");
                    } else if (h.getHoraInicio() == null) {
                        errosTabelaOutrasAtividades.add("Innull");
                    } else if (h.getHoraTermino() == null) {
                        errosTabelaOutrasAtividades.add("Termnull");
                    }
                }
            }

//           for (ProjetoPesquisaExtensao pPE : getPtd().getProjetosPesquisaExtensao()) {
//             for(Horario h : pPE.getHorariosAula()){
//                 
//                 if (h.getHoraInicio().getTime() > h.getHoraTermino().getTime()) {
//                     
//                 }
//             }
//           }
        }
    }

    public int verificarListaErros(List<String> erros) {
        if (erros.isEmpty()) {
            return 0;
        } else {
            return 1;
        }
    }

    public String abrirNotificacoesDiretorEnsino(int idUsuario) {
        return "/NotificacoesDiretorEnsino";
    }
    
    
    public String verificacaoIrregularidadesNotificacoesDiretorEnsino() {
        if (getPtd().getApoios().isEmpty() != true) {
            return "Possui irregularidades";
        } else {
            return "Correto";
        }
    }

    public String abrirPTDEmAvaliacao(PTD ptd) {
        setPtdEmAvaliacao(ptd);

//        if(){
//            
//        }
        return "PTDEmAvaliacao";
    }

    public PTD getPtd() {
        return ptd;
    }

    public void setPtd(PTD ptd) {
        this.ptd = ptd;
    }

    /**
     * @return the pdtsEmAvaliacao
     */
    public List<PTD> getPdtsEmAvaliacao() {
        return pdtsEmAvaliacao;
    }

    /**
     * @param pdtsEmAvaliacao the pdtsEmAvaliacao to set
     */
    public void setPdtsEmAvaliacao(List<PTD> pdtsEmAvaliacao) {
        this.pdtsEmAvaliacao = pdtsEmAvaliacao;
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

    public boolean isObrigatoriedadeJustificativaAula() {
        return obrigatoriedadeJustificativaAula;
    }

    public void setObrigatoriedadeJustificativaAula(boolean obrigatoriedadeJustificativaAula) {
        this.obrigatoriedadeJustificativaAula = obrigatoriedadeJustificativaAula;
    }

    public boolean isObrigatoriedadeJustificativaApoio() {
        return obrigatoriedadeJustificativaApoio;
    }

    public void setObrigatoriedadeJustificativaApoio(boolean obrigatoriedadeJustificativaApoio) {
        this.obrigatoriedadeJustificativaApoio = obrigatoriedadeJustificativaApoio;
    }

    public boolean isObrigatoriedadeJustificativaManutencaoEnsino() {
        return obrigatoriedadeJustificativaManutencaoEnsino;
    }

    public void setObrigatoriedadeJustificativaManutencaoEnsino(boolean obrigatoriedadeJustificativaManutencaoEnsino) {
        this.obrigatoriedadeJustificativaManutencaoEnsino = obrigatoriedadeJustificativaManutencaoEnsino;
    }

    public boolean isObrigatoriedadeJustificativaPesquisaExtensao() {
        return obrigatoriedadeJustificativaPesquisaExtensao;
    }

    public void setObrigatoriedadeJustificativaPesquisaExtensao(boolean obrigatoriedadeJustificativaPesquisaExtensao) {
        this.obrigatoriedadeJustificativaPesquisaExtensao = obrigatoriedadeJustificativaPesquisaExtensao;
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

    public List<ProjetoPesquisaExtensao> getProjetosPesquisaExtensaoAutor() {
        return projetosPesquisaExtensaoAutor;
    }

    public void setProjetosPesquisaExtensaoAutor(List<ProjetoPesquisaExtensao> projetosPesquisaExtensaoAutor) {
        this.projetosPesquisaExtensaoAutor = projetosPesquisaExtensaoAutor;
    }

    public List<ProjetoPesquisaExtensao> getProjetosPesquisaExtensaoColab() {
        return projetosPesquisaExtensaoColab;
    }

    public void setProjetosPesquisaExtensaoColab(List<ProjetoPesquisaExtensao> projetosPesquisaExtensaoColab) {
        this.projetosPesquisaExtensaoColab = projetosPesquisaExtensaoColab;
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
}
