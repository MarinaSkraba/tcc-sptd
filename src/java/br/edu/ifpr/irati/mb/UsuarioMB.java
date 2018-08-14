/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.mb;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.DiretorEnsino;
import br.edu.ifpr.irati.modelo.Professor;
import br.edu.ifpr.irati.modelo.Usuario;
import br.edu.ifpr.irati.util.Digest;
import br.edu.ifpr.irati.util.HashGenerationException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class UsuarioMB {

    private Usuario usuario;
    private Usuario usuarioLogado;

    public UsuarioMB() {
        usuarioLogado = new Usuario();
        usuario = new Usuario();
    }

    public void salvar() {
        Dao<Usuario> usuarioDAO = new GenericDAO<>(Usuario.class);
        usuarioDAO.salvar(usuario);
    }

    public String alterar(Usuario usuario) {
        this.usuario = usuario;
        return " adicionar xhtml aqui";
    }

    public void desabilitar() {
        //implementar depois
    }

    public String verificarLogin() throws HashGenerationException {
        String senhaSHA512 = Digest.hashString(usuario.getSenhaAlfanumerica(),"SHA-512");
        System.out.println("Chegou criptografia");
        System.out.println(getUsuarioLogado().getEmail());
        System.out.println(senhaSHA512);
        Dao<Usuario> usuarioDao = new GenericDAO<>(Usuario.class);
        List<Usuario> usuarios = usuarioDao.verificarUsuario(getUsuarioLogado().getEmail(), senhaSHA512);
        if (usuarios.isEmpty()) {
            setUsuarioLogado(new Usuario());
            System.out.println("Chegou object");
            System.out.println(getUsuarioLogado());
            return "/Login";
        } else {
            setUsuarioLogado(usuarios.get(0));
            System.out.println("Chegou object");
            System.out.println(getUsuarioLogado());
            if (getUsuarioLogado() instanceof Professor) {
                return "/Notificacoes";
            } else if (getUsuarioLogado() instanceof DiretorEnsino) {
                return "/Notificacoes";
            }
            return "/Notificacoes";
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    /**
     * @return the usuarioLogado
     */
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    /**
     * @param usuarioLogado the usuarioLogado to set
     */
    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

}
