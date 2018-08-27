
package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.Apoio;
import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ApoioDAO implements IApoioDao{

    @Override
    public List<Apoio> buscarApoiosAtivos(Professor idUsuario) {
       Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from apoio, professor where estadoApoio = 'Ativo' and idUsuario = ?";
        Query query = session.createQuery(hql);
        List results = query.list();
        session.clear();
        session.close();
        return results;
    }
    
}
