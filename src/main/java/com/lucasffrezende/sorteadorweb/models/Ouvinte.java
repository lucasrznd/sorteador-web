package com.lucasffrezende.sorteadorweb.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tb_ouvinte")
public class Ouvinte extends GenericDomain {

    @OneToOne(cascade = CascadeType.ALL)
    private Pessoa pessoa;

    @ManyToMany(mappedBy = "ouvinteSet")
    private Set<Sorteio> sorteioSet;

}
