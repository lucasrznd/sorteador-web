package com.lucasffrezende.sorteadorweb.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_empresa_associada")
public class EmpresaAssociada extends GenericDomain {

    private String nome;

    @Embedded
    private Endereco endereco;
    private String telefone;

}
