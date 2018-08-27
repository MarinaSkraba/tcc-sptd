
package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.AtividadeASerProposta;
import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class AtividadeASerPropostaDAO implements IAtividadeASerPropostaDao {

    @Override
    public List<AtividadeASerProposta> buscarAtividadesAtivas(Professor professor) {
         Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from atividadeaserproposta where estadoAtividadeASerProposta = Ativo";
        Query query = session.createQuery(hql);
        List results = query.list();
        session.clear();
        session.close();
        return results;
    }
    
}
