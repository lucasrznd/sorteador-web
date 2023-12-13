package com.lucasffrezende.sorteadorweb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_usuario_programa")
public class UsuarioPrograma extends GenericDomain {

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Programa programa;

}
