/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.util;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.ProjetoPesquisa;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



@FacesConverter(forClass = ProjetoPesquisa.class, value = "projetoPesquisaConverter")
public class ProjetoPesquisaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        if (value == null || value.isEmpty()) {
            return null;
        } else {
            Integer id = Integer.parseInt(value);
            Dao <ProjetoPesquisa> ppDAO = new GenericDAO<>(ProjetoPesquisa.class);
            ProjetoPesquisa projetoPesquisa  = ppDAO.buscarPorId(id);
            return projetoPesquisa;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        ProjetoPesquisa projetoPesquisa = (ProjetoPesquisa) value;
        if (projetoPesquisa != null) {
            return String.valueOf(projetoPesquisa.getIdProjetoPesquisa());
        } else {
            return null;
        }

    }

}
