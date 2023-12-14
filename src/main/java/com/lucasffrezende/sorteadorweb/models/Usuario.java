package com.lucasffrezende.sorteadorweb.models;

import com.lucasffrezende.sorteadorweb.models.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_usuario")
public class Usuario extends GenericDomain {

    @OneToOne(cascade = CascadeType.ALL)
    private Pessoa pessoa;
    private String login;
    private String senha;
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

}
