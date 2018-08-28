
package br.edu.ifpr.irati.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "PTDIncompleto")
@PrimaryKeyJoinColumn(name = "idPTD")
public class PTDIncompleto extends PTD implements Serializable {

    public PTDIncompleto() {
        super();
    }

    public PTDIncompleto(int idPTD, String campoJustificativaAtividadeEnsino, String campoJustificativaManutencaoEnsino, String campoJustificativaApoioEnsino, String campoJustificativaAdministracao, String campoJustificativaProjetoEnsino, String campoJustificativaSeremPropostas, String campoJustificativaOutrasAtividades, String campoJustificativaProjetoPesquisa, String campoJustificativaProjetoExtensao) {
        super(idPTD, campoJustificativaAtividadeEnsino, campoJustificativaManutencaoEnsino, campoJustificativaApoioEnsino, campoJustificativaAdministracao, campoJustificativaProjetoEnsino, campoJustificativaSeremPropostas, campoJustificativaOutrasAtividades, campoJustificativaProjetoPesquisa, campoJustificativaProjetoExtensao);
    }

    public PTDIncompleto(int idPTD, String campoJustificativaAtividadeEnsino, String campoJustificativaManutencaoEnsino, String campoJustificativaApoioEnsino, String campoJustificativaAdministracao, String campoJustificativaProjetoEnsino, String campoJustificativaSeremPropostas, String campoJustificativaOutrasAtividades, String campoJustificativaProjetoPesquisa, String campoJustificativaProjetoExtensao, Professor professor, List<Aula> aulas, List<Apoio> apoios, List<ManutencaoEnsino> manutencoesEnsino, List<Administracao> administrativas, List<ProjetoEnsino> projetosEnsino, List<ProjetoExtensao> projetosExtensao, List<ProjetoPesquisa> projetosPesquisa, List<AtividadeASerProposta> atividadesASeremPropostas, List<OutroTipoAtividade> outrosTiposAtividades) {
        super(idPTD, campoJustificativaAtividadeEnsino, campoJustificativaManutencaoEnsino, campoJustificativaApoioEnsino, campoJustificativaAdministracao, campoJustificativaProjetoEnsino, campoJustificativaSeremPropostas, campoJustificativaOutrasAtividades, campoJustificativaProjetoPesquisa, campoJustificativaProjetoExtensao, professor, aulas, apoios, manutencoesEnsino, administrativas, projetosEnsino, projetosExtensao, projetosPesquisa, atividadesASeremPropostas, outrosTiposAtividades);
    }

}
