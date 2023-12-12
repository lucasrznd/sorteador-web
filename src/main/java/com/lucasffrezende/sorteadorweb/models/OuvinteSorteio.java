package com.lucasffrezende.sorteadorweb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_ouvinte_sorteio")
public class OuvinteSorteio extends GenericDomain {

    @ManyToOne
    private Ouvinte ouvinte;

    @ManyToOne
    private Sorteio sorteio;

}
