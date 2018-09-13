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
import br.edu.ifpr.irati.modelo.ManutencaoEnsino;
import br.edu.ifpr.irati.modelo.OutroTipoAtividade;
import br.edu.ifpr.irati.modelo.PTD;
import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.modelo.ProjetoEnsino;
import br.edu.ifpr.irati.modelo.ProjetoExtensao;
import br.edu.ifpr.irati.modelo.ProjetoPesquisa;
import br.edu.ifpr.irati.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class PTDMB {

    private PTD ptd;
    private List<PTD> pdtsEmAvaliacao;
    private List<PTD> ptdsEmEdicao;
    

    public PTDMB() {

        ptd = new PTD();
        pdtsEmAvaliacao = new ArrayList<>();
        ptdsEmEdicao = new ArrayList();
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

    public String abrirNotificacoesDiretorEnsino(int idUsuario) {
        IPTDDAO ptdDAOEspecifico = new PTDDAO();
        pdtsEmAvaliacao = ptdDAOEspecifico.buscarPTDEmAvaliacao(idUsuario);
        return "/NotificacoesDiretorEnsino";
    }

    public String excluirPTDIncompleto(PTD ptd) {
        Dao<PTD> ptdDAO = new GenericDAO<>(PTD.class);
        ptdDAO.excluir(ptd);
        return "/adicionar html aqui";
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
