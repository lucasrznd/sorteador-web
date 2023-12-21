package com.lucasffrezende.sorteadorweb.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_programa")
public class Programa extends GenericDomain {

    @Column(name = "nome")
    private String nome;

    @Column(name = "ativo")
    private Boolean ativo;

}
