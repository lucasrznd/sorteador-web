package com.lucasffrezende.sorteadorweb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    private Endereco endereco;
    private String telefone;
    private LocalDate dataNascimento;

}
