package com.lucasffrezende.sorteadorweb.models;

import com.lucasffrezende.sorteadorweb.models.enums.TipoUsuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_usuario")
public class Usuario extends GenericDomain {

    private String login;
    private String senha;
    private TipoUsuario tipoUsuario;

}
