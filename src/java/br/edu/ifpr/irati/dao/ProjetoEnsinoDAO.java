
package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.modelo.ProjetoEnsino;
import br.edu.ifpr.irati.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ProjetoEnsinoDAO implements IProjetoEnsinoDao {

    @Override
    public List<ProjetoEnsino> buscarProjetosEnsinoAtivos(Professor professor) {
      Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from projetoensino where estadoProjetoEnsino = Ativo";
        Query query = session.createQuery(hql);
        List results = query.list();
        session.clear();
        session.close();
        return results;}
    
}
