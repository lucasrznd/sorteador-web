package com.lucasffrezende.sorteadorweb.converters;

import com.lucasffrezende.sorteadorweb.models.EmpresaAssociada;
import com.lucasffrezende.sorteadorweb.models.Usuario;
import com.lucasffrezende.sorteadorweb.services.UsuarioService;
import io.micrometer.common.util.StringUtils;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter<Usuario> {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Usuario getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        Usuario retorno = null;

        if (StringUtils.isNotBlank(value)) {
            retorno = this.usuarioService.buscaPorCodigo(Long.parseLong(value));
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Usuario value) {
        if (value != null) {
            Long codigo = ((Usuario) value).getCodigo();
            String retorno = (codigo == null ? null : codigo.toString());

            return retorno;
        }

        return "";
    }

}