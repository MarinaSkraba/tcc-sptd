package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.ManutencaoEnsino;
import br.edu.ifpr.irati.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ManutencaoDAO implements IManutencaoDao {

    @Override
    public List<ManutencaoEnsino> buscarManutencoesAtivas() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from manutencao where estadoManutencaoEnsino = Ativo";
        Query query = session.createQuery(hql);
        List results = query.list();
        session.clear();
        session.close();
        return results;
    }

}
