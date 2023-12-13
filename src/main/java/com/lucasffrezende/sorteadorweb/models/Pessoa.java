package com.lucasffrezende.sorteadorweb.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tb_pessoa")
public class Pessoa extends GenericDomain {

    private String nome;

    @Embedded
    private Endereco endereco;
    private String telefone;
    private LocalDate dataNascimento;

}
