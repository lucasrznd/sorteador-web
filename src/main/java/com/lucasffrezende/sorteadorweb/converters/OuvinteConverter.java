package com.lucasffrezende.sorteadorweb.converters;

import com.lucasffrezende.sorteadorweb.models.Ouvinte;
import com.lucasffrezende.sorteadorweb.services.OuvinteService;
import io.micrometer.common.util.StringUtils;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FacesConverter(forClass = Ouvinte.class)
public class OuvinteConverter implements Converter<Ouvinte> {

    @Autowired
    private OuvinteService ouvinteService;

    @Override
    public Ouvinte getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        Ouvinte retorno = null;

        if (StringUtils.isNotBlank(value)) {
            retorno = this.ouvinteService.buscaPorCodigo(Long.parseLong(value));
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Ouvinte value) {
        if (value != null) {
            Long codigo = ((Ouvinte) value).getCodigo();
            String retorno = (codigo == null ? null : codigo.toString());

            return retorno;
        }

        return "";
    }

}