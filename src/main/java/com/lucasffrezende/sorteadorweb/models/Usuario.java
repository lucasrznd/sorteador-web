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
    @JoinColumn(name = "pessoa_codigo")
    private Pessoa pessoa;

    @Column(name = "login")
    private String login;

    @Column(name = "senha")
    private String senha;

    @Column(name = "ativo")
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario")
    private TipoUsuario tipoUsuario;

}
