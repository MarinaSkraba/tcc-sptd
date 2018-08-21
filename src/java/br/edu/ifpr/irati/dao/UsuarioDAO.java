package br.edu.ifpr.irati.dao;

import br.edu.ifpr.irati.modelo.DiretorEnsino;
import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.modelo.Usuario;
import br.edu.ifpr.irati.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class UsuarioDAO implements IUsuarioDao {

    @Override
    public Usuario verificarUsuario(String email, String senhaAlfanumerica) {
        Usuario u = new Usuario();
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql01 = "from usuario where email = ? and senhaAlfanumérica = ?";
        Query query = session.createQuery(hql01);
        query.setString(0, email);
        query.setString(1, senhaAlfanumerica);
        List resultados = query.list();
        if (resultados.isEmpty() != true) {
            u = (Usuario) resultados.get(0);
            String hql02 = "from professor where idUsuario = ?";
            query = session.createQuery(hql02);
            query.setInteger(0, u.getIdUsuario());
            List professores = query.list();
            if (professores.isEmpty()) {
                u = (DiretorEnsino) u;
            } else {
                u = (Professor) u;
            }
        }
        session.clear();
        session.close();
        return u;
    }

}
