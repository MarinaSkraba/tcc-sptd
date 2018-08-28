package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.OutroTipoAtividade;
import br.edu.ifpr.irati.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class OutroTipoAtividadeDAO implements IOutroTipoAtividadeDao {

    @Override
    public List<OutroTipoAtividade> buscarOutrosTipoAtividadesAtivas(Serializable idUsuario) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from outrotipoatividade, professor where estadoOutroTipoAtividade = 'Ativo' and idUsuario = ? ";
        Query query = session.createQuery(hql);
        List results = query.list();
        session.clear();
        session.close();
        return results;
    }

}