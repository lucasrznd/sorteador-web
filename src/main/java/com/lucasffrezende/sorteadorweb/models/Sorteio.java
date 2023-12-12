package com.lucasffrezende.sorteadorweb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tb_sorteio")
public class Sorteio extends GenericDomain {

    private LocalDateTime dataHora;

    @ManyToOne
    private Brinde brinde;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private ResultadoSorteio resultado;

}
