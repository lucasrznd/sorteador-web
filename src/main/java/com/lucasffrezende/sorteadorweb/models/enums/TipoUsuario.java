package com.lucasffrezende.sorteadorweb.models.enums;

import lombok.Getter;
import lombok.Setter;

public enum TipoUsuario {

    ADMIN("Administrador"),
    TECNICO("Técnico"),
    LOCUTOR("Locutor"),
    COLABORADOR("Colaborador"),
    CONVIDADO("Convidado"),
    FINANCEIRO("Financeiro");

    @Getter @Setter
    private String descricao;

    private TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

}
