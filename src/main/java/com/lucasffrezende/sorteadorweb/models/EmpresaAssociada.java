package com.lucasffrezende.sorteadorweb.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_empresa_associada")
public class EmpresaAssociada extends GenericDomain {

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Embedded
    private Endereco endereco;

}
