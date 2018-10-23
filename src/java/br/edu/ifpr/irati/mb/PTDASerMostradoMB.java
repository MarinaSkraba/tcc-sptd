package br.edu.ifpr.irati.mb;

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

    public PTDASerMostradoMB() {
        ptdConcluido = new PTD();
        ptdsResultadoBusca = new ArrayList<>();
        participacoesAutorPTDConcluido = new ArrayList<>();
        participacoesColabPTDConcluido = new ArrayList<>();
    }

    public void abrirTelaBuscarPTDs() {
        IPTDDAO ptddao = new PTDDAO();
        ptdsResultadoBusca = ptddao.buscarPTDsConcluidos();
    }

    public void abrirMostrarPTD(PTD ptd) {
        ptdConcluido = ptd;
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
        return participacoesAutorPTDConcluido;
    }

    /**
     * @param participacoesAutorPTDConcluido the participacoesAutorPTDConcluido to set
     */
    public void setParticipacoesAutorPTDConcluido(List<Participacao> participacoesAutorPTDConcluido) {
        this.participacoesAutorPTDConcluido = participacoesAutorPTDConcluido;
    }

    /**
     * @return the participacoesColabPTDConcluido
     */
    public List<Participacao> getParticipacoesColabPTDConcluido() {
        return participacoesColabPTDConcluido;
    }

    /**
     * @param participacoesColabPTDConcluido the participacoesColabPTDConcluido to set
     */
    public void setParticipacoesColabPTDConcluido(List<Participacao> participacoesColabPTDConcluido) {
        this.participacoesColabPTDConcluido = participacoesColabPTDConcluido;
    }
    
}
