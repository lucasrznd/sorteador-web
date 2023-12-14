package com.lucasffrezende.sorteadorweb.models;

import jakarta.persistence.CascadeType;
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

    @ManyToOne(cascade = CascadeType.ALL)
    private Programa programa;

}
