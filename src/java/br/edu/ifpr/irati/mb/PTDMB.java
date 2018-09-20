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
import br.edu.ifpr.irati.dao.IProjetoExtensaoDao;
import br.edu.ifpr.irati.dao.IProjetoPesquisaDao;
import br.edu.ifpr.irati.dao.ManutencaoDAO;
import br.edu.ifpr.irati.dao.PTDDAO;
import br.edu.ifpr.irati.dao.ProjetoEnsinoDAO;
import br.edu.ifpr.irati.dao.ProjetoExtensaoDAO;
import br.edu.ifpr.irati.dao.ProjetoPesquisaDAO;
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
import br.edu.ifpr.irati.modelo.ProjetoEnsino;
import br.edu.ifpr.irati.modelo.ProjetoExtensao;
import br.edu.ifpr.irati.modelo.ProjetoPesquisa;
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
    private List<PTD> pdtsEmAvaliacao;
    private List<PTD> ptdsEmEdicao;

    public PTDMB() {

        ptd = new PTD();
        pdtsEmAvaliacao = new ArrayList<>();
        ptdsEmEdicao = new ArrayList();
        IPTDDAO ptdDAOEspecifico = new PTDDAO();
        pdtsEmAvaliacao = ptdDAOEspecifico.buscarPTDEmAvaliacao();

    }

    public String abrirCriarCorrigirPTDEmBranco(Usuario usuario) {
        Dao<PTD> ptdDAOGenerico = new GenericDAO<>(PTD.class);
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
        ptd.setDiretorEnsino(null);
        ptd.setEstadoPTD("EDICAO");
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

    public String abrirCriarCorrigirPTDAPartirDoUltimoArquivado(Usuario usuario) {
        Dao<PTD> ptdDAOGenerico = new GenericDAO<>(PTD.class);
        IPTDDAO ptdDAOEspecifico = new PTDDAO();
        List<PTD> ptdsEmEdicao = ptdDAOEspecifico.buscarPTDsEmEdicao(usuario.getIdUsuario());
        for (PTD ptdE : ptdsEmEdicao) {
            ptdE.setEstadoPTD("CANCELADO");
            ptdDAOGenerico.alterar(ptdE);
        }
        List<PTD> ptdsAprovados = ptdDAOEspecifico.buscarPTDsAprovados(usuario.getIdUsuario());
        if (ptdsAprovados.isEmpty() != true) {
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

    public String cancelarPTD() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ManagerBean", new PTDMB());
        
        Dao<Administracao> adminstracaoDAO = new GenericDAO<>(PTD.class);
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
        Dao<ProjetoPesquisa> pPesquisa = new GenericDAO<>(ProjetoPesquisa.class);
        Dao<ProjetoExtensao> pExtensao = new GenericDAO<>(ProjetoExtensao.class);
        Dao<TipoOferta> tipoOferta = new GenericDAO<>(TipoOferta.class);
        Dao<Usuario> usuarioDAO = new GenericDAO<>(Usuario.class);
        
        return "NotificacoesDocente";
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

    public String abrirPTDEmAvaliacao() {
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
}
