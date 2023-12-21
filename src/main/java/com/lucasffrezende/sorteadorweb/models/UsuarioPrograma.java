package com.lucasffrezende.sorteadorweb.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_usuario_programa")
public class UsuarioPrograma extends GenericDomain {

    @ManyToOne
    @JoinColumn(name = "usuario_codigo")
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "programa_codigo")
    private Programa programa;

}
