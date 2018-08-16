/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.util;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.ProjetoExtensao;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



@FacesConverter(forClass = ProjetoExtensao.class, value = "projetoExtensaoConverter")
public class ProjetoExtensaoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        if (value == null || value.isEmpty()) {
            return null;
        } else {
            Integer id = Integer.parseInt(value);
            Dao <ProjetoExtensao> peDAO = new GenericDAO<>(ProjetoExtensao.class);
            ProjetoExtensao projetoExtensao  = peDAO.buscarPorId(id);
            return projetoExtensao;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        ProjetoExtensao projetoExtensao = (ProjetoExtensao) value;
        if (projetoExtensao != null) {
            return String.valueOf(projetoExtensao.getIdProjetoExtensao());
        } else {
            return null;
        }

    }

}
