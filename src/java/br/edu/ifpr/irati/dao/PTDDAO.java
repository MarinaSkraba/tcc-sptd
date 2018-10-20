/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.PTD;
import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class PTDDAO implements IPTDDAO {

    @Override
    public List<PTD> buscarPTDsConcluidosPorProfessor(Serializable idUsuario) {
        int id = (int) idUsuario;
        String estado = "CONCLUÍDO";
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from ptd";
        Query query = session.createQuery(hql);
        List<PTD> results = query.list();
        List<PTD> filtrados = new ArrayList<>();
        for (PTD ptd : results) {
            if (ptd.getProfessor().getIdUsuario() == id) {
                if (ptd.getEstadoPTD().equals(estado)) {
                    filtrados.add(ptd);
                }
            }
        }
        session.clear();
        session.close();
        return filtrados;
    }
    
    @Override
    public List<PTD> buscarPTDsConcluidos(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        String estado = "CONCLUÍDO";
        String hql = "from ptd p where p.estadoPTD like '"+ estado +"' ";       
        Query query = session.createQuery(hql);
        List<PTD> results = query.list();
        session.clear();
        session.close();
        return results;
    }
    
    @Override
    public List<PTD> buscarPTDsAprovadosPorProfessor(Serializable idUsuario) {
        int id = (int) idUsuario;
        String estado = "APROVADO";
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from ptd";
        Query query = session.createQuery(hql);
        List<PTD> results = query.list();
        List<PTD> filtrados = new ArrayList<>();
        for (PTD ptd : results) {
            if (ptd.getProfessor().getIdUsuario() == id) {
                if (ptd.getEstadoPTD().equals(estado)) {
                    filtrados.add(ptd);
                }
            }
        }
        session.clear();
        session.close();
        return filtrados;
    }

    @Override
    public List<PTD> buscarPTDsEmEdicaoPorProfessor(Serializable idUsuario) {
        int id = (int) idUsuario;
        String estado = "EDICAO";
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from ptd";
        Query query = session.createQuery(hql);
        List<PTD> results = query.list();
        List<PTD> filtrados = new ArrayList<>();
        for (PTD ptd : results) {
            if (ptd.getProfessor().getIdUsuario() == id) {
                if (ptd.getEstadoPTD().equals(estado)) {
                    filtrados.add(ptd);
                }
            }
        }
        session.clear();
        session.close();
        return filtrados;
    }

    @Override
    public List<PTD> buscarPTDEmAvaliacao() {
        String estado = "AVALIACAO";
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from ptd";
        Query query = session.createQuery(hql);
        List<PTD> results = query.list();
        List<PTD> filtrados = new ArrayList<>();
        for (PTD ptd : results) {
            if (ptd.getEstadoPTD().equals(estado)) {
                filtrados.add(ptd);
            }
        }
        session.clear();
        session.close();
        return filtrados;
    }
    
    @Override
    public List<PTD> buscarPTDsReprovadosPorProfessor(Serializable idUsuario) {
        int id = (int) idUsuario;
        String estado = "REPROVADO";
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from ptd";
        Query query = session.createQuery(hql);
        List<PTD> results = query.list();
        List<PTD> filtrados = new ArrayList<>();
        for (PTD ptd : results) {
            if (ptd.getProfessor().getIdUsuario() == id) {
                if (ptd.getEstadoPTD().equals(estado)) {
                    filtrados.add(ptd);
                }
            }
        }
        session.clear();
        session.close();
        return filtrados;
    }

}
