/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class PTDSubmetidoDAO implements IPTDSubmetidoDAO{

    @Override
    public List<PTDSubmetidoDAO> buscarPTDsTodosSubmetidosPorProfessor(Professor professor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PTDSubmetidoDAO> buscarPTDsSubmetidosAprovadosPorProfessor(Professor professor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from ptdincompleto, professor where ptdsubmetido.estado = ? and professor.idUsuario = ? ";
        Query query = session.createQuery(hql);
        query.setSerializable(0, "Aprovado");
        query.setSerializable(1, professor.getIdUsuario());
        List results = query.list();
        session.clear();
        session.close();
        return results;
    }
    
}
