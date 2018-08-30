package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.Aula;
import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.modelo.Usuario;
import br.edu.ifpr.irati.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class AulaDAO implements IAulaDao {

    @Override
    public List<Aula> buscarAulasAtivas(Serializable idUsuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from aula, professor where estadoAula = 'Ativo' and idUsuario = ? ";
        Query query = session.createQuery(hql);
        query.setSerializable(0, idUsuario);
        List results = query.list();
        session.clear();
        session.close();
        return results;
    }

    @Override
    public List<Aula> buscarAulasPorProfessor(Serializable idUsuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from aula, professor where idUsuario = ? ";
        Query query = session.createQuery(hql);
        List results = query.list();
        session.clear();
        session.close();
        return results;
    }

}
