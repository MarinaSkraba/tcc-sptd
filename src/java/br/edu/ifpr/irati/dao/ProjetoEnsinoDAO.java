package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.modelo.ProjetoEnsino;
import br.edu.ifpr.irati.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ProjetoEnsinoDAO implements IProjetoEnsinoDao {

    @Override
    public List<ProjetoEnsino> buscarProjetosEnsinoAtivos(Serializable idUsuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from projetoensino, professor where estadoProjetoEnsino = 'Ativo' and idUsuario = ?";
        Query query = session.createQuery(hql);
        query.setSerializable(0, idUsuario);
        List results = query.list();
        session.clear();
        session.close();
        return results;
    }

    @Override
    public List<ProjetoEnsino> buscarProjetosEnsinoPorProfessor(Serializable idUsuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from projetoensino, professor where idUsuario = ?";
        Query query = session.createQuery(hql);
        query.setSerializable(0, idUsuario);
        List results = query.list();
        session.clear();
        session.close();
        return results;
    }

}
