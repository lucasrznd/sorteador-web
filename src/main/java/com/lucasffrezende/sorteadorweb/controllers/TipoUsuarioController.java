package com.lucasffrezende.sorteadorweb.controllers;

import com.lucasffrezende.sorteadorweb.models.enums.TipoUsuario;
import jakarta.faces.model.SelectItem;
import org.springframework.stereotype.Component;

@Component
public class TipoUsuarioController {

    public SelectItem[] getTiposUsuarios() {
        TipoUsuario[] tipos = TipoUsuario.values();
        SelectItem[] items = new SelectItem[tipos.length];

        for (int i = 0; i < tipos.length; i++) {
            items[i] = new SelectItem(tipos[i], tipos[i].name());
        }

        return items;
    }

}
