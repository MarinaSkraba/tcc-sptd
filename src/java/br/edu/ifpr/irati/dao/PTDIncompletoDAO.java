/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.PTDIncompleto;
import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author willi
 */
public class PTDIncompletoDAO implements IPTDIncompletoDAO{

    @Override
    public List<PTDIncompleto> buscarPTDsIncompletosPorProfessor(Professor professor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from PTDIncompleto, professor where professor.idUsuario = ? ";
        Query query = session.createQuery(hql);
        query.setSerializable(0, professor.getIdUsuario());
        List results = query.list();
        session.clear();
        session.close();
        return results;
    }
    
}
