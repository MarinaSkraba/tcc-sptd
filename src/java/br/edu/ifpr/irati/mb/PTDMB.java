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
import br.edu.ifpr.irati.modelo.TipoOferta;
import br.edu.ifpr.irati.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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

    }

    public void verificarCargaHorariaPTD() {

        for (Administracao adm : ptd.getAdministrativas()) {
            cargaHorariaTotalAdministracoes = cargaHorariaTotalAdministracoes + adm.getCargaHorariaSemanalAdministracao();
        }

        for (Apoio ap : ptd.getApoios()) {
            cargaHorariaTotalApoios = cargaHorariaTotalApoios + ap.getCargaHorariaSemanalApoio();
        }
        if (cargaHorariaTotalApoios != 4) {
            obrigatoriedadeJustificativaApoio = true;
        }

        for (Aula a : ptd.getAulas()) {
            cargaHorariaTotalAulas = cargaHorariaTotalAulas + a.getCargaHorariaTotal();
        }

        if (ptd.getProfessor().getRegimeTrabalho().equals("20h")) {

            if (cargaHorariaTotalAulas < 8 && cargaHorariaTotalAulas > 12) {

                obrigatoriedadeJustificativaAula = true;

            }

        } else if (ptd.getProfessor().getRegimeTrabalho().equals("40h")) {
            if (cargaHorariaTotalAulas < 12 && cargaHorariaTotalAulas > 16) {

                obrigatoriedadeJustificativaAula = true;
            }
        }

        for (ManutencaoEnsino me : ptd.getManutencoesEnsino()) {
            cargaHorariaTotalManutencoesEnsino = cargaHorariaTotalManutencoesEnsino + me.getCargaHorariaSemanalManutencaoEnsino();
        }

        if (cargaHorariaTotalManutencoesEnsino != 4) {
            obrigatoriedadeJustificativaManutencaoEnsino = true;
        }

        for (OutroTipoAtividade ota : ptd.getOutrosTiposAtividades()) {
            cargaHorariaTotalOutroTiposAtividade = cargaHorariaTotalOutroTiposAtividade + ota.getCargaHorariaSemanalOutroTipoAtividade();
        }
        for (ProjetoPesquisaExtensao ppe : ptd.getProjetosPesquisaExtensao()) {
            for (Participacao part : ppe.getParticipacoes()) {
                if(part.getRotulo().equals("AUTOR")){
                cargaHorariaTotalProjetosPesquisaExtensaoAutor = cargaHorariaTotalProjetosPesquisaExtensaoAutor + ppe.getCargaHorariaSemanalProjetoPesquisaExtensao();
                }else if(part.getRotulo().equals("COLABORADOR")){
                    cargaHorariaTotalProjetosPesquisaExtensaoColab = cargaHorariaTotalProjetosPesquisaExtensaoColab + ppe.getCargaHorariaSemanalProjetoPesquisaExtensao();
                }
            }
        }
        if ((cargaHorariaTotalProjetosPesquisaExtensaoAutor + cargaHorariaTotalProjetosPesquisaExtensaoColab) != 16) {
            obrigatoriedadeJustificativaPesquisaExtensao = true;
        }

        cargaHorariaTotalPTD = cargaHorariaTotalAdministracoes + cargaHorariaTotalApoios + cargaHorariaTotalAulas + cargaHorariaTotalManutencoesEnsino + cargaHorariaTotalOutroTiposAtividade + cargaHorariaTotalProjetosPesquisaExtensaoAutor + cargaHorariaTotalProjetosPesquisaExtensaoColab;
        if (Double.parseDouble(ptd.getProfessor().getRegimeTrabalho()) == cargaHorariaTotalPTD) {

            estadoCargaHorariaPTD = "CORRETO";

        } else {

            estadoCargaHorariaPTD = "INCORRETO";
        }
    }

    public String
            abrirCriarCorrigirPTDEmBranco(Usuario usuario) {
        Dao<PTD> ptdDAOGenerico = new GenericDAO<>(PTD.class
        );
        Dao<Professor> professorDAOGenerico = new GenericDAO<>(Professor.class);
        IPTDDAO ptdDAOEspecifico = new PTDDAO();
        Professor p = professorDAOGenerico.buscarPorId(usuario.getIdUsuario());

        ptd.setProfessor(p);
        ptdsEmEdicao = ptdDAOEspecifico.buscarPTDsEmEdicao(p.getIdUsuario());
        for (PTD ptdE : ptdsEmEdicao) {
            ptdE.setEstadoPTD("CANCELADO");
            ptdDAOGenerico.alterar(ptdE);
        }
        ptd = new PTD();

        ptd.setProfessor(p);

        ptd.setDiretorEnsino(
                null);
        ptd.setEstadoPTD(
                "EDICAO");
        ptdDAOGenerico.salvar(ptd);

        if (!ptdDAOEspecifico.buscarPTDsEmEdicao(p.getIdUsuario()).isEmpty()) {
            ptd = ptdDAOEspecifico.buscarPTDsEmEdicao(p.getIdUsuario()).get(0);
        }

        return "/CriarCorrigirPTD";
    }

    public String abrirCriarCorrigirPTDContinuarEdicao(Usuario usuario) {

        IPTDDAO ptdDAOEspecifico = new PTDDAO();
        List<PTD> ptdEmEdicao = ptdDAOEspecifico.buscarPTDsEmEdicao(usuario.getIdUsuario());
        ptd = ptdEmEdicao.get(0);
        return "/CriarCorrigirPTD";
    }

    public String
            abrirCriarCorrigirPTDAPartirDoUltimoArquivado(Usuario usuario) {
        Dao<PTD> ptdDAOGenerico = new GenericDAO<>(PTD.class
        );
        IPTDDAO ptdDAOEspecifico = new PTDDAO();
        List<PTD> ptdsEmEdicao = ptdDAOEspecifico.buscarPTDsEmEdicao(usuario.getIdUsuario());
        for (PTD ptdE : ptdsEmEdicao) {
            ptdE.setEstadoPTD("CANCELADO");
            ptdDAOGenerico.alterar(ptdE);
        }
        List<PTD> ptdsAprovados = ptdDAOEspecifico.buscarPTDsAprovados(usuario.getIdUsuario());

        if (ptdsAprovados.isEmpty()
                != true) {
            ptd = ptdsAprovados.get(ptdsAprovados.size() - 1);
            ptd.setIdPTD(0);
            ptd.setEstadoPTD("EDICAO");
            ptdDAOGenerico.salvar(ptd);
            ptd = ptdDAOEspecifico.buscarPTDsEmEdicao(usuario.getIdUsuario()).get(0);
            return "/CriarCorrigirPTD";
        } else {

            return "/NotificacoesDocente";
        }
    }

    public String
            cancelarPTD() {

        Dao<Administracao> adminstracaoDAO = new GenericDAO<>(PTD.class
        );
        Dao<Apoio> apoioDAO = new GenericDAO<>(Apoio.class);
        Dao<AtividadeASerProposta> aASPropostaDAO = new GenericDAO<>(AtividadeASerProposta.class);
        Dao<Aula> aulaDAO = new GenericDAO<>(Aula.class);
        Dao<Curso> cursoDAO = new GenericDAO<>(Curso.class);
        Dao<DiretorEnsino> diretorEnsinoDAO = new GenericDAO<>(DiretorEnsino.class);
        Dao<Horario> horarioDAO = new GenericDAO<>(Horario.class);
        Dao<ManutencaoEnsino> manutencaoDAO = new GenericDAO<>(ManutencaoEnsino.class);
        Dao<OutroTipoAtividade> oTAtividadeDAO = new GenericDAO<>(OutroTipoAtividade.class);
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        Dao<Participacao> participacaoDAO = new GenericDAO<>(Participacao.class);
        Dao<Professor> professorDAO = new GenericDAO<>(Professor.class);
        Dao<ProjetoPesquisaExtensao> pPesquisaExtensaoDAO = new GenericDAO<>(ProjetoPesquisaExtensao.class);
        Dao<TipoOferta> tipoOfertaDAO = new GenericDAO<>(TipoOferta.class);
        Dao<Usuario> usuarioDAO = new GenericDAO<>(Usuario.class);

        for (Administracao adm
                : ptd.getAdministrativas()) {

        }
        for (Apoio apoio
                : ptd.getApoios()) {

        }
        for (AtividadeASerProposta aASP
                : ptd.getAtividadesASeremPropostas()) {

        }
        for (Aula aula
                : ptd.getAulas()) {
            for (Horario h : aula.getHorariosAula()) {

            }
        }
        for (ManutencaoEnsino mEnsino
                : ptd.getManutencoesEnsino()) {

        }
        for (OutroTipoAtividade oTA
                : ptd.getOutrosTiposAtividades()) {

        }
        for (ProjetoPesquisaExtensao pPesquisaExtensao
                : ptd.getProjetosPesquisaExtensao()) {

        }

        return "NotificacoesDocente";
    }

    public String
            submeterPTD() {
        Dao<PTD> ptdDAOGenerico = new GenericDAO<>(PTD.class
        );
        ptd.setEstadoPTD(
                "AVALIACAO");
        ptdDAOGenerico.alterar(ptd);

        return "/NotificacoesDocente";
    }

    public String abrirNotificacoesDiretorEnsino(int idUsuario) {
        return "/NotificacoesDiretorEnsino";
    }

    public String verificacaoIrregularidadesNotificacoesDiretorEnsino() {
        if (ptd.getApoios().isEmpty() != true) {
            return "Possui irregularidades";
        } else {
            return "Correto";
        }
    }

    public String abrirPTDEmAvaliacao(PTD ptd) {
        ptdEmAvaliacao = ptd;

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
}
