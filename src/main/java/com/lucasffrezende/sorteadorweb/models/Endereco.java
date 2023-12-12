package com.lucasffrezende.sorteadorweb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_endereco")
public class Endereco extends GenericDomain {

    private String rua;
    private String bairro;
    private Integer numero;

}
