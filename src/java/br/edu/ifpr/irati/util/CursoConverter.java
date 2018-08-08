/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.irati.util;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.modelo.Curso;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



@FacesConverter(forClass = Curso.class, value = "cursoConverter")
public class CursoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        if (value == null || value.isEmpty()) {
            return null;
        } else {
            Integer id = Integer.parseInt(value);
            Dao <Curso> cDao = new GenericDAO<>(Curso.class);
            Curso curso  = cDao.buscarPorId(id);
            return curso;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        Curso curso = (Curso) value;
        if (curso != null) {
            return String.valueOf(curso.getIdCurso());
        } else {
            return null;
        }

    }

}
