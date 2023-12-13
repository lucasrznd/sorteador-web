package com.lucasffrezende.sorteadorweb.converters;

import com.lucasffrezende.sorteadorweb.models.EmpresaAssociada;
import com.lucasffrezende.sorteadorweb.services.EmpresaAssociadaService;
import io.micrometer.common.util.StringUtils;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FacesConverter(forClass = EmpresaAssociada.class)
public class EmpresaAssociadaConverter implements Converter<EmpresaAssociada> {

    @Autowired
    private EmpresaAssociadaService empresaAssociadaService;

    @Override
    public EmpresaAssociada getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        EmpresaAssociada retorno = null;

        if (StringUtils.isNotBlank(value)) {
            retorno = this.empresaAssociadaService.buscaPorCodigo(Long.parseLong(value));
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, EmpresaAssociada value) {
        if (value != null) {
            Long codigo = ((EmpresaAssociada) value).getCodigo();
            String retorno = (codigo == null ? null : codigo.toString());

            return retorno;
        }

        return "";
    }

}