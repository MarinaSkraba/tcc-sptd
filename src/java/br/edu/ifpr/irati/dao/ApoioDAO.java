
package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.Apoio;
import br.edu.ifpr.irati.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ApoioDAO implements IApoioDao{

    @Override
    public List<Apoio> buscarApoiosAtivos(Serializable idUsuario) {
       Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from apoio, professor where estadoApoio = 'Ativo' and idUsuario = ?";
        Query query = session.createQuery(hql);
        query.setSerializable(0, idUsuario);
        List results = query.list();
        session.clear();
        session.close();
        return results;
    }

    @Override
    public List<Apoio> buscarApoiosPorProfessor(Serializable idUsuario) {
        
     Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from apoio, professor where idUsuario = ?";
        Query query = session.createQuery(hql);
        query.setSerializable(0, idUsuario);
        List results = query.list();
        session.clear();
        session.close();
        return results;
        
    }
    
}
