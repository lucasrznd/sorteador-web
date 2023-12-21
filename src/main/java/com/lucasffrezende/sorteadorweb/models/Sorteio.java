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
    @JoinColumn(name = "programa_codigo")
    private Programa programa;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "brinde_codigo")
    private Brinde brinde;

    @ManyToOne
    @JoinColumn(name = "usuario_codigo")
    private Usuario usuario;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_participacao", joinColumns = {@JoinColumn(name = "sorteio_codigo")},
            inverseJoinColumns = {@JoinColumn(name = "ouvinte_codigo")})
    private Set<Ouvinte> ouvinteSet = new HashSet<>();

    @Column(name = "ativo")
    private Boolean ativo;

}
