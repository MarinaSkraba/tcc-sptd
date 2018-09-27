package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.modelo.ProjetoPesquisaExtensao;
import br.edu.ifpr.irati.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ProjetoPesquisaExtensaoDAO implements IProjetoPesquisaExtensaoDao {

    @Override
    public List<ProjetoPesquisaExtensao> buscarProjetosExtensaoAtivos(Serializable idUsuario) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from projetopesquisaextensao, professor where estadoProjetoExtensao = 'Ativo' and idUsuario = ?";
        Query query = session.createQuery(hql);
        query.setSerializable(0, idUsuario);
        List results = query.list();
        session.clear();
        session.close();
        return results;
    }

    @Override
    public List<ProjetoPesquisaExtensao> buscarProjetosExtensaoPorProfessor(Serializable idUsuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from projetopesquisaextensao, professor where idUsuario = ?";
        Query query = session.createQuery(hql);
        query.setSerializable(0, idUsuario);
        List results = query.list();
        session.clear();
        session.close();  
        return results;

    }

    @Override
    public List<ProjetoPesquisaExtensao> buscarProjetosExtensaoColabPorProfessor(Serializable idUsuario) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from projetopesquisaextensao, professor, participacao where idUsuario = ? and rotulo = 'Colaborador'";
        Query query = session.createQuery(hql);
        query.setSerializable(0, idUsuario);
        List results = query.list();
        session.clear();
        session.close();
        return results;

    }

}
