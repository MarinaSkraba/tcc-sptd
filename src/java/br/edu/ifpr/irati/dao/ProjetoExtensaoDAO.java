
package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.modelo.ProjetoExtensao;
import br.edu.ifpr.irati.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ProjetoExtensaoDAO implements IProjetoExtensaoDao {

    @Override
    public List<ProjetoExtensao> buscarProjetosExtensaoAtivos(Professor professor) {
     
     Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from projetextensao, professor where estadoProjetoExtensao = 'Ativo' and idUsuario = ?";
        Query query = session.createQuery(hql);
        List results = query.list();
        session.clear();
        session.close();
        return results;   
    }
    
}
