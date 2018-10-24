package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.dao.IPTDDAO;
import br.edu.ifpr.irati.dao.PTDDAO;
import br.edu.ifpr.irati.modelo.PTD;
import br.edu.ifpr.irati.modelo.Participacao;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PTDASerMostradoMB {

    private PTD ptdConcluido;
    private List<PTD> ptdsResultadoBusca;
    private List<Participacao> participacoesAutorPTDConcluido;
    private List<Participacao> participacoesColabPTDConcluido;
    private String textoBusca;
    private String filtroBusca;

    public PTDASerMostradoMB() {
        IPTDDAO ptdDAOEspecifico = new PTDDAO();
        ptdConcluido = new PTD();
        ptdsResultadoBusca = new ArrayList<>();
        ptdsResultadoBusca = ptdDAOEspecifico.buscarPTDsConcluidos();
        participacoesAutorPTDConcluido = new ArrayList<>();
        participacoesColabPTDConcluido = new ArrayList<>();
        textoBusca = "";
        filtroBusca = "";
    }

    public void realizarBusca() {

        ptdsResultadoBusca = new ArrayList();
        IPTDDAO ptdDAOEspecifico = new PTDDAO();

        if (filtroBusca.equals("buscaPorNome")) {

            ptdsResultadoBusca = ptdDAOEspecifico.buscarPTDsPorNomeDocente(textoBusca);

        } else if (filtroBusca.equals("buscaPorAtividade")) {

            ptdsResultadoBusca = ptdDAOEspecifico.buscarPTDsPorAtividade(textoBusca);

        }

    }

    public void abrirTelaBuscarPTDs() {
        IPTDDAO ptdDAOEspecifico = new PTDDAO();
        setPtdsResultadoBusca(ptdDAOEspecifico.buscarPTDsConcluidos());
    }

    public
            void abrirMostrarPTD(PTD ptd) {
        Dao<PTD> ptdDAOGenerico = new GenericDAO<>(PTD.class
        );
        setPtdConcluido(ptdDAOGenerico.buscarPorId(ptd.getIdPTD()));
    }

    public void atualizarListasParticipacoesPTD() {
        setParticipacoesAutorPTDConcluido(new ArrayList<>());
        setParticipacoesColabPTDConcluido(new ArrayList<>());
        for (Participacao part : ptdConcluido.getParticipacoes()) {
            if (part.getRotulo().equalsIgnoreCase("Autor")) {
                participacoesAutorPTDConcluido.add(part);
            } else {
                participacoesColabPTDConcluido.add(part);
            }
        }
    }

    /**
     * @return the ptdsResultadoBusca
     */
    public List<PTD> getPtdsResultadoBusca() {
        return ptdsResultadoBusca;
    }

    /**
     * @param ptdsResultadoBusca the ptdsResultadoBusca to set
     */
    public void setPtdsResultadoBusca(List<PTD> ptdsResultadoBusca) {
        this.ptdsResultadoBusca = ptdsResultadoBusca;
    }

    /**
     * @return the ptdConcluido
     */
    public PTD getPtdConcluido() {
        return ptdConcluido;
    }

    /**
     * @param ptdConcluido the ptdConcluido to set
     */
    public void setPtdConcluido(PTD ptdConcluido) {
        this.ptdConcluido = ptdConcluido;
    }

    /**
     * @return the participacoesAutorPTDConcluido
     */
    public List<Participacao> getParticipacoesAutorPTDConcluido() {
        atualizarListasParticipacoesPTD();
        return participacoesAutorPTDConcluido;
    }

    /**
     * @param participacoesAutorPTDConcluido the participacoesAutorPTDConcluido
     * to set
     */
    public void setParticipacoesAutorPTDConcluido(List<Participacao> participacoesAutorPTDConcluido) {
        this.participacoesAutorPTDConcluido = participacoesAutorPTDConcluido;
    }

    /**
     * @return the participacoesColabPTDConcluido
     */
    public List<Participacao> getParticipacoesColabPTDConcluido() {
        atualizarListasParticipacoesPTD();
        return participacoesColabPTDConcluido;
    }

    /**
     * @param participacoesColabPTDConcluido the participacoesColabPTDConcluido
     * to set
     */
    public void setParticipacoesColabPTDConcluido(List<Participacao> participacoesColabPTDConcluido) {
        this.participacoesColabPTDConcluido = participacoesColabPTDConcluido;
    }

    /**
     * @return the textoBusca
     */
    public String getTextoBusca() {
        return textoBusca;
    }

    /**
     * @param textoBusca the textoBusca to set
     */
    public void setTextoBusca(String textoBusca) {
        this.textoBusca = textoBusca;
    }

    /**
     * @return the filtroBusca
     */
    public String getFiltroBusca() {
        return filtroBusca;
    }

    /**
     * @param filtroBusca the filtroBusca to set
     */
    public void setFiltroBusca(String filtroBusca) {
        this.filtroBusca = filtroBusca;
    }

}
