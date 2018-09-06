package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.Horario;
import br.edu.ifpr.irati.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class HorarioDAO implements IHorarioDao {

    @Override
    public List<Horario> buscarHorariosPorAula(Serializable idAula) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from horario, aula_horario where horario.idHorario = aula_horario.horariosAula_idHorario and aula_horario.aula_idAula = ?";
        Query query = session.createQuery(hql);
        query.setSerializable(0, idAula);
        List results = query.list();
        session.clear();
        session.close();
        return results;

    }

}
