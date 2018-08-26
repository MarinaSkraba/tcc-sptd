/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.Administracao;
import br.edu.ifpr.irati.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class AdministracaoDAO implements IAdministracaoDao {

    @Override
    public List<Administracao> buscarAdministracoesAtivas() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from administracao where estadoAtividadeAdministracao = Ativo";
        Query query = session.createQuery(hql);
        List results = query.list();
        session.clear();
        session.close();
        return results;
    }

}
