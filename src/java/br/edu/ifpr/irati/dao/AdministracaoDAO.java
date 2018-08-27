
package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.Administracao;
import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class AdministracaoDAO implements IAdministracaoDao {

    @Override
    public List<Administracao> buscarAdministracoesAtivas(Professor professor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from administracao where estadoAtividadeAdministracao = Ativo";
        Query query = session.createQuery(hql);
        List results = query.list();
        session.clear();
        session.close();
        return results;
    }

}
