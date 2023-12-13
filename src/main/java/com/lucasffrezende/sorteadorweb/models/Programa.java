package com.lucasffrezende.sorteadorweb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_programa")
public class Programa extends GenericDomain {

    private String nome;

}
