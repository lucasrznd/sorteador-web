package com.lucasffrezende.sorteadorweb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_empresa_associada")
public class EmpresaAssociada extends GenericDomain {

    private String nome;

    @OneToOne
    private Endereco endereco;

}
