package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.ProjetoPesquisa;
import br.edu.ifpr.irati.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ProjetoPesquisaDAO implements IProjetoPesquisaDao {

    @Override
    public List<ProjetoPesquisa> buscarProjetosPesquisaAtivos() {
     Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from projetopesquisa where estadoProjetoPesquisa = Ativo";
        Query query = session.createQuery(hql);
        List results = query.list();
        session.clear();
        session.close();
        return results;
    }

}
