package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.DiretorEnsino;
import br.edu.ifpr.irati.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class DiretorEnsinoDAO implements IDiretorEnsinoDAO{

    @Override
    public List<DiretorEnsino> buscarDiretoresASeremHabilitados() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String estado = "AHabilitar";
        String hql = "from usuario u where u.estadoUsuario like '" + estado + "' ";
        Query query = session.createQuery(hql);
        List<DiretorEnsino> results = query.list();
        session.clear();
        session.close();
        return results;
    }
    
}
