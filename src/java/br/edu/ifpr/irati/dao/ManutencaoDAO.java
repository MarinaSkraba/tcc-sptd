package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.ManutencaoEnsino;
import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ManutencaoDAO implements IManutencaoDao {

    @Override
    public List<ManutencaoEnsino> buscarManutencoesAtivas(Serializable idUsuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from manutencao, professor where estadoManutencaoEnsino = 'Ativo' and idUsuario = ? ";
        Query query = session.createQuery(hql);
        query.setSerializable(0, idUsuario);
        List results = query.list();
        session.clear();
        session.close();
        return results;
    }

    @Override
    public List<ManutencaoEnsino> buscarManutencoesPorProfessor(Serializable idUsuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from manutencao, professor where idUsuario = ? ";
        Query query = session.createQuery(hql);
        query.setSerializable(0, idUsuario);
        List results = query.list();
        session.clear();
        session.close();
        return results;

    }

}
