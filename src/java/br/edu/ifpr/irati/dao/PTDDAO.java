/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.Administracao;
import br.edu.ifpr.irati.modelo.Apoio;
import br.edu.ifpr.irati.modelo.AtividadeASerProposta;
import br.edu.ifpr.irati.modelo.Aula;
import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.modelo.ManutencaoEnsino;
import br.edu.ifpr.irati.modelo.OutroTipoAtividade;
import br.edu.ifpr.irati.modelo.PTD;
import br.edu.ifpr.irati.modelo.Participacao;
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
    public List<PTD> buscarPTDsConcluidos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String estado = "CONCLUÍDO";
        String hql = "from ptd p where p.estadoPTD like '" + estado + "' ";
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

    @Override
    public void excluirPTDEOQueTemDentro(PTD ptd) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Administracao> adms = ptd.getAdministrativas();
        List<Apoio> apoios = ptd.getApoios();
        List<AtividadeASerProposta> aSerPropostas = ptd.getAtividadesASeremPropostas();
        List<Aula> aulas = ptd.getAulas();
        List<ManutencaoEnsino> manuEnsinos = ptd.getManutencoesEnsino();
        List<Participacao> parts = ptd.getParticipacoes();
        List<OutroTipoAtividade> oTAs = ptd.getOutrosTiposAtividades();
        
        for (Administracao adm : ptd.getAdministrativas()) {
            List<Horario> horariosAux = adm.getHorariosAdministracao();
            for (Horario horario : horariosAux) {
                adm.getHorariosAdministracao().remove(horario);
                session.update(ptd.getAdministrativas());
                session.delete(horario);
            }
        }
        for (Administracao adm : adms) {
            ptd.getAdministrativas().remove(adm);
            session.update(ptd);
            session.delete(adm);
        }
        for (Apoio apoio : ptd.getApoios()) {
            List<Horario> horariosAux = apoio.getHorariosApoio();
            for (Horario horario : horariosAux) {
                apoio.getHorariosApoio().remove(horario);
                session.update(apoio);
                session.delete(horario);
            }
        }
        for (Apoio apoio : apoios) {
            ptd.getApoios().remove(apoio);
            session.update(ptd);
            session.delete(apoio);
        }
        for (AtividadeASerProposta aSerProposta : ptd.getAtividadesASeremPropostas()) {
            List<Horario> horariosAux = aSerProposta.getHorariosAtividadesASerProposta();
            for (Horario horario : horariosAux) {
                aSerProposta.getHorariosAtividadesASerProposta().remove(horario);
                session.update(aSerProposta);
                session.delete(horario);
            }
        }
        for (AtividadeASerProposta aSerProposta : aSerPropostas) {
            ptd.getAtividadesASeremPropostas().remove(aSerProposta);
            session.update(ptd);
            session.delete(aSerProposta);
        }
        for (Aula aula : ptd.getAulas()) {
            List<Horario> horariosAux = aula.getHorariosAula();
            for (Horario horario : horariosAux) {
                aula.getHorariosAula().remove(horario);
                session.update(aula);
                session.delete(horario);
            }
        }
        for (Aula aula : aulas) {
            ptd.getAulas().remove(aula);
            session.update(ptd);
            session.delete(aula);
        }
        for (ManutencaoEnsino manuEnsino : ptd.getManutencoesEnsino()) {
            List<Horario> horariosAux = manuEnsino.getHorariosManutecao();
            for (Horario horario : horariosAux) {
                manuEnsino.getHorariosManutecao().remove(horario);
                session.update(manuEnsino);
                session.delete(horario);
            }
        }
        for (ManutencaoEnsino manuEnsino : manuEnsinos) {
            ptd.getManutencoesEnsino().remove(manuEnsino);
            session.update(ptd);
            session.delete(manuEnsino);
        }
        for (Participacao part : ptd.getParticipacoes()) {
            List<Horario> horariosAux = part.getHorariosParticipacao();
            for (Horario horario : horariosAux) {
                part.getHorariosParticipacao().remove(horario);
                session.update(part);
                session.delete(horario);
            }
        }
        for (Participacao part : parts) {
            ptd.getParticipacoes().remove(part);
            session.update(ptd);
            session.delete(part);
        }
        for (OutroTipoAtividade ota : ptd.getOutrosTiposAtividades()) {
            List<Horario> horariosAux = ota.getHorariosOutroTipoAtividade();
            for (Horario horario : horariosAux) {
                ota.getHorariosOutroTipoAtividade().remove(horario);
                session.update(ota);
                session.delete(horario);
            }
        }
        for (OutroTipoAtividade ota : oTAs) {
            ptd.getOutrosTiposAtividades().remove(ota);
            session.update(ptd);
            session.delete(ota);
        }
        
        session.delete(ptd);

        session.getTransaction().commit();
        session.clear();
        session.close();
    }

}
