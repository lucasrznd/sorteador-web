package com.lucasffrezende.sorteadorweb.converters;

import com.lucasffrezende.sorteadorweb.models.Brinde;
import com.lucasffrezende.sorteadorweb.services.BrindeService;
import io.micrometer.common.util.StringUtils;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FacesConverter(forClass = Brinde.class)
public class BrindeConverter implements Converter<Brinde> {

    @Autowired
    private BrindeService brindeService;

    @Override
    public Brinde getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        Brinde retorno = null;

        if (StringUtils.isNotBlank(value)) {
            retorno = this.brindeService.buscaPorCodigo(Long.parseLong(value));
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Brinde value) {
        if (value != null) {
            Long codigo = ((Brinde) value).getCodigo();
            String retorno = (codigo == null ? null : codigo.toString());

            return retorno;
        }

        return "";
    }

}