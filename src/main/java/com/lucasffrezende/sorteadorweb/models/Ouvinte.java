package com.lucasffrezende.sorteadorweb.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_ouvinte")
public class Ouvinte extends GenericDomain {

    @OneToOne(cascade = CascadeType.ALL)
    private Pessoa pessoa;

}
