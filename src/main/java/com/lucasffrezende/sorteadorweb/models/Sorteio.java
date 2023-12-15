package com.lucasffrezende.sorteadorweb.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tb_sorteio")
public class Sorteio extends GenericDomain {

    @ManyToOne
    private Programa programa;

    private LocalDateTime dataHora;

    @ManyToOne
    private Brinde brinde;

    @ManyToOne
    private Usuario usuario;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_participacao",
            joinColumns = @JoinColumn(name = "sorteio_codigo"),
            inverseJoinColumns = @JoinColumn(name = "ouvinte_codigo"))
    private Set<Ouvinte> ouvinteSet = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private ResultadoSorteio resultado;

}
