/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.PTD;
import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class PTDDAO implements IPTDDAO {

    @Override
    public List<PTD> buscarPTDsAprovados(Professor professor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PTD> buscarPTDsEmEdicao(Professor professor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from ptd where professor_idUsuario = ? and estadoPTD = ?";
        Query query = session.createQuery(hql);
        query.setSerializable(0, professor.getIdUsuario());
        query.setString(1, "EDICAO");
        List results = query.list();
        session.clear();
        session.close();
        return results;
    }

    @Override
    public List<PTD> buscarPTDEmAvaliacao(Professor professor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from ptd where professor_idUsuario = ? and estadoPTD = ?";
        Query query = session.createQuery(hql);
        query.setSerializable(0, professor.getIdUsuario());
        query.setSerializable(1, "AVALIACAO");
        List results = query.list();
        session.clear();
        session.close();
        return results;
    }
    
}
