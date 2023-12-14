package com.lucasffrezende.sorteadorweb.models.enums;

import lombok.Getter;
import lombok.Setter;

public enum TipoUsuario {

    ADMIN("Administrador"),
    LOCUTOR("Locutor"),
    TECNICO("Técnico");

    @Getter @Setter
    private String descricao;

    private TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

}
